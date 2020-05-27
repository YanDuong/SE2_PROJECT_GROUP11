package net.se2project.covidtracker.CountryServlet;

import net.se2project.covidtracker.model.Country;
import service.CountryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EditCountryServlet", urlPatterns = "/EditCountryServlet")
public class EditCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryService service = new CountryService()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Country existingCountry = service.selectCountry(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("form-country.jsp");
            request.setAttribute("country", existingCountry);
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
