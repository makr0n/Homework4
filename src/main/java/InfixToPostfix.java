import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class InfixToPostfix {
    public static void main(String[] args) {
        String infix = "9*(3+2)/(5-3)";
        String postfix = infixToPostfix(infix);
        System.out.println(postfix);
        System.out.println(evaluatePostfix(postfix));
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

    static double evaluatePostfix(String exp) {
        Deque<Double> stack = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else {
                double val1 = stack.pop();
                double val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }


}
