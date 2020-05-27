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
import java.util.List;


@WebServlet(name = "ShowAllCountryServlet", urlPatterns = "/ShowAllCountryServlet")
public class ShowAllCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryService service = new CountryService()) {
            List<Country> listCountry = service.selectAllCountries();
            request.setAttribute("listCountry", listCountry);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-world.jsp");
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

}
