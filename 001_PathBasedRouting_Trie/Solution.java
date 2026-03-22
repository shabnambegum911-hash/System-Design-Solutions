
import java.util.*;

// Trie Node class
class TrieNode {
    Map<String, TrieNode> children;
    String serviceName; // Service assigned for this path

    TrieNode() {
        children = new HashMap<>();
        serviceName = null;
    }
}

// Trie class for Path-Based Routing
public class Solution {
    private TrieNode root;

    public Solution() {
        root = new TrieNode();
    }

    // Insert a route like "/api/users/**" → "User Service"
    public void insert(String path, String serviceName) {
        TrieNode node = root;
        String[] segments = path.split("/");

        for (String segment : segments) {
            if (segment.isEmpty())
                continue; // skip leading '/'
            node.children.putIfAbsent(segment, new TrieNode());
            node = node.children.get(segment);
        }

        node.serviceName = serviceName;
    }

    // Search for the longest matching service for a request path
    public String search(String requestPath) {
        TrieNode node = root;
        String[] segments = requestPath.split("/");
        String lastService = null;

        for (String segment : segments) {
            if (segment.isEmpty())
                continue;
            if (node.children.containsKey(segment)) {
                node = node.children.get(segment);
                if (node.serviceName != null) {
                    lastService = node.serviceName; // update longest match
                }
            } else {
                break; // no further match
            }
        }

        return lastService;
    }

    // Demo
    public static void main(String[] args) {
        Solution trie = new Solution();

        // Insert routes
        trie.insert("/api/users/**", "User Service");
        trie.insert("/api/orders/**", "Order Service");
        trie.insert("/api/users/profile/**", "User Profile Service");

        // Test requests
        String[] requests = {
                "/api/users/123",
                "/api/orders/789",
                "/api/users/profile/456",
                "/api/products/999"
        };

        for (String req : requests) {
            String service = trie.search(req);
            System.out.println(req + " → " + (service != null ? service : "No Service Found"));
        }
    }
}