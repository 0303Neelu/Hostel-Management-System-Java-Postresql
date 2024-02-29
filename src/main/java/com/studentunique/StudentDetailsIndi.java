package com.studentunique;

import jakarta.servlet.RequestDispatcher;
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

import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class studentServlet
 */
public class StudentDetailsIndi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDetailsIndi() {
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
		
		jakarta.servlet.http.HttpSession session=request.getSession();
    	String stud=(String) session.getAttribute("studentsid");
    	
    	int student_id=0;
    	if(stud!=null) {
    		student_id=Integer.parseInt(stud);
    	}
    	else {
    		out.println("cannot parse");
    	}
    	
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
        	
        	String query="select * from students where student_id="+student_id+";";
        	st=connection.createStatement();
        	ResultSet rs=st.executeQuery(query);
        	
        	if(rs.next()) {
        		out.println("<html>");
        		out.println("<head>");
        		out.println("<meta charset=\"UTF-8\">\r\n"
        		        + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        		out.println("<title>student_details</title>");
        		out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
        		out.println("</head>");
        		out.println("<body>");
        		jakarta.servlet.http.HttpSession session1 = request.getSession();
        		session1.setAttribute("studentId", stud);
        		out.println("<center>");

        		out.println("<div class='container'>");
        		out.println("<div class='container2'>");

        		out.println("<table>");
        		out.println("<tr><th colspan='2'><img src=\"getServlet\" alt='my image'></th></tr><br><br>");

        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>STUDENT ID:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(1));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");
        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>STUDENT'S FIRST NAME:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(2));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>STUDENT'S LAST NAME:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(3));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");
        		
        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>GENDER:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(13));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>FATHER'S NAME:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(4));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>DATE OF BIRTH:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(5));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");
        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>STUDENT\'S EMAIL ID:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(12));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>BRANCH:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(6));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");
        		
        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>YEAR:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(14));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>MESS:</h3></th>");
        		out.println("<td><h4>");
        		String queryMess = "select mess_name, mess_type from mess inner join students on mess.mess_id=students.mess_id where students.student_id=" + student_id + ";";
        		Statement st1 = null;
        		st1 = connection.createStatement();
        		ResultSet result1 = st1.executeQuery(queryMess);
        		if (result1.next()) {
        		    out.println(result1.getString(1));
        		    out.println("-");
        		    out.println(result1.getString(2));
        		}
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>HOSTEL:</h3></th>");
        		out.println("<td><h4>");
        		String queryHostel = "select rooms.hostel_name, rooms.room_type from rooms inner join students on rooms.hostel_id=students.hostel_id where students.student_id=" + student_id + ";";
        		Statement st2 = null;
        		st2 = connection.createStatement();
        		ResultSet result2 = st2.executeQuery(queryHostel);
        		if (result2.next()) {
        		    out.println(result2.getString(1));
        		    out.println("-");
        		    out.println(result2.getString(2));
        		}
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");

        		
        		out.println("<tr><div class='details'>");
        		out.println("<th><h3>ROOM NO:</h3></th>");
        		out.println("<td><h4>");
        		out.println(rs.getString(9));
        		out.println("</h4></td><br><br>");
        		out.println("</div></tr>");
        		
        		out.println("<tr><div class='details'><th colspan='2'><button><a href='student_login_old.html'>LOG OUT</a></button></th></div></tr>");
        		out.println("</table>");
        		
        		out.println("</div>");
        		out.println("</div>");

        		out.println("</center>");
        		out.println("</body>");
        		out.println("</html>");
        		

        	}
        	else{
        		out.println("No values");
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