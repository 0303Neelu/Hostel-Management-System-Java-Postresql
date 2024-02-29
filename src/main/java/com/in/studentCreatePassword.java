package com.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Servlet implementation class studentCreatePassword
 */
public class studentCreatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentCreatePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String pass=request.getParameter("password");
		String conPass=request.getParameter("confirmpassword");
		
		if(pass.equals(conPass)) {
			Connection connection = null;
	        Statement st = null;
	        String host = "localhost";
	        String port = "5432";
	        String db_name = "DB_PROJECT";
	        String username = "postgres";
	        String password = "Neelu@0303";
	        
	        
	        try {
	        	Class.forName("org.postgresql.Driver");
	        	connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", username, password);
	        	
	        	if (connection != null) {
	                System.out.println("Connection OK");
	            } else {
	                System.out.println("Connection Not OK");
	            }
	        	HttpSession session=request.getSession();
	        	String stud=(String) session.getAttribute("studentid");
	        	int student_id=0;
	        	if(stud!=null) {
	        		student_id=Integer.parseInt(stud);
	        	}
	        	else {
	        		out.println("no string available");
	        	}
	        	
	        	String query="update student_login set password=? where student_id="+student_id+";";
	        	PreparedStatement state=connection.prepareStatement(query);
	        	state.setString(1, pass);
	        	state.executeUpdate();
	        	
	        	out.println("<html>");
	    		out.println("<head>");
	    		out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
				out.println("</head>");
				out.println("</html>");
	    		out.println("<script>");
				out.println("Swal.fire({");
				out.println("  title: \"Success!\",");
				out.println("  text: \"Password updated Successfully!\",");
				out.println("  icon: \"success\",");
				out.println("}).then(function() {");
				out.println("  window.location.href = 'student_login_old.html';"); // Redirect after the alert is closed
				out.println("});");
				out.println("</script>");
	        	
	        	state.close();
	        	connection.close();
	        	 
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
		}
		else {
			out.println("<html>");
    		out.println("<head>");
    		out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
			out.println("</head>");
			out.println("</html>");
    		out.println("<script>");
			out.println("Swal.fire({");
			out.println("  title: \"Error!\",");
			out.println("  text: \"Password and Confirm Password are Not same!\",");
			out.println("  icon: \"error\",");
			out.println("}).then(function() {");
			out.println("  window.location.href = 'student_create_password.html';"); // Redirect after the alert is closed
			out.println("});");
			out.println("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}