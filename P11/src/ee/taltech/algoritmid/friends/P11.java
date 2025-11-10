package ee.taltech.algoritmid.friends;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class P11 {
    public DirectedGraph graph = new DirectedGraph();

    private class DirectedGraph {
        private Map<String, List<String>> graph = new HashMap<>();

        /**
         * Add undirected edge to the graph.
         *
         * @param one   one element of the edge
         * @param other the other element of edge
         */
        public void addEdge(String one, String other) {
            if (!graph.containsKey(one)) {
                List<String> edges = new ArrayList<>();
                edges.add(other);
                graph.put(one, edges);
            } else {
                if (!graph.get(one).contains(other)) {
                    graph.get(one).add(other);
                }
            }
        }


        /**
         * Return the graph.
         *
         * @return the internal graph structure.
         */
        public Map<String, List<String>> getGraph() {
            return graph;
        }

        /**
         * Perform breadth first search.
         *
         * @param start the vertex to start the search from
         * @param goal  the goal vertex to find
         * @return the number of vertices of the path from start to goal including start and goal (e.g.,
         * start = A, goal = C, path = A, B, C => 3) and the path itself as a list of integers.
         * NB! You can return null as path and only return the number of nodes
         * that connect the start and goal vertices for partial credit
         * (some tests only check for number of nodes)
         */
        public SimpleEntry<Integer, List<String>> breadthFirstSearch(String start, String goal) {
            if (start == null || goal == null || !graph.containsKey(start)) {
                return null;
            }

            Queue<List<String>> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            queue.add(new ArrayList<>(List.of(start)));
            visited.add(start);

            while (!queue.isEmpty()) {
                List<String> path = queue.poll();
                String last = path.get(path.size() - 1);

                if (last.equals(goal)) {
                    return new SimpleEntry<>(path.size(), path);
                }

                for (String neighbour : graph.getOrDefault(last, new ArrayList<>())) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbour);
                        queue.add(newPath);
                    }
                }
            }
            return null;
        }
    }

    /**
     * Use buildGraphAndFindLink to build a graph using the DirectedGraph class and then use its breadthFirstSearch to
     * return the links.
     *
     * @param friends the list of friends as pairs
     *                (e.g., [["Juhan", "Jaan"], ["Juhan", "Siiri"]] means that "Juhan" knows "Jaan" and "Siiri")
     * @param pair    the pair to be searched
     * @return the number of people that connect the searched pair including the pair itself (e.g., if pair is
     * = ["Mark", "Johanna"] and path is ["Mark", "Peter", "Siiri", "Johanna"], the number of people is 4) the list of people that connect
     * the searched pair (e.g., pair = ["Mark", "Sam"] => result = ["Mark", "Siiri", "Helen", "Peeter", "Sam"])
     */
    public SimpleEntry<Integer, List<String>> buildGraphAndFindLink(
            List<SimpleEntry<String, String>> friends,
            SimpleEntry<String, String> pair) {

        for (SimpleEntry<String, String> entry : friends) {
            graph.addEdge(entry.getKey(), entry.getValue());
        }

        return graph.breadthFirstSearch(pair.getKey(), pair.getValue());
    }
}
