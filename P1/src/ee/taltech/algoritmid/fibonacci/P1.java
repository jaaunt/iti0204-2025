package ee.taltech.algoritmid.fibonacci;

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

        long a = 0, b = 1; // initialiseerib nr

        for (int i = 2; i <= n; i++) {  // i aka counter phm,2-st sest essal kahel vastus juba,
            // suurenda iga iteratsioon 1 vorra
            long newB = a + b;
            a = b;
            b = newB;
        }

        return String.valueOf(b);
    }

    public static void main(String[] args) throws Exception {
        P1 p1 = new P1();

        if (!p1.iterativeF(3).equals("2")) {
            throw new Exception("There is a mistake in your solution.");
        }
    }

}
