package net.se2project.covidtracker.NewsServlet;

import net.se2project.covidtracker.dao.NewsDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AutoUpdateNewsServlet", urlPatterns = "/AutoUpdateNewsServlet")
public class AutoUpdateNewsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (NewsDao newsDao = new NewsDao()) {
            newsDao.autoUpdateNews();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("ShowAllNewsServlet");
    }
}
