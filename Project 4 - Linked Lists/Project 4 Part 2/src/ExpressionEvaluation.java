import java.util.Scanner;

public class ExpressionEvaluation {
    // Written by: Nadine Mansour
    // Evaluates an infix expression (an expression with no parentheses)
    
    static int result;
    static LinkedStack opStack, valStack;
    
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        opStack       = new LinkedStack();
        valStack      = new LinkedStack();
        
        String expression, thisOp, currOp;
        int thisNum;
        
        System.out.print("Enter an infix expression: ");
        expression = stdin.nextLine();
        stdin      = new Scanner(expression);
        
        while(stdin.hasNext()) {
            if (stdin.hasNextInt()) {
                thisNum = stdin.nextInt();
                valStack.push(thisNum);
            } else {
                thisOp = stdin.next();
                while (thisOp != null)
                    if (opStack.isEmpty()) {
                        opStack.push(thisOp);
                        thisOp = null;
                    } else {
                        currOp = opStack.topString();
                        if (getPrecedence(thisOp, currOp)) {
                            // thisOp has greater precedence
                            opStack.push(thisOp);
                            thisOp = null;
                        } else
                            // currOp has greater precedence
                            computeExpression();
                    }
            }
        }
        if (valStack.numOfNodes == opStack.numOfNodes + 1) {
            while (!opStack.isEmpty()) 
                computeExpression();
            System.out.println("Result = " + result);
        } else   
            System.out.println("Error: Could not compute expression. "
                             + "Check input and try again.");
    }
    
    static boolean getPrecedence(String op1, String op2) {
        // Returns true if operator1 has greater precedence over operator2
        switch (op1) {
            case "-":   case "+":       return false;
            case "/":                   return !op2.equals("*");
            case "*":                   return !op2.equals("/");
            default:                    return false;
        }
    }
    
    static int performOperation(int num1, int num2, String operator) {
        switch (operator) {
            case "-":           result = num1 - num2;       return result;
            case "+":           result = num1 + num2;       return result;
            case "/":           result = num1 / num2;       return result;
            case "*":           result = num1 * num2;       return result;
            default:    
                System.out.println("Illegal operator: " + operator);
                System.exit(0);
                return 0;
        }
    }
    
    static void computeExpression() {
        String operator;
        int num1, num2;
        
        num1     = valStack.top();          valStack.pop();
        num2     = valStack.top();          valStack.pop();
        operator = opStack.topString();     opStack.pop();

        result = performOperation(num2, num1, operator);
        valStack.push(result);
    }
}