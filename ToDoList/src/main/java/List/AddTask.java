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
 * Servlet implementation class AddTask
 */
@WebServlet(urlPatterns="/AddTask")
public class AddTask extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String name = request.getParameter("tname");
	 String date = request.getParameter("tdate");
	 PrintWriter pw = response.getWriter();
	 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 String query = "Insert into task(name,deadline) values (?,?)";
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","password");
		 PreparedStatement pst = con.prepareStatement(query);
		 pst.setString(1, name);
		 pst.setString(2, date);
		 int res = pst.executeUpdate();
		 String que = "Select * from task";
		 PreparedStatement pt = con.prepareStatement(que);
         ResultSet re= pt.executeQuery();
		 if(res>0) {
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
			 		+ "    <strong>Success!</strong> Task Added Sucessfully.\r\n"
			 		+ "  </div></div><br>");
		 } else {
			 pw.println(" <div class='container m-3'><div class=\"alert alert-danger container align-item-center m-2 alert-dismissible\">\r\n"
			 		+ "    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\"></button>\r\n"
			 		+ "    <strong>Error!</strong>  Failed to add the task..\r\n"
			 		+ "  </div></div><br>");

		 }
		    
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
	    			pw.println("<td><a href='EditList?id=" + re.getInt("id") + "' class='btn btn-success p-1 rounded-2 text-white text-decoration-none text-bold'>Edit Task</a></td>");
	    			pw.println("<td><a href='DeleteTask?id=" + re.getInt("id") + "' class='btn btn-danger p-1 rounded-2 text-white text-decoration-none text-bold'>Delete</a></td>");
	    			pw.println("</div></body></html>");

		     }
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	}

}