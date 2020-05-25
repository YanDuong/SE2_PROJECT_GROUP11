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
import java.util.List;

@WebServlet(name = "ShowAllContinentServlet", urlPatterns = "/ShowAllContinentServlet")
public class ShowAllContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentDao ContinentDao = new ContinentDao()) {
            List<Continent> listContinent = ContinentDao.selectAllContinent();
            request.setAttribute("listContinent", listContinent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-continent.jsp");
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

}
