import java.util.*;

public class AlienDictionary {

    public static String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Build graph (edges)
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());
            boolean found = false;

            for (int j = 0; j < minLen; j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    found = true;
                    break;
                }
            }

            // Invalid case: prefix conflict (e.g., "abc" before "ab")
            if (!found && w1.length() > w2.length()) return "";
        }

        // Topological Sort (Kahn’s Algorithm)
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) queue.add(c);
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            order.append(current);

            for (char neighbor : graph.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) queue.add(neighbor);
            }
        }

        return order.length() == inDegree.size() ? order.toString() : "";
    }

    public static void main(String[] args) {
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = {"z", "x", "z"};

        System.out.println("Order for words1: " + alienOrder(words1)); // Output: "wertf"
        System.out.println("Order for words2: " + alienOrder(words2)); // Output: ""
    }
}