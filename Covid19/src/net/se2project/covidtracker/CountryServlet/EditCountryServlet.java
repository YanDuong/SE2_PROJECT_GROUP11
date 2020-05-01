package net.se2project.covidtracker.CountryServlet;

import net.se2project.covidtracker.dao.CountryDAO;
import net.se2project.covidtracker.model.Country;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "EditCountryServlet", urlPatterns = "/EditCountryServlet")
public class EditCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryDAO countryDAO = new CountryDAO()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Country existingCountry = countryDAO.selectCountry(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("country-form.jsp");
            request.setAttribute("country", existingCountry);
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
