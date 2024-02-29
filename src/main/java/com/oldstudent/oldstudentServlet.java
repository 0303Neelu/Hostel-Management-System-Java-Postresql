package com.oldstudent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class messDetailServlet
 */
public class oldstudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public oldstudentServlet() {
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
        	String query="select * from old_students";
        	st=connection.createStatement();
        	ResultSet res=st.executeQuery(query);
        	
        	out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Old Student Details</title>");
    		out.println("<link rel='stylesheet' type='text/css' href='styleMess.css'>");
    		out.println("</head>");
    		out.println("<body>");
    		
    			out.println("<div class='container'>");
    			out.println("<table>");
    			out.println("<tr>");
    			out.println("<th><h3>STUDENT ID</h3></th>");
    			out.println("<th><h3>STUDENT FIRST NAME</h3></th>");
    			out.println("<th><h3>STUDENT LAST NAME</h3></th>");
    			out.println("<th><h3>GENDER</h3></th>");
    			out.println("<th><h3>FATHER\'S NAME</h3></th>");
    			out.println("<th><h3>DATE OF BIRTH</h3></th>");
    			out.println("<th><h3>STUDENT\'S EMAIL ID</h3></th>");
    			out.println("<th><h3>BRANCH</h3></th>");
    			out.println("</tr>");
    		
        	while(res.next()) {
        		out.println("<tr>");
        		out.println("<td><h4>");
        			out.println(res.getInt(1));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(2));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(3));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(8));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(4));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(5));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(7));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(6));
        		out.println("</h4></td>");
        		
        		out.println("</tr>");
        	
        	}
        	out.println("</table>");
        	out.println("<center>");
        	out.println("<button><a href='DBMS.html'>GO BACK</a></button>");
        	out.println("</center>");
        	out.println("</div>");
        	out.println("</body>");
        	out.println("</html>");
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
