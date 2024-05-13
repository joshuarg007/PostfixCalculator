import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Display options to the user: manual input or file input
        System.out.println("Choose an option:");
        System.out.println("1. Enter expressions manually");
        System.out.println("2. Read expressions from a file");
        System.out.print("Enter your choice (1 or 2): ");

        // Read the user's choice
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Process the user's choice
        if (choice == 1) {
            // If the user chooses manual input
            System.out.print("Enter postfix expression: ");
            String postfixExpression = scanner.nextLine(); // Read the expression
            // Evaluate the postfix expression using PostfixCalculator.evaluatePostfix method
            double result = PostfixCalculator.evaluatePostfix(postfixExpression);
            // Print the result
            System.out.println("Result: " + result);

        } else if (choice == 2) {
            String filename = "postfix_expressions.txt";
            // Evaluate expressions from the file using PostfixCalculator.evaluateExpressionsFromFile method
            PostfixCalculator.evaluateExpressionsFromFile(filename);

        } else {
            // If the user enters an invalid choice
            System.out.println("Invalid choice. Exiting program.");
        }

        // Close the Scanner object to release resources
        scanner.close();
    }
}
