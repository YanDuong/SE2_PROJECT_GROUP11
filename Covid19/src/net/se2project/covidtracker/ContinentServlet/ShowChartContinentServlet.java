package net.se2project.covidtracker.ContinentServlet;

import net.se2project.covidtracker.dao.ContinentDao;
import net.se2project.covidtracker.model.Continent;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowChartContinentServlet", urlPatterns = "/ShowChartContinentServlet")
public class ShowChartContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentDao continentDao = new ContinentDao()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Continent existingCountry = continentDao.selectContinent(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("show-detail.jsp");
            request.setAttribute("country", existingCountry);
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
