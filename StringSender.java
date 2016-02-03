import java.util.*;
import java.io.*;
import java.math.*;

public class StringSender implements Runnable
{
    private Scanner scan; private PrintWriter out;
    boolean cont = true;
    BigInteger n,e;
    
    public StringSender(PrintWriter out) {
	this.out = out; scan = new Scanner(System.in);
    }

    public StringSender(PrintWriter out,BigInteger n, BigInteger e) {
	this.out = out; scan = new Scanner(System.in);
	this.n = n;
	this.e = e;
    }

    /*public String encryptString(String msg) {
        byte[] text = msg.getBytes();
        BigInteger encrypted = new BigInteger(text).modPow(e, n);
        return encrypted.toString();
    }*/
    
    public BigInteger encrypt(BigInteger en) {
        return en.modPow(e, n);
    }
    
    public void run() {
	while(cont==true) {
	    System.out.print("Send > "); String str = scan.nextLine();
	    BigInteger encrypted = encrypt(new BigInteger(str));
	    out.println(encrypted); out.flush();
	}
    }

    public void stop(){cont=false;}

    
    public static void main(String[] args)
    {
	Thread st = new Thread( new StringSender(new PrintWriter(System.out)));
	st.start();
	try{
	    Thread.sleep(5000);
	}
	catch(Exception e) {}
	finally {st.stop(); } //.stop();}
    }
    
}

