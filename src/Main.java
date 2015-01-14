import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class Main extends JFrame {

	public Main() {

		super();
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel students = new JPanel();
		JPanel books = new JPanel();
		JPanel specialities = new JPanel();
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Students", students);
		tabs.addTab("Books", books);
		tabs.addTab("Specialities", specialities);
		
		this.add(tabs);
	}

	public static void main(String[] args) {
		DBConnect connect = new DBConnect();
<<<<<<< HEAD
		// connect.setTables();
		// connect.addBook("topki", 33);
		connect.getData();
=======
		Main main = new Main();

>>>>>>> 105bcf4252457355aa843e5ff89d6ebb626a4fd9
	}

}
