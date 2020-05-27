package net.se2project.covidtracker.ContinentServlet;

import service.ContinentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "DeleteAllContinentServlet", urlPatterns = "/DeleteAllContinentServlet")
public class DeleteAllContinentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ContinentService service = new ContinentService()) {
            service.deleteAllContinent();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllContinentServlet");
    }


}
