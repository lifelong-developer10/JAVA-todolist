package List;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditList
 */
@WebServlet("/EditList")
public class EditList extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();	
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("tname");
		String date = request.getParameter("tdate");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist?autoReconnect=true&useSSl=false","root","password");
            String query = "SELECT * FROM TASK WHERE ID=?";
    		  PreparedStatement pst = con.prepareStatement(query);
    		  pst.setInt(1, id);
    		  ResultSet res= pst.executeQuery();
        	res.next();
        	 pw.println("<html> <head>\r\n"
 			 		+ "    <!-- Required meta tags -->\r\n"
 			 		+ "    <meta charset=\"utf-8\">\r\n"
 			 		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
 			 		+ "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
 			 		+ "\r\n"
 			 		+ "    <!-- Bootstrap CSS -->\r\n"
 			 		+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
 			 			+ "    <title>To Do List</title>\r\n"
 			 		+ "  </head><body>");
        		pw.println("<form action='TaskEdit?id="+id+"' method='post'");
  		    pw.println("<table>");
  		    
  		  pw.println("<table class=' container table table-responsive table-bordered table-hover form table-striped align-item-center table-light shadow-lg m-3 rounded-3'>");
			 pw.println("<tr>");
  			pw.println("<td>Task :</td>");
  			pw.println("<td><input type='text' class='form-control border-2 ' name='tname' value='"+res.getString("name")+"'</td>");
              pw.println("</tr>");
              pw.println("<tr>");
  			pw.println("<td>Deadline :</td>");

  			pw.println("<td><input type='date' class='form-control border-2 ' name='tdate' value='"+res.getString("deadline")+"'</td>");
  			pw.println("</tr>");
  			pw.println("<tr>");
  			pw.println("<td><button class='btn btn-success p-1 rounded-2 text-white text-decoration-none text-bold' type='submit' >Edit</button></td>");
  			pw.println("<td><button class='btn btn-danger p-1 rounded-2 text-white text-decoration-none text-bold' type='reset'>Cancel</button></td>");

  			pw.println("</tr>");


  		
  			pw.println("</table>");
  			pw.println("</form>");
            

    		  
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
