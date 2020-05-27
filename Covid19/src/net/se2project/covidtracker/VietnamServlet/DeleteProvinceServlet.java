package net.se2project.covidtracker.VietnamServlet;

import service.VietnamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProvinceServlet", urlPatterns = "/DeleteProvinceServlet")
public class DeleteProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamService service = new VietnamService()) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.deleteProvince(id);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllProvinceServlet");
    }
}
