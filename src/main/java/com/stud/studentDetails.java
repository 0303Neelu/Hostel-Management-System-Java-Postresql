package com.stud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class studentDetails
 */

@MultipartConfig
public class studentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentDetails() {
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
		System.out.println(stud==null);
		int student_id=Integer.parseInt(stud);
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String gender=request.getParameter("gender");
		String father=request.getParameter("father");
		String dob=request.getParameter("dob");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date=null;
		try {
			date=dateFormat.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		String email=request.getParameter("email");
		String branch=request.getParameter("branch");
		String year=request.getParameter("year");
		String mess=request.getParameter("mess");
		int mess_id=Integer.parseInt(mess);
		String hostel=request.getParameter("hostel");
		int hostel_id=Integer.parseInt(hostel);
		String room=request.getParameter("room");
		int room_no=Integer.parseInt(room);
		
		Part filePart=request.getPart("photo");
		InputStream inputStream=filePart.getInputStream();
		
		
		
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
            ResultSet res=st.executeQuery(query);
            if(res.next()) {
            	out.println("<html>");
          	    out.println("<head>");
          	out.println("<script src=\"https://cdn.jsdelivr.net/npm/sweetalert2@11\"></script>");
          	 out.println("</head>");
          	    //out.println("<body>");
          	    out.println("</html>");
  				out.println("<script>");
  				out.println("Swal.fire({");
  				out.println("  title: \"Error!\",");
  				out.println("  text: \"Data Already Exist!\",");
  				out.println("  icon: \"error\",");
  				out.println("}).then(function() {");
  				out.println("  window.location.href = 'insert.html';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
            }
            else {
            	String query1="insert into students values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            	PreparedStatement state=connection.prepareStatement(query1);
            	state.setInt(1, student_id);
            	state.setString(2, fname);
            	state.setString(3, lname);
            	state.setString(4, father);
            	state.setDate(5,sqlDate);
            	state.setString(6,branch);
            	state.setInt(7, mess_id);
            	state.setInt(8, hostel_id);
            	state.setInt(9, room_no);
            	state.setBinaryStream(10, inputStream);
            	state.setString(11, filePart.getSubmittedFileName());
            	state.setString(12,email);
            	state.setString(13, gender);
            	state.setString(14, year);
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
  				out.println("  text: \"Data Addedd Successfully!\",");
  				out.println("  icon: \"success\",");
  				out.println("}).then(function() {");
  				out.println("  window.location.href = 'insert.html';"); // Redirect after the alert is closed
  				out.println("});");
  				out.println("</script>");
  				state.close();
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
