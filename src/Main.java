import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;



public class Main extends JFrame {
	
	
	private DBConnect connect;
	private String pass;
	
	public Main() {
		
		super();
		this.setSize(500, 550);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pass = JOptionPane.showInputDialog(this, "Enter password: ");
		
		// db connect object should be created in each class that would use it
		connect = new DBConnect(pass);
		
		Students students = new Students(connect);
		Books books = new Books(connect);
		Specialities specialities = new Specialities(connect);
		MainStuff mainstuff = new MainStuff(connect);	
		
		
		JTabbedPane tabs = new JTabbedPane();
		
		
		tabs.setBackground(Color.WHITE);//tabs background
		//Tab icons
		Icon icon1 = new ImageIcon(this.getClass().getResource("/images/book_stack.png"));
		Icon icon2 = new ImageIcon(this.getClass().getResource("/images/people.png"));
		Icon icon3 = new ImageIcon(this.getClass().getResource("/images/folder.png"));
		Icon icon4 = new ImageIcon(this.getClass().getResource("/images/pencil.png"));
		//end tab icons
		
		
		tabs.addTab("MainStuff",icon4, mainstuff);
		tabs.addTab("Students",icon2, students);
		tabs.addTab("Books",icon1, books);
		tabs.addTab("Specialities",icon3, specialities);
		this.add(tabs);
		tabs.getSelectedComponent().revalidate();
		//refresh the screen on load
		this.invalidate(); 
		this.validate();
		
		tabs.getSelectedComponent().revalidate();
		
	}

	public static void main(String[] args) {
		
		Main main = new Main();
		

	}

}
