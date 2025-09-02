package ee.taltech.algoritmid.fibonacci;

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
     *
     */
    public static String timeToComputeRecursiveFibonacci(int n) {
        if (n >= 30) {
            int base = 30;

            long start = System.nanoTime();
            recursiveF(base);
            long end = System.nanoTime();

            double diffSec = (end - start) / 1_000_000_000.0;
            double estimate = diffSec * Math.pow((1 + Math.sqrt(5)) / 2, n - base);
            double years = estimate / (60.0 * 60.0 * 24.0 * 365.0);

            System.out.println(years);
            return Double.toString(years);
        }

        long start = System.nanoTime();
        recursiveF(n);
        long end = System.nanoTime();

        double diffSec = (end - start) / 1_000_000_000.0;
        double years = diffSec / (60.0 * 60.0 * 24.0 * 365.0);

        System.out.println(years);
        return Double.toString(years);
    }

    public static void main(String[] args) {
        System.out.println(timeToComputeRecursiveFibonacci(20));
    }
}