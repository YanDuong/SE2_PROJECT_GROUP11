package net.se2project.covidtracker.CountryServlet;

import net.se2project.covidtracker.dao.CountryDAO;
import net.se2project.covidtracker.model.Country;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "AddCountryServlet", urlPatterns = "/AddCountryServlet")
public class AddCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryDAO countryDAO = new CountryDAO()) {
            String country_name = request.getParameter("country_name");
            String total_cases = request.getParameter("total_cases");
            String new_cases = request.getParameter("new_cases");
            String total_death = request.getParameter("total_death");
            String new_death = request.getParameter("new_death");
            String total_recovered = request.getParameter("total_recovered");
            String active_cases = request.getParameter("active_cases");
            String critical_cases = request.getParameter("critical_cases");
            Country newCountry = new Country(country_name, total_cases,new_cases, total_death,new_death,total_recovered,active_cases,critical_cases);
            countryDAO.insertCountry(newCountry);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/ShowAllCountryServlet");
    }
}
