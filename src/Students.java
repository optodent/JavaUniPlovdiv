

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Students extends JPanel{
	
	DBConnect con;
	private JTextField fName;
	private JTextField lName;
	private JTextField fakNum;
	private JScrollPane scrollPane;
	private JTable table;
	
	//not done yet
	private JButton addPplBtn;

	
	public Students(DBConnect con){
		
								
		this.con = con;
		
		//visual stuff
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//dropdown men
		JComboBox<String> dropDown = new JComboBox<String>(con.getAllSpec());
		dropDown.setBounds(30, 129, 100, 20);
		dropDown.setVisible(true);
				
		
		this.add(dropDown);
		
		
		//first name text field
		JTextField fName = new JTextField();
		fName.setBounds(30, 36, 100, 20);
		fName.setColumns(10);
		fName.setVisible(true);				

		this.add(fName );
		
		//last name text field
		JTextField lName = new JTextField();
		lName.setBounds(30, 67, 100, 20);
		lName.setColumns(10);
		lName.setVisible(true);	
				
		this.add(lName );
		
		//fakulteten nomer text field
		JTextField fakNum = new JTextField();
		fakNum.setBounds(30, 98, 100, 20);
		fakNum.setColumns(10);
		fakNum.setVisible(true);		

		this.add(fakNum );
		
		//add people button
		addPplBtn = new JButton("Add Student");
		addPplBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addPplBtn.setBounds(138, 97, 89, 52);
		
		this.add(addPplBtn);
		
		// show/hide table button
		JButton showHideBtn = new JButton("Show Table");
		showHideBtn.setBounds(10, 211, 120, 23);
		this.add(showHideBtn);
		
		// refresh table button
		JButton refreshBtn = new JButton("Refresh");
		refreshBtn.setBounds(385, 211, 89, 23);
		this.add(refreshBtn);
		
		//text at top left
		JLabel lblNewLabel = new JLabel("Students");
		lblNewLabel.setBounds(10, 11, 93, 14);
		this.add(lblNewLabel);
		
		//adding a scrollpane with a table inside of it, containing the students data and books taken
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 245, 464, 206);
		this.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Student", "Fak. Number", "Speciality", "Drp down menu of books taken", "Taken date", "Return date"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(129);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		scrollPane.setViewportView(table);
		
		
		
		
		
		
		
	}
	

}
