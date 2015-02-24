import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class DBConnect {
	
	public static Connection con;
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
	
	public void addBookToPerson(int person_fk, int kniga_fk){
		
		
		try{
			
			String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			
			Calendar date = Calendar.getInstance();
			date.add(Calendar.MONTH, 6);
			String returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime());
			
			String query =  " insert into inforeg (person_fk, kniga_fk, vzeta, za_vrushtane)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);	
			pst.setInt(1, person_fk);
			pst.setInt(2, kniga_fk);
			pst.setString(3, currentDate);
			pst.setString(4, returnDate);
			
			pst.execute();
			
					
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public int getPersonIdByFn(String fn){
		try{

			int one = 0;
			// query to the db to get all the entries
			String query = "SELECT p.id"
						+ " from person p"
						+ " join specialnosti s"
						+ " on p.spec_fk = s.spec_id"
						+ " where p.faknum = " + fn;					       
		    
			rs = st.executeQuery(query);
			
			while (rs.next()){		   		        
				one = rs.getInt("id");			    		        
			}
															
			return one;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return 0;
		
	}
	
	public int getBookIdByName(String name){
		try{

			int one = 0;
			// query to the db to get all the entries
			String query = "SELECT id"
						+ " from knigi"				
						+ " where kname = '" + name + "'";					       
		    
			rs = st.executeQuery(query);
			
			while (rs.next()){		   		        
				one = rs.getInt("id");			    		        
			}
															
			return one;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return 0;
	}
	
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
	
	// get all fak nums
	public String[] getAllFakNums(){
		try{
			// creating an arraylist to store the string results
			ArrayList<String> fakList = new ArrayList<String>();
			
			// query to the db to get all the entries
			String query = "SELECT faknum from person";					       
		    
			rs = st.executeQuery(query);
			
			// iterating trough the db
			while (rs.next()){		   		        
		        String fakNum = rs.getString("faknum");
		        fakList.add(fakNum);		        
		    }
			
			// making a normal String array with the size of the ArrayList and putting the entries inside of it
			String[] fakArr = new String[fakList.size()];
			fakArr = fakList.toArray(fakArr); 
			
			return fakArr;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}
	// get books taken information for specific student
	public Object[][] getPersonBooks(String fn){
		
		ArrayList<Object[]> books = new ArrayList<Object[]>();
		try{
			String query = "SELECT k.kname, k.author, i.vzeta, i.za_vrushtane"
					+ " from knigi k"
					+ " join inforeg i"
					+ " on k.id = i.kniga_fk"
					+ " join person p"
					+ " on p.id = i.person_fk"
					+ " where p.faknum = " + fn;	
			
			Statement statm = con.createStatement();
			ResultSet result = statm.executeQuery(query);
			while(result.next()){
				
				Object[] book = {result.getObject("kname"), result.getObject("author"), result.getObject("vzeta"), result.getObject("za_vrushtane")};
				books.add(book);
			}
			
		}catch(Exception ex){
			System.out.println("Error" + ex);
		}
		return books.toArray(new Object[0][0]);
		
	}
	
	// get specific person information based on FakNum string
	public String getPersonByFn(String fn){
		try{

			String allData = null;
			
			// query to the db to get all the entries
			String query = "SELECT p.fname, p.lname, p.faknum, s.sname"
						+ " from person p"
						+ " join specialnosti s"
						+ " on p.spec_fk = s.spec_id"
						+ " where p.faknum = " + fn;					       
		    
			rs = st.executeQuery(query);
			
			while (rs.next()){		   		        
				String one = rs.getString("fname");
			    String two = rs.getString("lname");
				String three = rs.getString("faknum");
		        String four = rs.getString("sname");
		        
		        allData = "<html>" +one + " " +two+ "<br>" +three+ "<br>" + four + "</html>";		        
		    }
			
			
									
			return allData;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
		
	}
	
	
	// get all book names
	public String[] getAllBookNames(){
		try{
			// creating an arraylist to store the string results
			ArrayList<String> bookList = new ArrayList<String>();
			
			// query to the db to get all the entries
			String query = "SELECT kname from knigi";					       
		    
			rs = st.executeQuery(query);
			
			// iterating trough the db
			while (rs.next()){		   		        
		        String book = rs.getString("kname");
		        bookList.add(book);		        
		    }
			
			// making a normal String array with the size of the ArrayList and putting the entries inside of it
			String[] bookArr = new String[bookList.size()];
			bookArr = bookList.toArray(bookArr); 
			
			return bookArr;	
			
		}catch(Exception ex){
			System.out.println(ex);
		}
		return null;
	}
	
	// get all data for mainStuff books
	public Object[][] getBooksData(){
		
		ArrayList<Object[]> books = new ArrayList<Object[]>();
		try{
			String query = "select k.kname, k.author, i.vzeta, i.za_vrushtane"
						+ " from knigi k"
						+ " left join inforeg i"
						+ " on k.id = i.kniga_fk";
			Statement statm = con.createStatement();
			ResultSet result = statm.executeQuery(query);
			while(result.next()){
				
				Object[] book = {result.getObject("kname"), result.getObject("author"), result.getObject("vzeta"), result.getObject("za_vrushtane")};
				books.add(book);
			}
			
		}catch(Exception ex){
			System.out.println("Error" + ex);
		}
		return books.toArray(new Object[0][0]);
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
	
	//****************
	//*PERSON METHODS*
	//****************
	
	// method to add people to the database (try using a dropdown menu with specialnostite (in another method or this one??)
	public void addPerson(String fName, String lName, String fakNum, int spec){
		
				
		try{					
			String query =  " insert into person (fname, lname, faknum, spec_fk)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);	
			pst.setString(1, fName);
			pst.setString(2, lName);
			pst.setString(3, fakNum);
			pst.setInt(4, spec);
			
			pst.execute();
			
					
		}catch(Exception ex){
			System.out.println(ex);
		}
	}// end addPerson
	
	//tries to get string data from a few tables and returns an ArrayList of Object[] to populate the Students table with.
	public ArrayList<Object[]> getAllPeopleData(){
		String specName = "test";
		ArrayList<Object[]> people = new ArrayList<Object[]>();
		try{
			String queryPeople = "select * from person";
			Statement stPeople = con.createStatement();
			ResultSet rsPeople = stPeople.executeQuery(queryPeople);			
												
			while(rsPeople.next()){
				
				String fullName = (String) rsPeople.getObject("fname") + " " + rsPeople.getObject("lname");
				int spec = (Integer) rsPeople.getObject("spec_fk");
				String specStr = Integer.toString(spec);
				
				// next few lines get the speciality associated with the primary key we get from the foreign key entered in the person table
				String querySpec = "SELECT * FROM specialnosti WHERE spec_id=" + specStr;
				Statement stSpec = con.createStatement();
				ResultSet rsSpec = stSpec.executeQuery(querySpec);
				
				while(rsSpec.next()){
					specName = (String) rsSpec.getObject("sname");
				}
				
				Object[] person = {fullName , rsPeople.getObject("faknum"),						  
							specName,
						   "", ""};
				
				people.add(person);
			}
			
		}catch(Exception ex){
			System.out.println("Error" + ex);
		}
		return people;
	}
	
	
	// *********************
	// *End PERSON METHODS*
	// *********************
	
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
	
	// method for table creation (used only in development for easier work)
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
	                   " PRIMARY KEY ( id ))";
			
			String query = "CREATE TABLE specialnosti" +	                 
	                   " (sname VARCHAR(20) NOT NULL, " + 
	                   " spec_fk INT, " +
	                   " FOREIGN KEY ( spec_fk ) REFERENCES person(id))";
	                    
			
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
