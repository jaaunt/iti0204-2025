package ee.taltech.algoritmid.socialmedia;

import java.util.*;

public class SocialMedia {

    private final Map<String, Integer> visits = new HashMap<>();
    private static final Set<String> ALLOWED_HTTP = Set.of("GET", "POST", "PUT", "DELETE");

    private String makeKey(String url, String http) {
        return url + "|" + http;
    }

    private void validateHttp(String http) {
        if (!ALLOWED_HTTP.contains(http)) {
            throw new IllegalArgumentException("Invalid HTTP method: " + http);
        }
    }

    /**
     * Lisab külastuse antud URL+HTTP kombinatsioonile
     */
    public void addVisit(String url, String http) {
        validateHttp(http);
        String key = makeKey(url, http);
        visits.put(key, visits.getOrDefault(key, 0) + 1);
    }

    /**
     * Tagastab külastuste arvu antud URL+HTTP kombinatsiooni kohta
     */
    public int getNumberOfVisits(String url, String http) {
        validateHttp(http);
        String key = makeKey(url, http);
        return visits.getOrDefault(key, 0);
    }

    /**
     * Tagastab kõige rohkem külastatud lehe külastuste arvu
     */
    public int getTop1() {
        int top1 = 0;
        for (int v : visits.values()) {
            if (v > top1) {
                top1 = v;
            }
        }
        return top1;
    }

    /**
     * Tagastab top 2 külastuste arvu suurimas järjekorras.
     * Kui vähem kui 2 lehte, täidetakse 0-ga.
     */
    public List<Integer> getTop2() {
        int top1 = 0;
        int top2 = 0;

        for (int v : visits.values()) {
            if (v > top1) {
                top2 = top1;
                top1 = v;
            } else if (v > top2) {
                top2 = v;
            }
        }

        return List.of(top1, top2);
    }
}
