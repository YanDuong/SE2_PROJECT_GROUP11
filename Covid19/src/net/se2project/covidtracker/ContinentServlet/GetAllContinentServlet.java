package net.se2project.covidtracker.ContinentServlet;


import net.se2project.covidtracker.model.Continent;
import service.ContinentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "GetAllContinentServlet", urlPatterns = "/GetAllContinentServlet")
public class GetAllContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentService service = new ContinentService()) {
            List<Continent> listContinent = service.selectAllContinent();
            request.setAttribute("listCountry", listContinent);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("view-continent.jsp");
    }
}
