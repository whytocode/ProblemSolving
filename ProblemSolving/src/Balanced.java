import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/***
 { {} [] () <> }
 */
// {{}}}}}
public class Balanced {

    private Deque<Character> stack = new ArrayDeque<>();
    private Map<Character, Integer> map = new HashMap<>();

    public boolean isBalanced(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
            if (shouldPush(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        return stack.isEmpty();
    }

    private boolean shouldPush(char c) {
        return c == '{' || c == '[' || c == '(' || c == '<';
    }
}
