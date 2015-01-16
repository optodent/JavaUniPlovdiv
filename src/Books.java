import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Books extends JPanel {

	DBConnect con; // field accessing and modifying database

	public Books(DBConnect con) {

		this.con = con;
		String[] columnTableNames = { "Book ID", "Book Name", "Author",
				"Quantity" };
		
		Object[][] data = con.getBooks();
		
		
		JTable table = new JTable(data, columnTableNames);
		table.setPreferredScrollableViewportSize(new Dimension(450, 80));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane);

	}

}
