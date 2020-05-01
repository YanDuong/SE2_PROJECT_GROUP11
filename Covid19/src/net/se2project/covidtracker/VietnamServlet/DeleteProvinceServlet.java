package net.se2project.covidtracker.VietnamServlet;

import net.se2project.covidtracker.dao.VietnamDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "DeleteProvinceServlet", urlPatterns = "/DeleteProvinceServlet")
public class DeleteProvinceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamDAO vietnamDAO = new VietnamDAO()) {
            int id = Integer.parseInt(request.getParameter("id"));
            vietnamDAO.deleteProvince(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ShowAllProvinceServlet");
    }
}
