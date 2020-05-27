package net.se2project.covidtracker.ContinentServlet;

import net.se2project.covidtracker.model.Continent;
import service.ContinentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddContinentServlet", urlPatterns = "/AddContinentServlet")
public class AddContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentService service = new ContinentService()) {
            String country_name = request.getParameter("country_name");
            int total_cases = Integer.parseInt(request.getParameter("total_cases"));
            int new_cases = Integer.parseInt(request.getParameter("new_cases"));
            int total_death =Integer.parseInt(request.getParameter("total_death"));
            int new_death =Integer.parseInt(request.getParameter("new_death"));
            int total_recovered =Integer.parseInt(request.getParameter("total_recovered"));
            int active_cases =Integer.parseInt(request.getParameter("active_cases"));
            int critical_cases =Integer.parseInt(request.getParameter("critical_cases"));
            Continent newContinent = new Continent(country_name, total_cases,new_cases, total_death,new_death,total_recovered,active_cases,critical_cases);
            service.insertContinent(newContinent);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllContinentServlet");
    }
}
