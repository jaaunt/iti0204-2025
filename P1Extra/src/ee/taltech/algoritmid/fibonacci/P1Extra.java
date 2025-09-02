package ee.taltech.algoritmid.fibonacci;

import javax.sound.midi.SysexMessage;
import java.math.BigInteger;

public class P1Extra {

    /**
     * Compute the Fibonacci sequence number recursively.
     * (You need this in the timeToComputeRecursiveFibonacci(int n) function!)
     * @param n The n-th number to compute.
     * @return The n-th Fibonacci number as a string.
     */
    public static BigInteger recursiveF(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        return recursiveF(n - 1).add(recursiveF(n - 2));
    }

    /**
     * Estimate or find the exact time required to compute the n-th Fibonacci number.
     * @param n The n-th number to compute.
     * @return The time estimate or exact time in YEARS.
     */
    public static String timeToComputeRecursiveFibonacci(int n) {
        int base = 10;
        if (n <= 1) return "0";

        long start = System.nanoTime();
        recursiveF(base);
        long end = System.nanoTime();

        long diff_sec = (end - start)/1_000_000_000;

        long diff_year = diff_sec/(60*60*24*365);
        return String.format("%.15f", diff_year);
    }

    public static void main(String[] args) {
        System.out.println(timeToComputeRecursiveFibonacci(10));
    }

}