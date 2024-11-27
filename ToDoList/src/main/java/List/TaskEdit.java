package List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@WebServlet("/TaskEdit")
public class TaskEdit extends HttpServlet  {

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("tname");
		String date = request.getParameter("tdate");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist?autoReconnect=true&useSSl=false","root","password");
            String query = "Update task set name=?,deadline=? where id=?";
    		  PreparedStatement pst = con.prepareStatement(query);
    		  pst.setString(1, name);
    		  pst.setString(2, date);
    		 
    		  pst.setInt(3, id);
    		  int count = pst.executeUpdate();
    		  if(count>0) {
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
     			 pw.println("<div class='container m-3'> <div class=\"alert alert-success container align-item-center m-2 alert-dismissible\">\r\n"
     			 		+ "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\"></button>\r\n"
     			 		+ "    <strong>Success!</strong> Record Updated Sucessfully.\r\n"
     			 		+ "  </div></div><br>");
     		 } else {
     			 pw.println(" <div class='container m-3'><div class=\"alert alert-danger container align-item-center m-2 alert-dismissible\">\r\n"
     			 		+ "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\"></button>\r\n"
     			 		+ "    <strong>Error!</strong>  Failed To Update Record..\r\n"
     			 		+ "  </div></div><br>");

     		 }
    		  
      		 String que = "Select * from task";
    		 PreparedStatement pt = con.prepareStatement(que);
             ResultSet re= pt.executeQuery();
             
    		    
    			 pw.println("<html><body><div class='container'>");
    			 pw.println("<div class='text-bold text-danger'><h3> All Tasks :</h3><br>");
    			 pw.println("<table class=' container table table-responsive table-bordered table-hover table-striped align-item-center table-light shadow-lg m-3 rounded-3'>");
    			 pw.println("<tr class='bg-secondary p-2 text-align-center'>");
    			 pw.println("<th>ID</th>");
    			 pw.println("<th>TASK</th>");
    			 pw.println("<th>DEADLINE</th>");
    			 pw.println("<th>CREATED ON</th>");
    			 pw.println("<th>EDIT</th>");
    			 pw.println("<th>DELETE</th>");
                 pw.println("</tr>");
    			 while(re.next()){
    				 pw.println("<tr>");
    				 pw.println("<td>"+re.getInt("id")+"</td>");
    	    			pw.println("<td>"+re.getString("name")+"</td>");
    	    			pw.println("<td>"+re.getString("deadline")+"</td>");
    	    			pw.println("<td>"+re.getString("created_on")+"</td>");
    	    			pw.println("<td><button class='btn btn-success p-1 rounded-2 text-white text-decoration-none text-bold'><a href='EditList?id="+re.getInt("id")+"' style='text-decoration:none'>Edit Task</a></button></td>");
    	    			pw.println("<td><button class=' btn btn-danger p-1 rounded-2  text-white text-decoration-none text-bold'><a href='DeleteTask?id="+re.getInt("id")+"'style='text-decoration:none'>Delete</a></button></td>");
                        pw.println("</div></body></html>");
    		     }

		
		}catch(Exception e) {
			e.printStackTrace();
		}


	}
 

}



