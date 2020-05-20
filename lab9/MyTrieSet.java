import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    public MyTrieSet(){
        root = new Node(false);
    }


    private class Node {
        Boolean isKey = false;
        HashMap<Character, Node> link;

        private Node(boolean isKey) {
            this.isKey = isKey;
            link = new HashMap<>();
        }

        public Node() {
            this.isKey = false;
            link = new HashMap<>();
        }

        private Node addLink(char c) {
            Node newNode = new Node();
            link.put(c, newNode);
            return newNode;
        }

        private boolean containChar (char c) {
            return link.containsKey(c);
        }

        private Node getNodeFromChar(char c) {
            return link.get(c);
        }
    }

    @Override
    public void clear() {
        root = new Node(false);

    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.containChar(c)) {
                curr = curr.getNodeFromChar(c);
            } else {
                return false;
            }
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.containChar(c)) {
                curr = curr.getNodeFromChar(c);
            } else {
                curr = curr.addLink(c);
            }
            if (i == (key.length() - 1)) {
                curr.isKey = true;
            }
        }
    }


    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            curr = curr.getNodeFromChar(prefix.charAt(i));
        }
        return result = getWordsFromNode(prefix, curr);
    }

    private List<String> getWordsFromNode(String s, Node n) {
        if (n == null)  return null;
        List<String> result = new ArrayList<>();
        if (n.isKey)    result.add(s);
        for (Map.Entry<Character, Node> entry: n.link.entrySet()) {
            String newString = s + entry.getKey();
            Node nextNode = entry.getValue();
            result.addAll(getWordsFromNode(newString, nextNode));
        }
        return result;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
