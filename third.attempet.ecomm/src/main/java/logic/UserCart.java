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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;




@WebServlet("/UserCart")
public class UserCart extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String userName=(String)session.getAttribute("userName");
		List<CartProductsBean>  items=new ArrayList<>();
		Connection con=DBConnection.getConnection();
		String query="SELECT * from usercart where username=?";
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(query);
			pstm.setString(1,userName);
			
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				CartProductsBean item=new CartProductsBean(rs.getString("product_name"),rs.getInt("product_price"));
				items.add(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(items);

        res.getWriter().print(json);
		
		
			
		}
		
		
		
		
		
	}

	


