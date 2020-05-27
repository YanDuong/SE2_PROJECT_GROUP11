package net.se2project.covidtracker.ChartServlet;

import net.se2project.covidtracker.model.Chart;
import service.ChartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowAllWorldChartServlet", urlPatterns = "/ShowAllWorldChartServlet")
public class ShowAllWorldChartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (ChartService chartService = new ChartService()) {
            List<Chart> listChart= chartService.selectAllWorldChart();
            request.setAttribute("listChart", listChart);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
