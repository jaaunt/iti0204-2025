package ee.taltech.algoritmid.fibonacci;

import java.math.BigInteger;

public class P1Extra {
    public static BigInteger recursiveF(int n) {
        if (n <= 1)
            return BigInteger.valueOf(n);
        return recursiveF(n - 1).add(recursiveF(n - 2));}

    public static String timeToComputeRecursiveFibonacci(int n) {
        if (n >= 30) {
            int base = 30;

            double start = System.nanoTime();
            recursiveF(base);
            double end = System.nanoTime();

            double dif = end - start;
            double diff_sec = (dif / 1_000_000_000);
            double year = diff_sec / 60*60*24*365;
            System.out.println(year);

            double yearReal = (n * year) / base;
            return Double.toString(yearReal);


        }

        double start = System.nanoTime();
        recursiveF(n);
        double end = System.nanoTime();

        double dif = end - start;
        double diff_sec = (dif / 1_000_000_000);
        double year = diff_sec / 60*60*24*365;
        System.out.println(year);
        return Double.toString(year);
    }

    BigInteger subtractBigInteger(BigInteger minuend, int subtrahend) {
        return minuend.subtract(BigInteger.valueOf(subtrahend));
    }


    public static void main(String[] args) {
        System.out.println(timeToComputeRecursiveFibonacci(30));
    }
}