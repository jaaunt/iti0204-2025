package ee.taltech.algoritmid.socialmedia;

import java.util.*;

public class SocialMedia {

    private final Map<String, Integer> visits = new HashMap<>();
    private static final Set<String> ALLOWED_HTTP = Set.of("GET", "POST", "PUT", "DELETE");

    private int top1 = 0;
    private int top2 = 0;

    private String makeKey(String url, String http) {
        return url + "|" + http;
    }

    private void validateHttp(String http) {
        if (!ALLOWED_HTTP.contains(http)) {
            throw new IllegalArgumentException("Invalid HTTP method: " + http);
        }
    }

    /**
     * @param url  - website address
     * @param http - http request method
     */
    public void addVisit(String url, String http) throws IllegalArgumentException {
        validateHttp(http);
        String key = makeKey(url, http);
        int newCount = visits.getOrDefault(key, 0) + 1;
        visits.put(key, newCount);

        if (newCount > top1) {
            top2 = top1;
            top1 = newCount;
        } else if (newCount > top2 && newCount < top1) {
            top2 = newCount;
        }
    }

    /**
     * @param url  - website address
     * @param http - http request method
     * @return the number of visits of the given website
     */
    public Integer getNumberOfVisits(String url, String http) throws IllegalArgumentException {
        validateHttp(http);
        String key = makeKey(url, http);
        return visits.getOrDefault(key, 0);
    }

    /**
     * @return the number of visits of the most visited website
     */
    public Integer getTop1() {
        if (visits.isEmpty()) {
            return 0;
        }
        return Collections.max(visits.values());
    }

    /**
     * @return the list of the number of visits of the top 2 most visited websites sorted in descending order
     */
    public List<Integer> getTop2() {
        if (visits.isEmpty()) {
            return List.of();
        }
        List<Integer> counts = new ArrayList<>(visits.values());
        counts.sort(Collections.reverseOrder());
        if (counts.size() == 1) {
            return List.of(counts.get(0));
        }
        return List.of(counts.get(0), counts.get(1));
    }
}
