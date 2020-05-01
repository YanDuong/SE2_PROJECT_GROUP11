package net.se2project.covidtracker.CountryServlet;

import net.se2project.covidtracker.dao.CountryDAO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AutoUpdateCountryServlet", urlPatterns = "/AutoUpdateCountryServlet")
public class AutoUpdateCountryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (CountryDAO countryDAO = new CountryDAO()) {
            countryDAO.autoUpdateCountry();
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
