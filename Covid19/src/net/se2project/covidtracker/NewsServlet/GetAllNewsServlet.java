package net.se2project.covidtracker.NewsServlet;


import net.se2project.covidtracker.model.News;
import service.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAllNewsServlet", urlPatterns = "/GetAllNewsServlet")
public class GetAllNewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (NewsService service = new NewsService()) {
            List<News> listNews = service.selectAllNews();
            request.setAttribute("listNews", listNews);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        response.sendRedirect("news.jsp");
    }
}
