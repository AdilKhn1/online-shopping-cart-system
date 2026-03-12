package logic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/AddToCart")
public class AddToCart  extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int productId=Integer.parseInt(req.getParameter("product_id"));
		
		HttpSession session=req.getSession();
		String userName=(String)session.getAttribute("userName");
		
		Connection con=DBConnection.getConnection();
		
	    String query="Select * from homepageproducts where product_id=?";
	    
	    PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(query);
			 pstm.setInt(1,productId);
			    
			    ResultSet rs=pstm.executeQuery();
			    rs.next();
			    
			    String query1="INSERT into usercart(username,product_name,product_price) values(?,?,?)";
			    PreparedStatement pstm1=con.prepareStatement(query1);
			    
			    pstm1.setString(1, userName);
			    pstm1.setString(2,rs.getString("product_name"));
			    pstm1.setInt(3, rs.getInt("product_price"));
			    
			    pstm1.executeUpdate();
			    
			    res.getWriter().print("Product added to cart");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			 res.getWriter().print("Product not added to cart");
		}
	    
	   
	    
	    
		
		
		
		
		
		
		
	}

}
