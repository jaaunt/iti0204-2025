package ee.taltech.algoritmid.socialmedia;

import java.util.*;

public class SocialMedia {

    private final Map<String, Integer> visits = new HashMap<>();
    private static final Set<String> ALLOWED_HTTP = Set.of("GET", "POST", "PUT", "DELETE");

    private String top1Key = null;
    private String top2Key = null;

    private String makeKey(String url, String http) {
        return url + "|" + http;
    }

    private void validateHttp(String http) {
        if (!ALLOWED_HTTP.contains(http)) {
            throw new IllegalArgumentException("Invalid HTTP method: " + http);
        }
    }

    public void addVisit(String url, String http) {
        validateHttp(http);
        String key = makeKey(url, http);
        int newCount = visits.getOrDefault(key, 0) + 1;
        visits.put(key, newCount);

        int top1Count = top1Key == null ? 0 : visits.get(top1Key);
        int top2Count = top2Key == null ? 0 : visits.get(top2Key);

        if (newCount > top1Count) {
            if (!key.equals(top1Key)) {
                top2Key = top1Key;
            }
            top1Key = key;
        } else if (!key.equals(top1Key) && newCount > top2Count) {
            top2Key = key;
        }
    }

    public int getNumberOfVisits(String url, String http) {
        validateHttp(http);
        String key = makeKey(url, http);
        return visits.getOrDefault(key, 0);
    }

    public int getTop1() {
        return top1Key == null ? 0 : visits.get(top1Key);
    }

    public List<Integer> getTop2() {
        int top1Count = top1Key == null ? 0 : visits.get(top1Key);
        int top2Count = top2Key == null ? 0 : visits.get(top2Key);
        return List.of(top1Count, top2Count);
    }
}
