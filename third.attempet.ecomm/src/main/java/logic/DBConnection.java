package logic;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
	static Connection con;
	
	public static Connection getConnection() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user="root";
			String password="@Khalilabad12";
			String url="jdbc:mysql://localhost:3306/thirdAttempet";
			con=DriverManager.getConnection(url,user,password);
		
			
			
		}
		catch(Exception e){
			
			
			e.printStackTrace();
			
		}
		return con;
		
		
		
		
		
		
		
		
	}

}