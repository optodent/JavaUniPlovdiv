import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
	
	// method to get all the specialities from the DB in the form of a string array
	public String[] getAllSpec(){
		try{
			// creating an arraylist to store the string results
			ArrayList<String> specList = new ArrayList<String>();
			
			// query to the db to get all the entries
			String query = "SELECT * from specialnosti";					       
		    
			rs = st.executeQuery(query);
			
			// iterating trough the db
			while (rs.next()){		   		        
		        String specName = rs.getString("sname");
		        specList.add(specName);		        
		    }
			
			// making a normal String array with the size of the ArrayList and putting the entries inside of it
			String[] specArr = new String[specList.size()];
			specArr = specList.toArray(specArr); 
			
			return specArr;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}
	
	// method for adding new specialnosti to the db
	public void addSpec(String spec){
		try{					
			String query =  " insert into specialnosti (sname)"
			        + " values (?)";
			PreparedStatement pst = con.prepareStatement(query);	
			pst.setString(1, spec);			
			
			pst.execute();
					
		}catch(Exception ex){
			System.out.println(ex);
		}
	}// end addSpec
	
	// method to add people to the database (try using a dropdown menu with specialnostite (in another method or this one??)
	public void addPerson(String fName, String lName, String fakNum, int spec){
		
		
		try{					
			String query =  " insert into person (fname, lname, faknum, specialnost)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query);	
			pst.setString(1, fName);
			pst.setString(2, lName);
			pst.setString(3, fakNum);
			pst.setInt(4, spec);
			pst.execute();
					
		}catch(Exception ex){
			System.out.println(ex);
		}
	}// end addPerson
		
	// method to add books into the db
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
	
	
	//return all books as a array
	public Object[][] getBooks(){
		
		ArrayList<Object[]> books = new ArrayList<Object[]>();
		try{
			String query = "select * from knigi";
			Statement statm = con.createStatement();
			ResultSet result = statm.executeQuery(query);
			while(result.next()){
				
				Object[] book = {result.getObject("id"), result.getObject("kname"), result.getObject("author"), result.getObject("qty")};
				books.add(book);
			}
			
		}catch(Exception ex){
			System.out.println("Error" + ex);
		}
		return books.toArray(new Object[0][0]);
	}
	
	// method for table creation (used only in development for easyer work)
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
	
	// method that returns data from the db
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
