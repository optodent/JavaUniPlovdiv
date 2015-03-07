import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class Specialities extends JPanel{
	private DBConnect con; // field accessing and modifying database
	private String[] columnTableNames = { "Spec ID", "Spec Name"}; // table column names
	private JScrollPane scrollPane; // scrolling panel for JTable
	private JLabel specTitle = new JLabel("Current spec's in database!");
	private JPanel specOption = new JPanel();
	private JTextField addSpecName = new JTextField(20);
	private JLabel addSpecLabel = new JLabel("Spec name", 10);
	private JButton addSpecButton = new JButton("Add spec");
	private JButton refreshSpecButton = new JButton("Refresh");
	private JButton removeSpecButton = new JButton("Remove");
	private JButton editSpecButton = new JButton("Update");
public Specialities(DBConnect con){
		this.con = con;
		Object[][] data = con.getBooks(); // getting books from database
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(data, columnTableNames));
		table.setPreferredScrollableViewportSize(new Dimension(450, 80));
		this.add(specTitle);
		this.scrollPane = new JScrollPane(table);
		this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
		this.add(specOption);
		specOption.setLayout(new GridBagLayout());
		this.initializeLayout();
		
		
		//Style Button
		Border line = new LineBorder(Color.BLUE);
		Border margin = new EmptyBorder(5, 15, 5, 15);
		Border compound = new CompoundBorder(line, margin);
		addSpecButton.setForeground(Color.BLACK);
		addSpecButton.setBorder(compound);
		editSpecButton.setForeground(Color.BLACK);
		editSpecButton.setBorder(compound);
		refreshSpecButton.setForeground(Color.BLACK);
		refreshSpecButton.setBorder(compound);
		removeSpecButton.setForeground(Color.BLACK);
		removeSpecButton.setBorder(compound);
		//end style
		
		addSpecButton.setSize(100, 100);
		//addSpecBtn.setLocation(150, 50);
		addSpecButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {	
				String specName = addSpecName.getText();

				Statement stmt = null;
				try {
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "INSERT INTO specialnosti (sname)"
							+ "VALUES ('" + specName + "')";
					stmt.execute(query);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//	    	  
	    	// if nothing is in the textfield do nothing, else add the text to the database as a specialnost
	    	if(addSpecName.getText().equals("")){
	    		;
	    	}else{
		        con.addSpec(addSpecName.getText());
		        addSpecName.setText("");
	    	}
	      }
	    });
		
		this.add(addSpecButton);
		this.add(addSpecName);
		
		refreshSpecButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int tableRows = model.getRowCount();

				for (int i = tableRows - 1; i >= 0; i--) {
					model.removeRow(i);
				}

				Object[][] dataSpec = con.getAllSpecPesho();
				for (Object[] spec : dataSpec) {
					model.addRow(spec);
				}
			}

		});
		removeSpecButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				String selected = model.getValueAt(table.getSelectedRow(), 0)
						.toString();
				model.removeRow(table.getSelectedRow());
				Statement stmt = null;
				try {
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "DELETE FROM specialnosti WHERE spec_id = " + selected;
					stmt.execute(query);

				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}

			}

		});
		editSpecButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int specID = (int) model.getValueAt(table.getSelectedRow(), 0);
				String specName = model.getValueAt(table.getSelectedRow(), 1).toString();
				
				Statement stmt = null;
				try {
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "UPDATE specialnosti SET sname='" + specName
							+ "'" + " WHERE spec_id = " + specID;
					stmt.execute(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
			
		});
		// end of specialnosti tab elements
	}
	private void initializeLayout() {
		specOption.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridwidth = 1;
		specOption.add(addSpecLabel, c);
		c.gridx = 0;
		c.gridy = 2;
		specOption.add(addSpecName, c);
		c.gridx = 0;
		c.gridy = 2;

		this.add(addSpecButton);
		this.add(refreshSpecButton);
		this.add(removeSpecButton);
		this.add(editSpecButton);
	}
}
