import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;


public class MainStuff extends JPanel {
	DBConnect con;
	private JTextField textField;
	private JTextField textField_1;
	private String studentName, studentFn;

	/**
	 * Create the panel.
	 */
	public MainStuff(DBConnect con) {
		
		this.con = con;
		
		
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fak. Num");
		lblNewLabel.setBounds(30, 19, 46, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(97, 16, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		
		
		JComboBox studentNames = new JComboBox(con.getAllStudentNames());
		studentNames.setBounds(30, 47, 153, 20);
		add(studentNames);
		
		JComboBox bookNames = new JComboBox(con.getAllBookNames());
		bookNames.setBounds(30, 118, 153, 20);
		add(bookNames);
		
		JLabel studentInfoLabel = new JLabel("");
		studentInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		studentInfoLabel.setBounds(337, 26, 124, 112);
		add(studentInfoLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 87, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book");
		lblNewLabel_2.setBounds(30, 90, 46, 14);
		add(lblNewLabel_2);
		
		
		
		JButton filterBooksBtn = new JButton("Filter Books");
		filterBooksBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		filterBooksBtn.setBounds(193, 87, 105, 20);
		add(filterBooksBtn);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 203, 480, 227);
		add(scrollPane);
		
		Object[][] data = con.getBooksData();
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
				
			},
			new String[] {
				"Book Name", "Book Author", "Taken date", "Return date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(129);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		
		DefaultTableModel model=(DefaultTableModel) table.getModel();
    	/*for(Object[] book : con.getBooksData()){
    		model.addRow(book);
    	}*/
		scrollPane.setViewportView(table);
		
		JButton getStudentBtn = new JButton("Get Student");
		getStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentName = (String) studentNames.getSelectedItem();
				studentInfoLabel.setText(con.getPersonByName(studentName));
				
				int rowCount = model.getRowCount();
    			//Remove rows one by one from the end of the table
    			for (int i = rowCount - 1; i >= 0; i--) {
    			    model.removeRow(i);   			
    			}
    			//Repopulates the table with the updated data
    			    			for(Object[] person : con.getPersonBooks(studentName)){
    	    		model.addRow(person);
    	    	}
				
			}
		});
		getStudentBtn.setBounds(193, 47, 105, 20);
		add(getStudentBtn);
		
		JButton addBookBtn = new JButton("Add book");
		addBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//studentFn = (String) studentNames.getSelectedItem();
				//int stuId = con.getPersonIdByFn(fakNum);
				String bookName = (String) bookNames.getSelectedItem();
				int bookId = con.getBookIdByName(bookName);
				
				//con.addBookToPerson(stuId, bookId);
			}
		});
		addBookBtn.setBounds(193, 118, 105, 20);
		add(addBookBtn);
		
		JButton filterStudentsBtn = new JButton("Filter Students");
		filterStudentsBtn.setBounds(193, 15, 105, 23);
		add(filterStudentsBtn);

	}

}
