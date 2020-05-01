package net.se2project.covidtracker.web;


import java.io.IOException;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/logout")
public class AdminLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public AdminLogoutServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("admin");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ShowAllCountryUserViewServlet");
            dispatcher.forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}