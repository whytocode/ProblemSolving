import java.util.HashMap;
import java.util.Map;

public class Tries {
    private Node root;

    class Node {
        char value;
        Map<Character, Node> children;
        boolean isEnd;

        private Node(char value) {
            this.value = value;
            children = new HashMap<>();
            isEnd = false;
        }

    }

    public Tries() {
        root = new Node('*');
    }

    public void insert(String word) {
        int length = word.length();

        Node diver = root;

        for(int i = 0 ; i<length ; i++) {
            char key = word.charAt(i);
            if(!diver.children.containsKey(key)) {
                Node node = new Node(key);
                diver.children.put(key,node);
            }
            diver = diver.children.get(key);
        }
        // mark end of word.
        diver.isEnd = true;
    }

}
