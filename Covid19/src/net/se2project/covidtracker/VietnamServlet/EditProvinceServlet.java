package net.se2project.covidtracker.VietnamServlet;

import com.sun.tools.attach.VirtualMachine;
import net.se2project.covidtracker.dao.VietnamDAO;
import net.se2project.covidtracker.model.Country;
import net.se2project.covidtracker.model.Vietnam;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "EditProvinceServlet", urlPatterns = "/EditProvinceServlet")
public class EditProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamDAO vietnamDAO = new VietnamDAO()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Vietnam existingProvince = vietnamDAO.selectProvince(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("province-form.jsp");
            request.setAttribute("province", existingProvince);
            dispatcher.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
