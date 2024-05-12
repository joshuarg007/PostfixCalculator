  

public class Main {
    public static void main(String[] args) {

        String postfixExpression = "5 3 + 8 * 4 -"; // Example postfix expression
        
        double result = PostfixCalculator.evaluatePostfix(postfixExpression);

        System.out.println("Result: " + result);
    }
}
