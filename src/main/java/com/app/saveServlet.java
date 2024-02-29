package com.app;

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
import java.sql.Statement;

@MultipartConfig
public class saveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public saveServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Part filepart = request.getPart("photo");

        InputStream fileInputStream = filepart.getInputStream();

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

            String query2 = "insert into students (filename,photo) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(query2);
            statement.setString(1, filepart.getSubmittedFileName());
            statement.setBinaryStream(2, fileInputStream);

            statement.executeUpdate();

            statement.close();
            fileInputStream.close();
            connection.close();

            out.println("photo stored");

        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.getMessage());
            out.println("unable to save");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
