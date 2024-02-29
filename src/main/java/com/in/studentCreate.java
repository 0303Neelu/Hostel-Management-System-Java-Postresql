
package com.in;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class studentLogin
 */
public class studentCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentCreate() {
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
		
		String student_id=request.getParameter("emailId");
		int id=Integer.parseInt(student_id);
		
		HttpSession session=request.getSession();
		session.setAttribute("studentid", student_id);
		
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
        	String query="select students.student_id from students inner join student_login on students.student_id=student_login.student_id where students.student_id="+student_id+";";
        	st=connection.createStatement();
        	ResultSet rs=st.executeQuery(query);
        	if(rs.next()) {
 	    		
        		out.println("go");
        		 out.println("<html>");
         	    out.println("<head>");
         	out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
         	 out.println("</head>");
         	    out.println("</html>");
 				out.println("<script>");
 				out.println("Swal.fire({");
 				out.println("  title: \"Error!\",");
 				out.println("  text: \"Data Already exist in the database and if you want to change the password then click the below link !\",");
 				out.println("  icon: \"error\",");
 				out.println("  footer: '<a href=\"student_create_password.html\">click here to reset the password</a>'");
 				out.println("}).then(function() {");
 				out.println("  window.location.href = 'student_login_new.html';"); // Redirect after the alert is closed
 				out.println("});");
 				out.println("</script>");
 				out.println("hello\n");
 				
 				
 	    		rs.close();
 	        	st.close();
 	        	connection.close();
 	    		return;
 				
        		}
        	else {
        		String query1="select student_id from students where student_id="+student_id+";";
        		Statement st1=connection.createStatement();
        		ResultSet res1=st1.executeQuery(query1);
        		if(res1.next()) {
            		
            		
        			String query3="insert into student_login (student_id) values (?);";
            		PreparedStatement state=connection.prepareStatement(query3);
            		state.setInt(1,id);
            		state.executeUpdate();
            		response.sendRedirect("student_create_password.html");
            		state.close();
            		
            		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentCreatePassword");
            		dispatcher.forward(request, response);
            		
            		
            		res1.close();
            		rs.close();
                	st.close();
                	connection.close();
            		return;
            		
        		}
        		else {
        			out.println("<html>");
              	    out.println("<head>");
              	out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
              	 out.println("</head>");
              	    //out.println("<body>");
              	    out.println("</html>");
      				out.println("<script>");
      				out.println("Swal.fire({");
      				out.println("  title: \"Error!\",");
      				out.println("  text: \"Data Does not Exist !\",");
      				out.println("  icon: \"error\",");
      				out.println("}).then(function() {");
      				out.println("  window.location.href = 'student_login_new.html';"); // Redirect after the alert is closed
      				out.println("});");
      				out.println("</script>");
      				out.println("hello\n");
      				res1.close();
      				rs.close();
      	        	st.close();
      	        	connection.close();
      				return;
        		}
        		
        	}
        }catch(Exception e) {
        	e.printStackTrace();
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