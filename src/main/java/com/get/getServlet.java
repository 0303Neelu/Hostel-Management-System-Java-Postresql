package com.get;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Servlet implementation class getServlet
 */
public class getServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dbUrl = "jdbc:postgresql://localhost:5432/DB_PROJECT";
        String dbUser = "postgres";
        String dbPassword = "Neelu@0303";

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Not OK");
                return;
            }

            String query = "SELECT photo FROM students WHERE student_id = ?";
            jakarta.servlet.http.HttpSession session = request.getSession();
    		String stud=(String) session.getAttribute("studentId");
            int studentId = Integer.parseInt(stud); // Change this to the actual student_id you want to retrieve
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                byte[] photoData = resultSet.getBytes("photo");

                // Set the content type to image/jpeg (adjust as needed based on your photo format)
                response.setContentType("image/jpeg");

                // Write the photo data to the response output stream
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(photoData);

                // Close resources
                outputStream.close();
            } else {
                System.out.println("No photo found for student_id: " + studentId);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
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
