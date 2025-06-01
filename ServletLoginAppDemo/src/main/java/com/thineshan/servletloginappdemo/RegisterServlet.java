package com.thineshan.servletloginappdemo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    Connection con;

    public void init(ServletConfig config)
    {
        String driver = config.getInitParameter("driver");
        String url = config.getInitParameter("url");
        String username = config.getInitParameter("username");
        String password = config.getInitParameter("password");
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {

            response.setContentType("text/html");
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            String sql = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, username);
            st.setString(3, password);

            st.executeUpdate();
            out.println("Registered Successfully<br>");
            out.println("Click <a href=\"/ServletLoginAppDemo-1.0/login.html\">here</a> to login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy()
    {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}