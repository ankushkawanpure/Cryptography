import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ankush on 11/21/16.
 */
public class ErrorProbabilities {

    /***
     * Function to Run the Miller Rabin test with a and n
     * @param random a value (Random value)
     * @param num   number to check
     * @return 1 if number is prime 0 if number is composite according to Miller-Rabin test
     */
    private int millerRabin(long random, long num) {

        /*Convert both the numbers a and n into BigInteger*/
        BigInteger a = new BigInteger(Long.toString(random));
        BigInteger n = new BigInteger(Long.toString(num));

        /*Check by GCD if they share common factor greater than 1 If GCD(a, n) > 1 return composite*/
        if (!n.gcd(a).equals(BigInteger.ONE)) {
            return 0;
        }

        /*Get n-1 and 2 in BigInteger class*/
        BigInteger numberlessone = n.subtract(BigInteger.ONE);
        BigInteger two = new BigInteger("2");

        /*Find the largest power of 2 that divides n-1*/
        int power = 0;
        while ((numberlessone.mod(two)).equals(BigInteger.ZERO)) {
            numberlessone = numberlessone.divide(two);
            power++;
        }

        /*Get the value of b = a^m mod(n) in BigInteger*/
        BigInteger b = a.modPow(numberlessone,n);

        /*If a^m mod(n) = 1 number is prime*/
        if (b.equals(BigInteger.ONE)) {
            return 1;
        }

        /*Check for number is composite or not*/
        for (int i = 0; i < power; i++) {

            /*Check if a^m mod(n) = -1 mod(n) ie  b =-1 mod(n) ie b = (n -1) mod(n) if true return prime*/
            if (b.equals(n.subtract(BigInteger.ONE))) {
                return 1;
            }
            /*Increase the power of b to b^2  till power-1*/
            else {
                b = b.modPow(two, n);
            }
        }

        /*If no value of b = -1 mod(n) found return number is composite*/
        return 0;
    }

    /**
     * Function to check number is prime or not.
     * @param n number
     * @return 1 if number is prime
     *         0 if number is composite
     */
    private int isPrimeCheck(long n) {

        /*If the number is 2 or 3 return prime */
        if(n == 2 || n == 3) {
            return 1;
        }

        /*Check for the even number*/
        if (n % 2 == 0) {
            return 0;
        }

        /*Check for the prime with the odd number greater then equal to 3 till square root of number*/
        for (long i = 3; i * i <= n; i = i + 2) {

            if (n % i == 0) {
                return 0;
            }
        }

        return 1;

    }

    /**
     * Function to get the error probability for a number
     * @param n number
     * @return error probability
     */
    private double errorOfMillerRabin(long n) {


        long count = 0;
        double error;

        /* Check the current number is actually prime of not*/
        int isPrime = isPrimeCheck(n);

        /* Choose the value of a from the a <= 1 <= n-1 for Miller-Rabin test*/
        for(long a = 1; a <= n -1 ; a++) {

            /* get the answer from Miller-Rabin test with current a and n */
            int answer = millerRabin(a, n);

            /*Check is answer is correct of not is answer is wrong increase the error count*/
            if(isPrime != answer) {
                count ++;
            }
        }

        /*Calculate and return the error probability*/
        error = (double) count / (n-1);
        return error;
    }

    /**
     * Main Function to run the program
     * @param args
     */
    public static void main(String[] args) {
        long loweRange = 2;
        long upperRange = 100;

        /*Take input if given be command line other wise load default*/
        if(args.length > 1) {
            loweRange = Long.parseLong(args[1]);
            upperRange = Long.parseLong(args[2]);
        }


        List<Double> errorList = new ArrayList<>();
        List<Long> number = new ArrayList<>();

        ErrorProbabilities e = new ErrorProbabilities();

        /* Calculate the error probabilities for the numbers in the range*/
        for (long n = (loweRange + 1); n < upperRange; n = n + 2) {
            System.out.println("Error Prob Testing for :" + n);
            double error = e.errorOfMillerRabin(n);
            errorList.add(error);
            number.add(n);
        }

        /*Error Probabilities of all the numbers in range just to double check */
        for (int i = 0; i <errorList.size();i++) {
            System.out.println(number.get(i) + " " + errorList.get(i));
        }


        /*Highest probability calculation for 10 numbers*/
        for (int i = 0; i < 10; i++){
            double max = 0;
            int index;

            for(int itr = 0; itr <errorList.size(); itr++) {
                if(max < errorList.get(itr)) {
                    max = errorList.get(itr);
                }
            }

            index = errorList.indexOf(max);
            /*Print the maximum number and its error probability*/
            System.out.println(number.get(index) + " " + errorList.get(index));

            errorList.remove(index);
            number.remove(index);
        }
    }
}
