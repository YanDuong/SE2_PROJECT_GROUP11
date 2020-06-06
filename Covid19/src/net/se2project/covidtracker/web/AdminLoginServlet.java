package net.se2project.covidtracker.web;

 
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.AdminService;
import net.se2project.covidtracker.model.Admin;

@WebServlet("/login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public AdminLoginServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String destPage = "error.jsp";


        Map<String, String> errors = new HashMap<String, String>();

        AdminService userDao = null;
        try {
            userDao = new AdminService();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Admin admin = userDao.checkLogin(email, password);
            destPage = "login.jsp";

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                destPage = "ShowAllCountryServlet";
            } else {
                errors.put("passwordError", "Invalid");
                String message = "Invalid email/password";
                request.setAttribute("message", message);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }
}