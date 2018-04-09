// Java program to construct an expression tree
 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Stack;
 
// Java program for expression tree
class Node {
 
    char value;
    Node left, right;
 
    Node(char postfix) {
        value = postfix;
        left = right = null;
    }
}
 
class PostfixToInfix {
	StringBuilder stringBuilder = new StringBuilder();
	char temp;
 
    // A utility function to check if 'c'
    // is an operator
 
    boolean isOperator(char postfix) {
        if (postfix == '+' || postfix == '-'
                || postfix == '*' || postfix == '/'
                || postfix == '^') {
            return true;
        }
        return false;
    }
 
    //returns infix expression as sting
    String toString(Node t){
    	if (t != null) {
            if(Character.isDigit(t.value))
            	stringBuilder.append(t.value + " ");
            else{
            	stringBuilder.append("(");
            	toString(t.left);
            	stringBuilder.append(t.value);
            	toString(t.right);
            	stringBuilder.append(")");
            }
    	}
    	String finalString = stringBuilder.toString();
		return finalString;
    }
    
    // Returns root of constructed tree for given
    // postfix expression
    Node constructTree(char postfix[]) {
        Stack<Node> st = new Stack();
        Node t, t1, t2;
 
        // Traverse through every character of
        // input expression
        for (int i = 0; i < postfix.length; i++) {
 
            // If operand, simply push into stack
            if (!isOperator(postfix[i])) {
                t = new Node(postfix[i]);
                st.push(t);
            } else // operator
            {
                t = new Node(postfix[i]);
 
                // Pop two top nodes
                // Store top
                t1 = st.pop();      // Remove top
                t2 = st.pop();
 
                //  make them children
                t.right = t1;
                t.left = t2;
 
                //System.out.println(t1 + "" + t2);
                // Add this subexpression to stack
                st.push(t);
            }
        }
 
        //  only element will be root of expression
        // tree
        t = st.peek();
        st.pop();
 
        return t;
    }
}
 