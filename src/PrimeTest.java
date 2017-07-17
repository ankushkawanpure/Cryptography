import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class PrimeTest {

    public PrimeTest() {

    }

    /***
     */
    public boolean miller_Rabin_helper(BigInteger a, BigInteger m, BigInteger n, int k) {
        BigInteger b;
        b = a.modPow(m, n);
        if (b.mod(n).equals(BigInteger.valueOf(1).mod(n))) {
            return true;
        }
        for (int i = 0; i < k; i++) {
            if (b.mod(n).equals(BigInteger.valueOf(-1).mod(n))) {
                return true;
            } else {
                b = b.modPow(BigInteger.valueOf(2), n);
            }
        }
        return false;
    }

    public BigDecimal millerRabin(BigInteger n, boolean bruteforce) {
        long count = 0;
        int k = 0;
        BigInteger m = n.subtract(BigInteger.valueOf(1));
        while (m.mod(BigInteger.valueOf(2)).equals(0)) {
            m = m.divide(BigInteger.valueOf(2));
            k++;
        }

        for (int a = 1; a < n.intValue(); a++) {
            if (bruteforce != miller_Rabin_helper(BigInteger.valueOf(a), m, n, k)) {
                count++;
            }
        }
        double val = count / (double) (n.intValue() - 1);
        BigDecimal err = BigDecimal.valueOf(val);
        return err;
    }

    public boolean bruteForce(int n){
        int limit = (int)Math.pow(n, 0.5);
        int factors = 0;
        for(int i = 2; i <= limit; i++){
            if(n%i == 0){
                factors++;
            }
            if(factors >= 1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedHashMap<Integer, Double> original_values = new LinkedHashMap<>();
        Custom_Comparator cc = new Custom_Comparator(original_values);
        TreeMap<Integer, Double> sorted_values = new TreeMap<>(cc);
        PrimeTest obj = new PrimeTest();
        int[] n_arr = new int[45];
        int num = 3;

        for (int i = 0; i < n_arr.length; i++) {
            n_arr[i] = num;
            num+=2;
        }
        BigDecimal err;
        for (int n : n_arr) {
            if (n != 0) {
                err = obj.millerRabin(BigInteger.valueOf(n), obj.bruteForce(n));
                if (!err.equals(BigDecimal.valueOf(1.0))) {
//					System.out.println(n + "," + err);
                    original_values.put(n, err.doubleValue());
                }
            }
        }

        sorted_values.putAll(original_values);
        System.out.println(sorted_values.toString());
    }
}

class Custom_Comparator implements Comparator<Integer>{
    LinkedHashMap<Integer,Double> map;

    public Custom_Comparator(LinkedHashMap<Integer,Double> map) {
        this.map = map;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        if(map.get(o1) >= map.get(o2)){
            return -1;
        }
        else{
            return 1;
        }
    }

}
