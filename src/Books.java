import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Books extends JPanel {

	private DBConnect con; // field accessing and modifying database
	private String[] columnTableNames = { "Book ID", "Book Name", "Author","Quantity" }; //table column names
	private JScrollPane scrollPane; //scrolling panel for JTable
	private JLabel booksLabel = new JLabel("Books specification"); //books label
	private JLabel booksTitle = new JLabel("Current books in database!");
	private JPanel booksOption = new JPanel();
	private JTextField addBookName = new JTextField(20);
	private JTextField author = new JTextField(20);
	private JTextField quantity = new JTextField(20);

	public Books(DBConnect con) {

		this.con = con;	
		Object[][] data = con.getBooks(); // getting books from database 
		JTable table = new JTable(data, columnTableNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 80));
		this.add(booksTitle);
		this.scrollPane = new JScrollPane(table);
		this.scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);
		this.add(booksLabel);
		booksOption.add(booksLabel);
		booksOption.add(addBookName);
		booksOption.add(author);
		booksOption.add(quantity);
		booksOption.setLayout(new BoxLayout(booksOption, BoxLayout.Y_AXIS));
		this.add(booksOption);
		
		
	}

}
