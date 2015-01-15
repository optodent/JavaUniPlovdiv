import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class Main extends JFrame {
	
	private DBConnect connect;	
	
	public Main() {
		
		super();
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel students = new JPanel();
		JPanel books = new JPanel();
		JPanel specialities = new JPanel();
		
		// db connect object should be created in each class that would use it
		connect = new DBConnect();
		
				
				
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Students", students);
		tabs.addTab("Books", books);
		tabs.addTab("Specialities", specialities);
		this.add(tabs);
		
		// specialnosti tab elements
		JTextField textField = new JTextField("", 11);				
		JButton addSpecBtn = new JButton("Add Specialnost");
		
		addSpecBtn.setSize(100, 100);
		//addSpecBtn.setLocation(150, 50);
		addSpecBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {	
	    	  
	    	// if nothing is in the textfield do nothing, else add the text to the database as a specialnost
	    	if(textField.getText().equals("")){
	    		;
	    	}else{
		        connect.addSpec(textField.getText());
		        textField.setText("");
	    	}
	      }
	    });
		
		specialities.add(addSpecBtn);
		specialities.add(textField);
		// end of specialnosti tab elements
		
		
		
		// person tab elements
		
		JComboBox<String> dropDown = new JComboBox<String>(connect.getAllSpec());
		dropDown.setVisible(true);
		dropDown.setSize(100, 100);
		students.add(dropDown);
		
		
		
		
		

		
	}

	public static void main(String[] args) {
		//DBConnect connect = new DBConnect();

		Main main = new Main();


	}

}
