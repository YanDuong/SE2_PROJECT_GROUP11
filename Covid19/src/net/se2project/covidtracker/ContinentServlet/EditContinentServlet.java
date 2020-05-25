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

@WebServlet(name = "EditContinentServlet", urlPatterns = "/EditContinentServlet")
public class EditContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentDao ContinentDao = new ContinentDao()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Continent existingContinent = ContinentDao.selectContinent(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("form-continent.jsp");
            request.setAttribute("continent", existingContinent);
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
