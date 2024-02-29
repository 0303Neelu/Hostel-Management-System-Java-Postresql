package com.login;

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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class loginServlet
 */
public class studentLoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentLoginCheck() {
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
		
		String pass=request.getParameter("password");
		
		HttpSession session=request.getSession();
		session.setAttribute("studentsid", stud);
		
		
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
        	String query="select * from student_login where student_id="+student_id+" and password='"+pass+"';";
        	st=connection.createStatement();
        	ResultSet rs=st.executeQuery(query);
        	if(rs.next()) {
        
        		out.println("<html>");
          	    out.println("<head>");
          	out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
          	 out.println("</head>");
          	    //out.println("<body>");
          	    out.println("</html>");
  				out.println("<script>");
  				out.println("Swal.fire({");
  				out.println("  title: \"Success!\",");
  				out.println("  text: \"Successfully Loged In !\",");
  				out.println("  icon: \"success\",");
  				out.println("}).then(function() {");
  				out.println("  window.location.href = 'StudentDetailsIndi';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
  				out.println("hello\n");
  				
  				//RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentDetailsIndi");
  	    		//dispatcher.forward(request, response);
  				
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
  				out.println("  text: \"Student Id or password wrong!\",");
  				out.println("  icon: \"error\",");
  				out.println("}).then(function() {");
  				out.println("  window.location.href = 'student_login_old.html';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
  				out.println("hello\n");
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
