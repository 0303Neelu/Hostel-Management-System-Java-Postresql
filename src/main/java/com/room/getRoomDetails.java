package com.room;

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
public class getRoomDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRoomDetails() {
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
		
		String hostel_name=request.getParameter("hostel");
		String room=request.getParameter("room");
		int room_no=Integer.parseInt(room);
		
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
        	String query="select students.student_id,students.first_name,students.last_name,students.gender,students.branch,students.year from students inner join hostels on students.hostel_id=hostels.hostel_id where students.room_no="+room_no+" and hostels.hostel_name='"+hostel_name+"';";
        	st=connection.createStatement();
        	ResultSet res=st.executeQuery(query);
        	
        	out.println("<html>");
    		out.println("<head>");
    		out.println("<title>Mess Details</title>");
    		out.println("<link rel='stylesheet' type='text/css' href='styleMess.css'>");
    		out.println("</head>");
    		out.println("<body>");
    		
    			out.println("<div class='container'>");
    			out.println("<table>");
    			out.println("<tr>");
    			out.println("<th><h3>STUDENT ID</h3></th>");
    			out.println("<th><h3>FIRST NAME</h3></th>");
    			out.println("<th><h3>LAST NAME</h3></th>");
    			out.println("<th><h3>GENDER</h3></th>");
    			out.println("<th><h3>BRANCH</h3></th>");
    			out.println("<th><h3>YEAR</h3></th>");
    			out.println("</tr>");
    		
	        	if(res.next()) {
	    			do {
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
	        		out.println(res.getString(4));
	        		out.println("</h4></td>");
	        		
	        		out.println("<td><h4>");
	        		out.println(res.getString(5));
	        		out.println("</h4></td>");
	        		
	        		
	        		out.println("<td><h4>");
	        		out.println(res.getString(6));
	        		out.println("</h4></td>");
	        		
	        		out.println("</tr>");
	        	
	        	}while(res.next());
	        }else {
	        	out.println("<html>");
	    		out.println("<head>");
	    		out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
				out.println("</head>");
				out.println("</html>");
	    		out.println("<script>");
				out.println("Swal.fire({");
				out.println("  title: \"Error!\",");
				out.println("  text: \"Hostel or Room no does not exist!\",");
				out.println("  icon: \"error\",");
				out.println("}).then(function() {");
				out.println("  window.location.href = 'SearchRoom.html';"); // Redirect after the alert is closed
				out.println("});");
				out.println("</script>");
	        }
        	out.println("</table>");
        	out.println("</div>");
        	out.println("<center><button><a href='SearchRoom.html'>GO BACK</a></button></center>");
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
