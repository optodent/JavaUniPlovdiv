import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBConnect {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://78.130.208.36/tutorials", "admin4o", "56745674");
			st = con.createStatement();
			
			
		}catch(Exception exe){
			System.out.println("Error" + exe);
		}
	}
	
	public void getData() {
		  try{
		   
		   String query = "select * from tutorials_tbl";
		   rs = st.executeQuery(query);
		   System.out.println("Records from Database");
		   while(rs.next()){
		    String title = rs.getString("tutorial_title");
		    String author = rs.getString("tutorial_author");
		    String date = rs.getString("submission_date");
		    
		    System.out.println("Title: " + title + "    "+"Name: "+author+"   "+"Date: "+date);
		    
		   }
		   
		  }catch(Exception ex){
		   System.out.println(ex);
		  }
		 }
}
