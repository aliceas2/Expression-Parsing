import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/* This class creates the GUI for the InfixMaths class.
 * It provides an evaluate button along with two textboxes for input/output.
 * 
 * 11/4/2016
 * @author Alice Stanford
 */

public class Gui extends JPanel
implements ActionListener {
	private static final long serialVersionUID = 4709828296331151263L;
	protected JTextArea textArea;
    
    public Gui() {
        super(new BorderLayout());

        //Create GUI items.
        JButton eval = new javax.swing.JButton();
        eval.setPreferredSize(new Dimension(120, 30));
        Label expression = new Label("Enter Infix Expression");
        Label result = new Label("Result : ");
        JTextField infix = new JTextField();
        infix.setColumns(15);
        JTextField resultText = new JTextField();
        resultText.setColumns(10);
        resultText.setEditable(false);

        //Create the text area used for output.  Request
        //enough space for 5 rows and 30 columns.
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);

        //Lay out the main panel.
        setPreferredSize(new Dimension(450, 130));

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        
        northPanel.add(expression);
        northPanel.add(infix);
        
        southPanel.add(result);
        southPanel.add(resultText);
        centerPanel.add(eval);
        
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        add(northPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.PAGE_END);
        
        eval.setText("Evaluate");
        
        eval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String getInfix = infix.getText();
            	String Results = InfixMaths.toPrefix(getInfix);
            	
            	resultText.setText(Results); 
                //evaluateActionPerformed(evt);
            }
        });
    }

    //Create and show GUI.
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Infix Expression Eval");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
	       	createAndShowGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
