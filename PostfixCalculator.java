/**
 * Resources used:
 *   - https://mrtan.me/post/19.html
 *   - https://www.calcont.in/Calculator/Postfix_calculator/
 *   - https://www.geeksforgeeks.org/evaluation-of-postfix-expression/
 *   - https://jhucsf.github.io/spring2020/assign/assign02.html
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;  // Import the Stack class from java.util package

public class PostfixCalculator {  // Define a class named PostfixCalculator

    // Method to read expressions from a file, evaluate them, and print the results
    public static void evaluateExpressionsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line; // Individual line to be read
            // Read each line from the file until the end
            while ((line = reader.readLine()) != null) {
                // Trim the line to remove leading and trailing whitespace
                double result = evaluatePostfix(line.trim());
                // Print the expression and its result
                System.out.println("Expression: " + line.trim() + ", Result: " + result);
            }
        } catch (IOException e) {
            // Handle any IOException that might occur during file reading
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static double evaluatePostfix(String expression) {  
        Stack<Double> stack = new Stack<>();  // Create a Stack object to store operands

        // Tokenize the expression and process each token
        String[] tokens = expression.split("\\s+");  
        for (String token : tokens) {  // Iterate over each token in the array of tokens
            if (isOperand(token)) {  // Check if the token is an operand
                // Convert the token to a double and push it onto the stack
                stack.push(Double.parseDouble(token));  
            } else if (isOperator(token)) {  // Check if the token is an operator
                if (stack.size() < 2) {  // Check if there are enough operands on the stack
                    // Print an error message if there are not enough operands
                    System.err.println("Invalid expression: Not enough operands for operator " + token);  
                    return Double.NaN;  // Return Double.NaN to indicate an error
                }
                double operand2 = stack.pop();  // Pop the top operand from the stack
                double operand1 = stack.pop();  // Pop the second top operand from the stack
                // Perform the operation with the operands and operator
                double result = performOperation(operand1, operand2, token);  
                stack.push(result);  // Push the result back onto the stack
            } else {  // If the token is neither an operand nor an operator
                // Print an error message indicating an invalid token
                System.err.println("Invalid token: " + token);  
                return Double.NaN;  // Return Double.NaN to indicate an error
            }
        }

        // Check if there is not exactly one operand left on the stack
        if (stack.size() != 1) {  
            // Print an error message if there are too many operands
            System.err.println("Invalid expression: Too many operands");  
            return Double.NaN;  // Indicate an error
        }

        return stack.pop();  // Return the final result obtained from the stack
    }

    // Method isOperand takes a String token as input and returns a boolean
    private static boolean isOperand(String token) {  
        // Check if the token is a valid integer
        return token.matches("-?\\d+");  
    }

    // Method  isOperator takes a String token as input and returns a boolean
    private static boolean isOperator(String token) {  
        // Check if the token is one of the valid operators (+, -, *, /, %)
        return token.matches("[+\\-*/%]");  
    }

    // Method  performOperation takes two operands and an operator as input and returns a double
    private static double performOperation(double operand1, double operand2, String operator) {  
        switch (operator) {  // Use a switch statement to perform the appropriate operation based on the operator
            case "+":  // Addition
                return operand1 + operand2;  // Return the result of addition
            case "-":  // Subtraction 
                return operand1 - operand2;  // Return the result of subtraction
            case "*":  // Multiplication operation
                return operand1 * operand2;  // Return the result of multiplication
            case "/":  // Division 
                return operand1 / operand2;  // Return the result of division
            case "%":  // Modulus 
                return operand1 % operand2;  // Return the result of modulus
            default:  // If the operator is invalid
                // Throw an IllegalArgumentException 
                throw new IllegalArgumentException("Invalid operator: " + operator);  
        }
    }
}
