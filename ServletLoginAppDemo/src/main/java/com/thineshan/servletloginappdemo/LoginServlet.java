package com.thineshan.servletloginappdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        try (Connection con = DBConnectionUtil.getConnection();
             PrintWriter out = response.getWriter()) {

            response.setContentType("text/html");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                out.println("Welcome "+name);
                out.println("<br>Click <a href=\"/ServletLoginAppDemo-1.0/login.html\">here</a> to login");
            } else {
                out.println("Login Failure");
                out.println("<br>Click <a href=\"/ServletLoginAppDemo-1.0/login.html\">here</a> to login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
