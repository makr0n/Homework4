import java.util.ArrayDeque;
import java.util.Deque;

public class SimplifyPath {
    public static void main(String[] args) {
        String path = "/home//foo/";
        System.out.println(path);
    }

    public static String simplifyPath(String path) {
        StringBuilder result = new StringBuilder();
        Deque<String> stack = new ArrayDeque<String>();
        String[] pathArray = path.split("/");

        for (int i = 0; i < pathArray.length; i++) {
            if (!stack.isEmpty() && pathArray[i].equals("..")) {
                stack.pop();
            } else if (!pathArray[i].equals("") && !pathArray[i].equals(".") && !pathArray[i].equals("..")) {
                stack.push(pathArray[i]);
            }
        }


        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop()).insert(0, "/");
        }

        return result.toString();
    }
}
