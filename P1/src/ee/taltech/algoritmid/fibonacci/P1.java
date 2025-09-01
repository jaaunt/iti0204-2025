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

        int a = 0, b = 1; // initialiseerib nr


        return "";
    }

    public static void main(String[] args) throws Exception {
        P1 p1 = new P1();

        if (!p1.iterativeF(3).equals("2")) {
            throw new Exception("There is a mistake in your solution.");
        }
    }

}
