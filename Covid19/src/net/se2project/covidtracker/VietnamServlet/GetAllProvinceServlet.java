package net.se2project.covidtracker.VietnamServlet;


import net.se2project.covidtracker.model.Vietnam;
import service.VietnamService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllProvinceServlet", urlPatterns = "/GetAllProvinceServlet")
public class GetAllProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamService service = new VietnamService()) {
            List<Vietnam> listProvince = service.selectAllProvince();
            request.setAttribute("listCountry", listProvince);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view-vietnam.jsp");
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
