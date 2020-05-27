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

@WebServlet(name = "EditProvinceServlet", urlPatterns = "/EditProvinceServlet")
public class EditProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamService service = new VietnamService()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Vietnam existingProvince = service.selectProvince(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("form-city.jsp");
            request.setAttribute("province", existingProvince);
            dispatcher.forward(request, response);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
