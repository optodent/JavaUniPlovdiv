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
		Main main = new Main();

	}

}
