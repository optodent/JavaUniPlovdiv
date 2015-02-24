import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Books extends JPanel {

	private DBConnect con; // field accessing and modifying database
	private String[] columnTableNames = { "Book ID", "Book Name", "Author","Quantity" }; //table column names
	private JScrollPane scrollPane; //scrolling panel for JTable
	private JLabel booksLabel = new JLabel("Books specification"); //books label
	private JLabel booksTitle = new JLabel("Current books in database!");
	private JPanel booksOption = new JPanel();
	private JTextField addBookName = new JTextField(35);
	private JTextField addAuthour = new JTextField(35);
	private JTextField addQuantity = new JTextField(35);
	private JLabel addBookLabel = new JLabel("Book name", 10);
	private JLabel addAuthorLabel = new JLabel("Author name", 10);
	private JLabel addQuantityLabel = new JLabel("Quantity ");
	private JButton addBookButton = new JButton("Add book");
	private JButton refreshBookButton = new JButton("Refresh");
	private JButton removeBookButton = new JButton("Remove");
	
	

	public Books(DBConnect con) {


		Object[][] data = con.getBooks(); // getting books from database 
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				data,columnTableNames));
		table.setPreferredScrollableViewportSize(new Dimension(450, 80));
		this.add(booksTitle);
		this.scrollPane = new JScrollPane(table);
		this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
		this.add(booksOption);
		booksOption.setLayout(new GridBagLayout());
		this.initializeLayout();	
		addBookButton.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String bookName = addBookName.getText();
				String authorBook = addAuthour.getText();
				String quantityBook = addQuantity.getText();
				
				Statement stmt = null;
				try {			
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "INSERT INTO knigi (kname, author, qty)" +
									"VALUES ('" + bookName+"'" +", " + "'"+authorBook+"', " + quantityBook+")";
					stmt.execute(query);
			
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
		});
		refreshBookButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int tableRows = model.getRowCount();
				
				for (int i = tableRows - 1; i >= 0; i--) {
    			    model.removeRow(i);   			
    			}
				
				Object[][] dataBooks = con.getBooks();
				for (Object[] book : dataBooks) {
					model.addRow(book);
				}
			}
			
		});
		removeBookButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model= (DefaultTableModel) table.getModel();
				String selected = model.getValueAt(table.getSelectedRow(), 0).toString();
				model.removeRow(table.getSelectedRow());
				
				Statement stmt = null;
				try {			
					Connection tempConnection = DBConnect.con;
					stmt = tempConnection.createStatement();
					String query = "DELETE FROM knigi WHERE id = "  + selected;					
					stmt.execute(query);
			
				} catch (SQLException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}			
					
			}
			
		});
	}
	
	private void initializeLayout(){
		booksOption.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		booksOption.add(booksLabel, c);
		c.gridwidth = 1;
		c.ipady = 0;
		c.gridx = 0;
		c.gridy = 1;
		booksOption.add(addBookLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		booksOption.add(addBookName, c);
		c.gridx = 0;
		c.gridy = 2;
		booksOption.add(addAuthorLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		booksOption.add(addAuthour, c);
		c.gridx = 0;
		c.gridy = 3;
		booksOption.add(addQuantityLabel, c);
		c.gridx = 1;
		c.gridy = 3;
		booksOption.add(addQuantity, c);
		this.add(addBookButton);
		this.add(refreshBookButton);
		this.add(removeBookButton);
	}
	
		
}
