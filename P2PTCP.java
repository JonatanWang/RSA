import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;

public class P2PTCP {
    public static void main(String[] args) {
	Scanner scan; Thread st=null;
	Socket peerConnectionSocket=null;
	if(args[0].equals("server")){
	    try{
		ServerSocket ss = new ServerSocket(Integer.parseInt(args[1]));
		RSA rsa = new RSA(Integer.parseInt(args[2]));
		System.out.println("Waiting for connection...");
		peerConnectionSocket = ss.accept();

		PrintWriter send = new PrintWriter(peerConnectionSocket.getOutputStream(), true);
		send.println(rsa.getN());
		send.println(rsa.getE());

		scan = new Scanner (peerConnectionSocket.getInputStream());
		String fromSocket;
                BigInteger decrypted;
		while((fromSocket = scan.nextLine())!=null){
		    decrypted = rsa.decrypt(new BigInteger(fromSocket));
		    System.out.println(decrypted);
		}
	    }catch(IOException e) {System.err.println("Server crash");}
	    finally {st.stop();}
	}
	else if(args[0].equals("client")) {
	    try{
		peerConnectionSocket = new Socket(args[1], Integer.parseInt(args[2]));

		scan = new Scanner (peerConnectionSocket.getInputStream());
		BigInteger n = scan.nextBigInteger();
		BigInteger e = scan.nextBigInteger();

		st = new Thread(new StringSender(new PrintWriter(peerConnectionSocket.getOutputStream()),n,e));
		st.start();
                
                String fromSocket;
                
		while((fromSocket = scan.nextLine())!=null)
			System.out.println(fromSocket);
	    }
      	    catch(Exception e) {System.err.println("Client crash");}
	    finally{st.stop();}
	}
    }
}

