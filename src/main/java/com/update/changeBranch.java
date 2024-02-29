package com.update;

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
import java.sql.Statement;

/**
 * Servlet implementation class changeFname
 */

public class changeBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeBranch() {
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
		
		String stud=request.getParameter("sid");
		int student_id=Integer.parseInt(stud);
		
		String branch=request.getParameter("branch");
		
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
        	String query="select student_id from students where student_id="+student_id+";";
        	st=connection.createStatement();
        	ResultSet rs=st.executeQuery(query);
        	
        	if(rs.next()) {
        		String query1="update students set branch=? where student_id="+student_id+";";
        		PreparedStatement state=connection.prepareStatement(query1);
        		state.setString(1, branch);
        		state.executeUpdate();
        		
        		out.println("<html>");
          	    out.println("<head>");
          	out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
          	 out.println("</head>");
          	    //out.println("<body>");
          	    out.println("</html>");
  				out.println("<script>");
  				out.println("Swal.fire({");
  				out.println("  title: \"Success!\",");
  				out.println("  text: \"Branch Updated Successfully!\",");
  				out.println("  icon: \"success\",");
  				out.println("}).then(function() {");
  				out.println("  window.location.href = 'DBMS3.html';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
  				state.close();
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
  				out.println("  window.location.href = 'branch.html';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
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
