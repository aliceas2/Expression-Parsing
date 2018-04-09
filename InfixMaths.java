import java.util.Stack;
import javax.swing.JOptionPane;

/* This class handles the math end of Infix calculations.
 * toPrefix - handles the loop to go through all the char.
 * doMath - calculates the math required.
 * priority - calculates math symbol priority.
 * 
 * 11/4/2016
 * @author Alice Stanford
 */

public class InfixMaths {

static Stack<Character> operatorStack = new Stack<Character>();
static Stack<Integer> operandStack = new Stack<Integer>();

public static String toPrefix(String expression) {
    expression="("+expression+")";
    int i; 
    int num;
    char token;
    int output = 0;
    
    //token retrieves text
    for (i = 0; i < expression.length(); i++) {
        token = expression.charAt(i);
        
        //if digit push to operand stack
        if (Character.isDigit(token) == true){
        	num = Character.getNumericValue(token);
            operandStack.push(num);
        }
        //if char is a ( it gets added to operator stack
        else if (token == '('){
        	operatorStack.push(token);
		}
        //if char is a )
        else if (token == ')') {
            while (operatorStack.peek() != '(') {
            	Integer tempChar = operandStack.peek();
            	operandStack.pop();
            	char tempOperator = operatorStack.peek();
            	operatorStack.pop();
            	Integer tempChar2 = operandStack.peek();
            	operandStack.pop();
            	output = doMath(tempChar,tempOperator,tempChar2);
                operandStack.push(output);
            }
            operatorStack.pop();
        } 
        
        else if (token == '-' || token == '+' || token == '*' || token == '/'){
        	int k;
        	if(operatorStack.empty())
        		k = 0;
        	else
        		k = priority(operatorStack.peek());
        	int l = priority(token);
            while(!operatorStack.empty() && k>=l){
                	Integer tempChar = operandStack.peek();
                	operandStack.pop();
                	Integer tempChar2 = operandStack.peek();
                	operandStack.pop();
                	char tempOperator = operatorStack.peek();
                	operatorStack.pop();
            		output = doMath(tempChar, tempOperator, tempChar2);
            		operandStack.push(output);
            		k = priority(operatorStack.peek());
            }
            
        	operatorStack.push(token);
        }
    }
    
    while (!operatorStack.empty()) {
    	Integer tempChar = operandStack.peek();
    	operandStack.pop();
    	
    	char tempOperator = operatorStack.peek();
    	operatorStack.pop();
    	
    	Integer tempChar2 = operandStack.peek();
    	operandStack.pop();
    	
    	output = doMath(tempChar, tempOperator, tempChar2);
        operandStack.push(output);
        System.out.println("I am here");
    }
    
    output = operandStack.peek();
    return String.valueOf(output);

}

private static int doMath(int tempChar, Character tempOperator, int tempChar2) {
	int result = 0;
	if(tempOperator=='+')
		result = (tempChar2 + tempChar);
	else if(tempOperator=='-')
		result = (tempChar2 - tempChar);	
	else if(tempOperator=='*'){
		result = (tempChar2 * tempChar);
	}
	else if(tempOperator=='/'){
		if (tempChar == 0)
			//Requirement 1
			JOptionPane.showMessageDialog(null, "Cannot divide by 0", "InfoBox: " + "DivideByZero", JOptionPane.INFORMATION_MESSAGE);
		else
			result = tempChar2 / tempChar;
	}
	return result;
}

private static int priority(char operator) {
    if (operator == '/' || operator == '*')
        return 2;
    if (operator == '+' || operator == '-')
        return 1;
    return 0;
}

}