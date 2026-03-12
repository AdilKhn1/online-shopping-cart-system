package logic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/LoginChecker")
public class LoginChecker extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con=DBConnection.getConnection();
		String password=req.getParameter("password");
		String userName=req.getParameter("username");
		
		
		String query="Select username from usercredential where password=? and username=?";
		
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1, password);
			pstm.setString(2, userName);
			ResultSet rs=pstm.executeQuery();
			
			if(rs.next()) {
				HttpSession session=req.getSession();
				session.setAttribute("userName",userName);
				res.sendRedirect("homepage.html");
				}
			else {
				res.sendRedirect("register.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
