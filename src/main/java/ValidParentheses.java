import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParentheses {
    public static void main(String[] args) {
        String input = "([)";
        System.out.println(isValid(input));
    }

    public static boolean isValid(String input) {
        Map<Character, Character> parentheses = Map.of('(', ')', '[', ']', '{', '}');
        Deque<Character> stack = new ArrayDeque<Character>();
        char[] arr = input.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (parentheses.get(arr[i]) != null) {
                stack.push(arr[i]);
            } else if (stack.isEmpty()) {
                return false;
            } else if (arr[i] == parentheses.get(stack.peekFirst())) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
