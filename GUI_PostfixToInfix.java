
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI_PostfixToInfix extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GUI_PostfixToInfix() {
        initComponents();
    }
	private void initComponents() {
        constructTree = new javax.swing.JButton();
        textAreaInfix = new javax.swing.JTextArea();
        textAreaPostfix = new javax.swing.JTextArea();
        labelInfix = new javax.swing.JLabel();
        labelPostfix = new javax.swing.JLabel();
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        constructTree.setText("Construct Tree");
        constructTree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
					constructTreeActionPerformed(evt);
            }
        });
        
        labelInfix.setText("Infix Expression : ");
        textAreaInfix.setPreferredSize(new Dimension(176,22));
        textAreaInfix.setBorder(border);
        textAreaInfix.setEditable(false);
        
        labelPostfix.setText("Postfix Expression : ");
        textAreaPostfix.setPreferredSize(new Dimension(176,22));
        textAreaPostfix.setBorder(border);
        
        JPanel firstPanel = new JPanel(new BorderLayout());
        firstPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        firstPanel.add(labelPostfix);
        firstPanel.add(textAreaPostfix);
        
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        innerPanel.add(constructTree);
        
        JPanel finalPanel = new JPanel();
        finalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        finalPanel.add(labelInfix);
        finalPanel.add(textAreaInfix);

        add(firstPanel, BorderLayout.NORTH);
        add(innerPanel, BorderLayout.CENTER);
        add(finalPanel, BorderLayout.SOUTH);

        //setSize(700, 300);
        pack();
        setVisible(true);
    	}
	
		//End GUI build
        
	protected void constructTreeActionPerformed(ActionEvent evt){
		textAreaInfix.setText(" ");
    	int index = 0;
    	String inputPost = textAreaPostfix.getText();
    	
    	//determine final array length without spaces
    	int length = inputPost.length();
    	for(int i = 0; i < inputPost.length(); i++){
    		if(inputPost.charAt(i) == ' ')
    			length--;
    	}
    	//create array of all char
    	char[] finalPost = new char[length];
    	try{
    	for (int i = 0; i < inputPost.length(); i++){
    	    char ch = inputPost.charAt(i);
			if(Character.isDigit(inputPost.charAt(i))){
    	    	finalPost[index++] = ch;
    	    }
    	    else if(inputPost.charAt(i) == '+' || inputPost.charAt(i) == '-' 
    	    		|| inputPost.charAt(i) == '*' || inputPost.charAt(i) == '/'){
    	    	finalPost[index++] = ch;
    	    }
			//if char is a space do nothing
    	    else if(inputPost.charAt(i)==' ');
			//all other chars not a digit or math sign throw an error
    	    else{
    	    	JOptionPane.showMessageDialog(null, "Invalid character!", "Error",JOptionPane.ERROR_MESSAGE);
    	    	break;
    	    }
    	}
    	PostfixToInfix postfix = new PostfixToInfix();
        Node root = postfix.constructTree(finalPost);
        String output = postfix.toString(root);
        textAreaInfix.append(output);
        
	}catch (RuntimeException ex) {
	}
    	
	}
	
	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_PostfixToInfix().setVisible(true);
            }
        });
        }
    // Variables declaration
    private javax.swing.JButton constructTree;
    private javax.swing.JTextArea textAreaPostfix;
    private javax.swing.JTextArea textAreaInfix;
    private javax.swing.JLabel labelInfix;
    private javax.swing.JLabel labelPostfix;
    // End of variables declaration    
}
