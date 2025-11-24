package ee.taltech.algoritmid.gemstones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gemstones {

    public static List<Integer> findGemstones(int target, List<Integer> candidates) {

        // lil checks
        if (candidates == null || candidates.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (target < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (target == 0) {
            return new ArrayList<>();
        }

        // no duplicate no neg yes
        List<Integer> allowed = new ArrayList<>();
        for (Integer v : candidates) {
            if (v != null && v > 0 && !allowed.contains(v)) {
                allowed.add(v);
            }
        }
        if (allowed.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }

        Collections.sort(allowed);

        // tables
        int[] bestCount = new int[target + 1];
        int[] perfectUsed = new int[target + 1];
        int[] prevChoice = new int[target + 1];

        for (int i = 1; i <= target; i++) {
            bestCount[i] = Integer.MAX_VALUE;
            perfectUsed[i] = -1;
            prevChoice[i] = -1;
        }

        for (int total = 1; total <= target; total++) {
            for (int gem : allowed) {
                if (gem > total) break;

                if (bestCount[total - gem] == Integer.MAX_VALUE) continue;

                int newCount = bestCount[total - gem] + 1;
                int newPerfect = perfectUsed[total - gem] + (isPerfect(gem) ? 1 : 0);

                boolean better = false;

                if (bestCount[total] == Integer.MAX_VALUE) {
                    better = true;
                } else if (newCount < bestCount[total]) {
                    better = true;
                } else if (newCount == bestCount[total] && newPerfect > perfectUsed[total]) {
                    better = true;
                }  // why is it flagged yellow but works i dont get it
                // aaaaa let it be ig

                if (better) {
                    bestCount[total] = newCount;
                    perfectUsed[total] = newPerfect;
                    prevChoice[total] = gem;
                }
            }
        }

        if (bestCount[target] == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("No combination found to reach the sum");
        }

        List<Integer> output = new ArrayList<>();
        int pos = target;
        while (pos > 0) {
            int g = prevChoice[pos];
            output.add(g);
            pos -= g;
        }

        return output;
    }

    private static boolean isPerfect(int value) {
        return value != 1 && (value % 10 != 0);
    }
}
