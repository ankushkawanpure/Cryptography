import java.math.BigInteger;
import java.util.*;

public class MillerRabin {
	
	// Just used to test out our MillerRabinTest
	public static void main(String[] args) {

        long n = 7;
        for(long a = 1; a <= n -1 ; a++) {

            int answer = MyMillerRabin(a, n);

            System.out.println(a + " " + answer);
        }

    }



    private static int MyMillerRabin(long a, long num) {


//        BigInteger temp = new BigInteger(Long.toString(a));
//        BigInteger n = new BigInteger(Long.toString(num));
//
//        // Ensures that temp > 1 and temp < n.
////        BigInteger temp = BigInteger.ZERO;
////        do {
////            temp = new BigInteger(n.bitLength()-1, r);
////        } while (temp.compareTo(BigInteger.ONE) <= 0);
//
//        // Screen out n if our random number happens to share a factor with n.
//        if (!n.gcd(temp).equals(BigInteger.ONE)) return 0;
//
//        // For debugging, prints out the integer to test with.
//        //System.out.println("Testing with " + temp);
//
//        BigInteger base = n.subtract(BigInteger.ONE);
//        BigInteger TWO = new BigInteger("2");
//
//        // Figure out the largest power of two that divides evenly into n-1.
//        int k=0;
//        while ( (base.mod(TWO)).equals(BigInteger.ZERO)) {
//            base = base.divide(TWO);
//            k++;
//        }
//
//        // This is the odd value r, as described in our text.
//        //System.out.println("base is " + base);
//
//        BigInteger curValue = temp.modPow(base,n);
//
//        // If this works out, we just say it's prime.
//        if (curValue.equals(BigInteger.ONE))
//            return 1;
//
//        // Otherwise, we will check to see if this value successively
//        // squared ever yields -1.
//        for (int i=0; i<k; i++) {
//
//            // We need to really check n-1 which is equivalent to -1.
//            if (curValue.equals(n.subtract(BigInteger.ONE)))
//                return 1;
//
//                // Square this previous number - here I am just doubling the
//                // exponent. A more efficient implementation would store the
//                // value of the exponentiation and square it mod n.
//            else
//                curValue = curValue.modPow(TWO, n);
//        }
//
//        // If none of our tests pass, we return false. The number is
//        // definitively composite if we ever get here.
        return 0;
    }

}