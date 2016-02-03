import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger n; //(n,e) = public key, (n,d) = private key
    private BigInteger d;
    private BigInteger e;
    
    public RSA(int size) {
        BigInteger p = BigInteger.probablePrime(size / 2, new Random());
        BigInteger q = BigInteger.probablePrime(size /2, new Random());
        this.n = p.multiply(q);
        
        BigInteger x = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        //this.e = BigInteger.probablePrime(size, new Random());
        this.e = new BigInteger(size, new Random());
        
        while(!(x.gcd(e).equals(BigInteger.ONE))) {
            //this.e = this.e.add(new BigInteger("2"));
            this.e = new BigInteger(size, new Random());
        }
        
        this.d = e.modInverse(x);
    }
    
    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getD() {
        return this.d;
    }

    public BigInteger getE() {
        return this.e;
    }
    
    public BigInteger encrypt(BigInteger en) {
        return en.modPow(e, n);
    }
    
    public BigInteger decrypt(BigInteger de) {
        return de.modPow(d, n);
    }
}
