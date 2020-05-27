package net.se2project.covidtracker.VietnamServlet;


import net.se2project.covidtracker.model.Vietnam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.VietnamService;

@WebServlet(name = "AddProvinceServlet", urlPatterns = "/AddProvinceServlet")
public class AddProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamService service = new VietnamService()) {
            String country_name = request.getParameter("country_name");
            int total_cases = Integer.parseInt(request.getParameter("total_cases"));
            int total_death = Integer.parseInt(request.getParameter("total_death"));
            int total_recovered = Integer.parseInt(request.getParameter("total_recovered"));
            int active_cases = Integer.parseInt(request.getParameter("active_cases"));
            Vietnam newProvince = new Vietnam(country_name, total_cases,active_cases,total_recovered, total_death);
            service.insertProvince(newProvince);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllProvinceServlet");
    }
}
