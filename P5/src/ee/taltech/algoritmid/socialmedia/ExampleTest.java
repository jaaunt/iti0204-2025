package ee.taltech.algoritmid.socialmedia;

import java.util.List;

public class ExampleTest {
    public static final void testExample() throws Exception {
        SocialMedia socialMedia = new SocialMedia();

        for (int i = 0; i < 4; i++) {
            socialMedia.addVisit("1", "GET");
        }
        for (int i = 0; i < 5; i++) {
            socialMedia.addVisit("2", "GET");
        }

        int x = socialMedia.getNumberOfVisits("1", "GET");
        int y = socialMedia.getNumberOfVisits("2", "GET");
        if (4 != x || 5 != y) {
            System.err.println("Found the wrong amount of visits!");
        }

        if (5 != socialMedia.getTop1()) {
            System.err.println("Finding top1 is incorrect.");
        }
        List<Integer> result = socialMedia.getTop2();
        if (result.get(0) != 5 || result.get(1) != 4) {
            System.err.println("Finding top2 is incorrect.");
        }
    }

    public static void main(String[] args) {
        try {
            testExample();
        } catch (Exception ignored) {
            System.err.println("Something went wrong...");
        }
    }
}
