import java.util.Stack;

public class InfixConversion {

    static int precedence(char ch) {
        if (ch == '+' || ch == '-') return 1;
        if (ch == '*' || ch == '/') return 2;
        if (ch == '^') return 3;
        return -1;
    }

    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result.append(ch);
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() &&
                       precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }

    static String infixToPrefix(String exp) {
        exp = reverse(exp);

        StringBuilder modified = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            switch (ch) {
                case '(':
                    modified.append(')');
                    break;
                case ')':
                    modified.append('(');
                    break;
                default:
                    modified.append(ch);
            }
        }

        String postfix = infixToPostfix(modified.toString());
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C";

        System.out.println("Infix Expression  : " + infix);
        System.out.println("Postfix Expression: " + infixToPostfix(infix));
        System.out.println("Prefix Expression : " + infixToPrefix(infix));
    }
}