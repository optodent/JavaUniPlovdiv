import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	}// end DBConnect
	
		
	// metod za dobavqne na kniga koito priema String ime na knigata i koli4estvo na knigata
	public void addBook(String ime, int qty) {
		
		try{					
			String query =  " insert into knigi (kname, qty)"
			        + " values (?, ?)";
			PreparedStatement pst = con.prepareStatement(query);	
			pst.setString(1, ime);
			pst.setInt(2, qty);
			
			pst.execute();
					
		}catch(Exception ex){
			System.out.println(ex);
		}
	}// end addBook
	
	// metod za suzdavane na tablicite
	public void setTables() {
		try{
			String query3 = "CREATE TABLE inforeg" +
		                   "(id INTEGER not NULL AUTO_INCREMENT, " +
		                   " personid INT , " + 
		                   " knigaid INT, " +
		                   " vzeta DATE, " +
		                   " vurnata DATE, " +
		                   " FOREIGN KEY ( personid ) REFERENCES person(id), " +
		                   " FOREIGN KEY ( knigaid ) REFERENCES knigi(id), " +
		                   " PRIMARY KEY ( id ))";
			
			String query1 = "CREATE TABLE person" +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " fname VARCHAR(20) NOT NULL, " + 
	                   " lname VARCHAR(20) NOT NULL, " +
	                   " faknum VARCHAR(10) NOT NULL, " +
	                   " specialnost INT, " +
	                   " FOREIGN KEY ( specialnost ) REFERENCES specialnosti(id), " +	                
	                   " PRIMARY KEY ( id ))";
			
			String query = "CREATE TABLE specialnosti" +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " sname VARCHAR(20) NOT NULL, " + 	                   
	                   " PRIMARY KEY ( id ))";
			
			String query2 = "CREATE TABLE knigi" +
	                   "(id INTEGER not NULL AUTO_INCREMENT, " +
	                   " kname VARCHAR(30) NOT NULL, " + 
	                   " qty INT NOT NULL, " +	                  	                  	                   
	                   " PRIMARY KEY ( id ))";
	                   
			st.execute(query);
			st.execute(query1);
			st.execute(query2);
			st.execute(query3);
			
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		
	}// end setTables
	
	// metod za 4etene na informaciq
	public void getData() {
		try{
			
			String query = "select * from knigi";
			rs = st.executeQuery(query);
			System.out.println("Records from Database");
			while(rs.next()){
				String name = rs.getString("kname");
				int qty = rs.getInt("qty");
				
				
				System.out.println("Bla: "+ name + qty);
				
			}
			
		}catch(Exception ex){
			System.out.println(ex);
		}
	}// end getData()
}// end class
