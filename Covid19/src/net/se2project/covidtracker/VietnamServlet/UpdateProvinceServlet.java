package net.se2project.covidtracker.VietnamServlet;

import net.se2project.covidtracker.dao.VietnamDAO;
import net.se2project.covidtracker.model.Vietnam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateProvinceServlet", urlPatterns = "/UpdateProvinceServlet")
public class UpdateProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamDAO vietnamDAO = new VietnamDAO()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String country_name = request.getParameter("country_name");
            int total_cases = Integer.parseInt(request.getParameter("total_cases"));
            int total_death = Integer.parseInt(request.getParameter("total_death"));
            int total_recovered = Integer.parseInt(request.getParameter("total_recovered"));
            int active_cases = Integer.parseInt(request.getParameter("active_cases"));
            Vietnam book = new Vietnam(id,country_name, total_cases,active_cases,total_recovered,total_death);
            vietnamDAO.updateProvince(book);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowAllProvinceServlet");
        dispatcher.forward(request, response);
    }
}
