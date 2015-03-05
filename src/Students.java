

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Students extends JPanel{
	
	DBConnect con;
	private JComboBox<String> dropDown;
	private JTextField fName;
	private JTextField lName;
	private JTextField fakNum;
	private JScrollPane scrollPane;
	private JTable table;
	private String fNameT,lNameT,fakNumT;
	private int spec;
	private boolean clicked = false; 
	
	//not done yet
	private JButton addPplBtn;

	
	public Students(DBConnect con){
		
								
		this.con = con;
		
		//visual stuff
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//dropdown menu
		dropDown = new JComboBox<String>(con.getAllSpec());
		dropDown.setBounds(66, 130, 100, 20);
		dropDown.setVisible(true);
				
		
		this.add(dropDown);
		
		
		//first name text field
		JTextField fName = new JTextField();
		fName.setBounds(66, 36, 100, 20);
		fName.setColumns(10);
		fName.setVisible(true);
				

		this.add(fName );
		
		//last name text field
		JTextField lName = new JTextField();
		lName.setBounds(66, 67, 100, 20);
		lName.setColumns(10);
		lName.setVisible(true);	
				
		
		this.add(lName );
		
		//fakulteten nomer text field
		JTextField fakNum = new JTextField();
		fakNum.setBounds(66, 98, 100, 20);
		fakNum.setColumns(10);
		fakNum.setVisible(true);		
		
		
		this.add(fakNum );
		
		//add student button
		addPplBtn = new JButton("Add Student");	
		addPplBtn.setBounds(176, 98, 89, 52);
		addPplBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {		
	    	  fNameT = fName.getText();
	    	  lNameT = lName.getText();
	    	  fakNumT = fakNum.getText();
	    	  if (fNameT.equals("") || lNameT.equals("") || fakNumT.equals("") ) {
	    		  JOptionPane.showMessageDialog(null, "Unfilled information!", "Error",
                          JOptionPane.ERROR_MESSAGE);

	          }  else {  	  
			    	  spec = dropDown.getSelectedIndex() + 1;
			    	  con.addPerson(fNameT, lNameT, fakNumT, spec);
			    	  fName.setText("");
			    	  lName.setText("");
			    	  fakNum.setText("");
	          }
	      }	   
	    });
		
		this.add(addPplBtn);
		
		// edit student selection button
		JButton editButton = new JButton("Update");
		editButton.setBounds(274, 98, 89, 52);
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int ID = (int) model.getValueAt(table.getSelectedRow(), 0);
				String[] stuNames = model.getValueAt(table.getSelectedRow(), 1).toString().split("\\s+");
				String fName = stuNames[0];
				String lName = stuNames[1];
				String stuFakNum = model.getValueAt(table.getSelectedRow(), 2).toString();
				
				Statement stmt = null;
				try {
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "UPDATE person SET fname='"
							+ fName + "', lname='" + lName
							+ "', faknum='" + stuFakNum 
							+ "' WHERE ID = " + ID;
					System.out.println(query);
					stmt.execute(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			
		});
		this.add(editButton);
		
		// delete student button
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(374, 98, 89, 52);
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				String selected = model.getValueAt(table.getSelectedRow(), 0)
						.toString();
				model.removeRow(table.getSelectedRow());
				
				Statement stmt = null;
				try {
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "DELETE FROM person WHERE id = " + selected;
					stmt.execute(query);

				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}
			}
			
		});
		this.add(deleteButton);
		
		//text at top left
		JLabel lblNewLabel = new JLabel("Students");
		lblNewLabel.setBounds(10, 11, 93, 14);
	
		this.add(lblNewLabel);
		// more text/labels
		JLabel lblNewLabel_1 = new JLabel("F. Name");
		lblNewLabel_1.setBounds(10, 39, 46, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("L. Name");
		lblNewLabel_2.setBounds(10, 70, 46, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fak. Num");
		lblNewLabel_3.setBounds(10, 101, 46, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Speciality");
		lblNewLabel_4.setBounds(10, 132, 46, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.add(lblNewLabel_4);
		
		
		//adding a scrollpane with a table inside of it, containing the students data and books taken
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 245, 464, 206);
		scrollPane.setVisible(false);
		this.add(scrollPane);
		
		table = new JTable();
		table.setVisible(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"ID", "Student", "Fak. Number", "Speciality"
			}
		));
		// stff
		
		
		// -- next 4 lines of code populate the table with the necessary data
		DefaultTableModel model=(DefaultTableModel) table.getModel();
    	for(Object[] person : con.getAllPeopleData()){
    		model.addRow(person);
    	}
    	// --
    	
    	// ********************
    	// REFRESH table button
    	// ********************
    	JButton refreshBtn = new JButton("Refresh");
    	refreshBtn.setBounds(385, 211, 89, 23);


    	// action listener for the refresh button
    	refreshBtn.addActionListener(new ActionListener()
    	{
    		public void actionPerformed(ActionEvent e)
    		{		
    			//refreshing the table data
    			int rowCount = model.getRowCount();
    			//Remove rows one by one from the end of the table
    			for (int i = rowCount - 1; i >= 0; i--) {
    			    model.removeRow(i);   			
    			}
    			//Repopulates the table with the updated data
    			    			for(Object[] person : con.getAllPeopleData()){
    	    		model.addRow(person);
    	    	}
    			
    			//refreshing the dropdown menu data    			
    			String[] specList = con.getAllSpec();
    			dropDown.removeAllItems();
    			
    			for (int i = 0; i < specList.length; i++) {
    	            String temp = specList[i];
    	            dropDown.addItem(temp);
    	        }
    			
    		}	   
    	});
		
		this.add(refreshBtn);
		// ************************
    	// end REFRESH table button
    	// ************************
	
		table.getColumnModel().getColumn(0).setPreferredWidth(129);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		scrollPane.setViewportView(table);
		
		// show/hide table button
		JButton showHideBtn = new JButton("Show Table");
		showHideBtn.setBounds(10, 211, 120, 23);
		showHideBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (clicked)  
		         {  										
					scrollPane.setVisible(false);
					table.setVisible(false);
					showHideBtn.setText("Show Table");
					clicked = false; 
		         }  
		         else  
		         {  
		        	 scrollPane.setVisible(true);
		        	 table.setVisible(true);
		        	 showHideBtn.setText("Hide Table");
		        	 clicked = true; 
		         }  
		         
																				
			}
		});

		this.add(showHideBtn);
		
		
								
		
	}
	

}
