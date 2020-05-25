package net.se2project.covidtracker.NewsServlet;

import net.se2project.covidtracker.dao.NewsDao;
import net.se2project.covidtracker.model.News;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "ShowAllNewsServlet", urlPatterns = "/ShowAllNewsServlet")
public class ShowAllNewsServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try (NewsDao newDao = new NewsDao()) {
                List<News> listNews = newDao.selectAllNews();
                request.setAttribute("listNews", listNews);
                RequestDispatcher dispatcher = request.getRequestDispatcher("news.jsp");
                dispatcher.forward(request, response);
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }

        }

}
