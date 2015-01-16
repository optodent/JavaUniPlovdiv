import javax.swing.JFrame;
import javax.swing.JTabbedPane;



public class Main extends JFrame {
	
	
	private DBConnect connect;	
	
	public Main() {
		
		super();
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// db connect object should be created in each class that would use it
		connect = new DBConnect();
		
		Students students = new Students(connect);
		Books books = new Books(connect);
		Specialities specialities = new Specialities(connect);
			
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.addTab("Students", students);
		tabs.addTab("Books", books);
		tabs.addTab("Specialities", specialities);
		this.add(tabs);
		//refresh the screen on load
		this.invalidate(); 
		this.validate();
		
	}

	public static void main(String[] args) {
		
		Main main = new Main();

	}

}
