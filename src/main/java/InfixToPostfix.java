import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class InfixToPostfix {
    public static void main(String[] args) {
        String infix = "4*(3-2)*(5-2)";
        System.out.println(infixToPostfix(infix));
    }

    public static int priorityMatch(char operator) {
        Map<Character, Integer> operators = Map.of('-', 0, '+', 0, '*', 1, '/', 1); //Мап с приоритетами операторов
        if (operators.containsKey(operator)) {
            return operators.get(operator);
        } else {
            return -1;
        }
    }

    public static String infixToPostfix(String infix) {

        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {

            if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9') {
                result.append(infix.charAt(i));
            } else if (infix.charAt(i) == '(') {
                stack.push('(');
            } else if (infix.charAt(i) == ')') {

                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }

            } else {

                while (!stack.isEmpty() && priorityMatch(stack.peek()) >= priorityMatch(infix.charAt(i))) {
                    result.append(stack.pop());
                }
                stack.push(infix.charAt(i));

            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
