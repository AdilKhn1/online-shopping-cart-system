package logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class UserRegis extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String userName=req.getParameter("username");
		String userPassword=req.getParameter("password");
		
		Connection con=DBConnection.getConnection();
		
		String query="INSERT INTO usercredential(username,password) values(?,?)";
		
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, userName);
			pstm.setString(2,userPassword);
			
			pstm.executeUpdate();
			
			res.sendRedirect("login.html");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
