package ee.taltech.algoritmid.fibonacci;

import java.math.BigInteger;

public class P1 {

    /**
     * Compute the Fibonacci sequence number.
     * @param n The number of the sequence to compute.
     * @return The n-th number in Fibonacci series.
     */
    public String iterativeF(int n) {
        if (n == 0) {
            return "0";
        }
        if (n == 1) {
            return "1";
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE; // initialiseerib nr, big kuna longist ei piisanud

        for (int i = 2; i <= n; i++) {  // i aka counter phm,2-st sest essal kahel vastus juba,
            // suurenda iga iteratsioon 1 vorra
            BigInteger newB = a.add(b);
            a = b;
            b = newB;
        }

        return b.toString();  // anna tagasi teksti nrina
    }

    public static void main(String[] args) throws Exception {
        P1 p1 = new P1();

        if (!p1.iterativeF(3).equals("2")) {
            throw new Exception("There is a mistake in your solution.");
        }
    }

}
