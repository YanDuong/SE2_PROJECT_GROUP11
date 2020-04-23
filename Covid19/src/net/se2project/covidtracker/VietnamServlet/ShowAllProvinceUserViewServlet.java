package net.se2project.covidtracker.VietnamServlet;

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
import java.util.List;

@WebServlet(name = "ShowAllProvinceUserViewServlet", urlPatterns = "/ShowAllProvinceUserViewServlet")
public class ShowAllProvinceUserViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (VietnamDAO vietnamDAO = new VietnamDAO()) {
            List<Vietnam> listProvinceUserView = vietnamDAO.selectAllProvince();
            request.setAttribute("listProvinceUserView", listProvinceUserView);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-view-vn.jsp");
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
