package net.se2project.covidtracker.web;

 
import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.se2project.covidtracker.dao.UserDAO;
import net.se2project.covidtracker.model.Admin;
import net.se2project.covidtracker.model.Country;

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

        Map<String, String> errors = new HashMap<String, String>();

        UserDAO userDao = new UserDAO();

        try {
            Admin admin = userDao.checkLogin(email, password);
            String destPage = "/login.jsp";

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                destPage = "/ShowAllCountryServlet";
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