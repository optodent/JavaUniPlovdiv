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
	private String fakNum;

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
		
		
		
		JComboBox comboBox = new JComboBox(con.getAllFakNums());
		comboBox.setBounds(30, 47, 153, 20);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(con.getAllBookNames());
		comboBox_1.setBounds(30, 118, 153, 20);
		add(comboBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(337, 26, 124, 112);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(97, 87, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Book");
		lblNewLabel_2.setBounds(30, 90, 46, 14);
		add(lblNewLabel_2);
		
		
		
		JButton btnNewButton_1 = new JButton("Filter Books");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(193, 87, 105, 20);
		add(btnNewButton_1);
		
		
		
		
		
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
    	for(Object[] book : con.getBooksData()){
    		model.addRow(book);
    	}
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Get Student");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fakNum = (String) comboBox.getSelectedItem();
				lblNewLabel_1.setText(con.getPersonByFn(fakNum));
				
				int rowCount = model.getRowCount();
    			//Remove rows one by one from the end of the table
    			for (int i = rowCount - 1; i >= 0; i--) {
    			    model.removeRow(i);   			
    			}
    			//Repopulates the table with the updated data
    			    			for(Object[] person : con.getPersonBooks(fakNum)){
    	    		model.addRow(person);
    	    	}
				
			}
		});
		btnNewButton.setBounds(193, 19, 105, 48);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Add book");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				fakNum = (String) comboBox.getSelectedItem();
				int stuId = con.getPersonIdByFn(fakNum);
				String bookName = (String) comboBox_1.getSelectedItem();
				int bookId = con.getBookIdByName(bookName);
				
				con.addBookToPerson(stuId, bookId);
			}
		});
		btnNewButton_2.setBounds(193, 118, 105, 20);
		add(btnNewButton_2);

	}

}
