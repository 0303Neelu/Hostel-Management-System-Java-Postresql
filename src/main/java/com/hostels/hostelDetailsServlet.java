package com.hostels;

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
public class hostelDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hostelDetailsServlet() {
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
		int count=1;
		
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
        	String query="select hostels.hostel_name,hostels.hostel_address,block_details.* from hostels inner join block_details on hostels.hostel_id=block_details.hostel_id order by hostels.hostel_id;";
        	st=connection.createStatement();
        	ResultSet res=st.executeQuery(query);
        	
        	out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Hostel Details</title>");
    		out.println("<link rel='stylesheet' type='text/css' href='styleMess.css'>");
    		out.println("</head>");
    		out.println("<body>");
    		
    			out.println("<div class='container'>");
    			out.println("<table>");
    			out.println("<tr>");
    			out.println("<th><h3>HOSTEL ID</h3></th>");
    			out.println("<th><h3>HOSTEL NAME</h3></th>");
    			out.println("<th><h3>STUDENTS YEAR</h3></th>");
    			out.println("<th><h3>HOSTEL TYPE</h3></th>");
    			out.println("<th><h3>RESIDENT COUNCELLOR NAME</h3></th>");
    			out.println("<th><h3>NO OF ROOMS</h3></th>");
    			out.println("<th><h3>NO OF FLOORS</h3></th>");
    			out.println("<th><h3>HOSTEL ADDRESS</h3></th>");
    			out.println("<th><h3>INDIVIDUAL LINKS TO ALL HOSTELS</h3></th>");
    			out.println("</tr>");
    		
        	while(res.next()) {
        		out.println("<tr>");
        		out.println("<td><h4>");
        			out.println(res.getInt(3));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(1));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(7));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(8));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(4));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getInt(5));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getInt(6));
        		out.println("</h4></td>");
        		
        		out.println("<td><h4>");
        		out.println(res.getString(2));
        		out.println("</h4></td>");

            	out.println("<td><h4><a href='link"+count+"'>Click Here!!</a></h4></td>");
            	count++;

        		out.println("</tr>");
        	
        	}
        	
        	
        	out.println("</table>");
        	out.println("</div>");
        	out.println("<center><button><a href='DBMS.html'>GO BACK</a></button></center>");
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
