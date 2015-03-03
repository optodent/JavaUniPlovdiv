import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;



public class Main extends JFrame {
	
	
	private DBConnect connect;	
	
	public Main() {
		
		super();
		this.setSize(500, 500);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// db connect object should be created in each class that would use it
		connect = new DBConnect();
		
		Students students = new Students(connect);
		Books books = new Books(connect);
		Specialities specialities = new Specialities(connect);
		MainStuff mainstuff = new MainStuff(connect);	
		
		JTabbedPane tabs = new JTabbedPane();
		Icon icon1 = new ImageIcon(this.getClass().getResource("/images/book_stack.png"));
		Icon icon2 = new ImageIcon(this.getClass().getResource("/images/people.png"));
		Icon icon3 = new ImageIcon(this.getClass().getResource("/images/folder.png"));
		Icon icon4 = new ImageIcon(this.getClass().getResource("/images/pencil.png"));
		tabs.addTab("MainStuff",icon4, mainstuff);
		tabs.addTab("Students",icon2, students);
		tabs.addTab("Books",icon1, books);
		tabs.addTab("Specialities",icon3, specialities);
		this.add(tabs);
		//refresh the screen on load
		this.invalidate(); 
		this.validate();
		
	}

	public static void main(String[] args) {
		
		Main main = new Main();

	}

}
