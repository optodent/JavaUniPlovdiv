import javax.swing.JComboBox;
import javax.swing.JPanel;


public class Students extends JPanel{
	
	DBConnect con;
	
	public Students(DBConnect con){
		this.con = con;
		JComboBox<String> dropDown = new JComboBox<String>(con.getAllSpec());
		dropDown.setVisible(true);
		dropDown.setSize(100, 100);
		this.add(dropDown);
	}

}
