package ee.taltech.algoritmid.hr;

import java.util.HashMap;
import java.util.Map;

public class DisjointSubsets {
    // iga elemendi vanem (parent)
    private Map<String, String> parent = new HashMap<>();
    // sügavus (depth) optimal
    private Map<String, Integer> depth = new HashMap<>();

    // leia root
    public String find(String element) throws IllegalArgumentException {
        if (!parent.containsKey(element)) {
            throw new IllegalArgumentException("Element not found: " + element);
        }

        // tee madalamaks
        if (!parent.get(element).equals(element)) {
            parent.put(element, find(parent.get(element)));
        }

        return parent.get(element);
    }

    // uhenda hulgad
    public void union(String element1, String element2) throws IllegalArgumentException {
        if (!parent.containsKey(element1)) {
            throw new IllegalArgumentException("Element not found: " + element1);
        }
        if (!parent.containsKey(element2)) {
            throw new IllegalArgumentException("Element not found: " + element2);
        }

        String root1 = find(element1);
        String root2 = find(element2);

        // kui juba samas hulgas, pole vaja midagi teha
        if (root1.equals(root2)) {
            return;
        }

        // uhenda depth jargi, vaiksem puu laheb suurema alla
        int rank1 = depth.get(root1);
        int rank2 = depth.get(root2);

        if (rank1 < rank2) {
            parent.put(root1, root2);
        } else if (rank1 > rank2) {
            parent.put(root2, root1);
        } else {
            parent.put(root2, root1);
            depth.put(root1, rank1 + 1);
        }
    }

    // lisa uus element kui eraldi hulk
    public void addSubset(String element) throws IllegalArgumentException {
        if (parent.containsKey(element)) {
            throw new IllegalArgumentException("Element already exists: " + element);
        }

        // uus element on iseenda vanem (eraldi hulk)
        parent.put(element, element);
        depth.put(element, 0);
    }
}