package logic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


@WebServlet("/products")
public class HomePageLoader extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=DBConnection.getConnection();
		
		
		List<ProductsBean> items=new ArrayList<>();
		String query="SELECT * From homepageproducts";
		
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(query);
			ResultSet rs=pstm.executeQuery();
		    
		    while(rs.next()) {
		    	ProductsBean item=new ProductsBean(
		    			 rs.getInt("product_id"),
	                     rs.getString("product_name"),
	                     rs.getInt("product_price")
		    			);
		    	
		    	items.add(item);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	    
	    
	    
	    // 🔑 Send JSON to frontend
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        String json = gson.toJson(items);

        response.getWriter().print(json);
		
		
	}

	
}
