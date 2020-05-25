package net.se2project.covidtracker.CountryServlet;

import net.se2project.covidtracker.dao.CountryDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteCountryServlet", urlPatterns = "/DeleteCountryServlet")
public class DeleteCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryDAO countryDAO = new CountryDAO()) {
            int id = Integer.parseInt(request.getParameter("id"));
            countryDAO.deleteCountry(id);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllCountryServlet");
    }


}
