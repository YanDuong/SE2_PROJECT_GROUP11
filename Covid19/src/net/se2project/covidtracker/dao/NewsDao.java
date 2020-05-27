package net.se2project.covidtracker.dao;

import net.se2project.covidtracker.model.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import connect.DBConnection;
import daoi.NewsDAOI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class NewsDAO implements AutoCloseable, NewsDAOI {

    private static final String INSERT_NEWS_SQL = "INSERT INTO news (title, url, imageUrl,sourceMeta, datePublic) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_NEWS = "select * from news;";
    private static final String DELETE_ALL_NEWS = "DELETE FROM news;";
    private static final String RESET_NEWS_ID = "ALTER TABLE news AUTO_INCREMENT = 1;";

    public NewsDAO() {

    }

    public boolean autoUpdateNews() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;
        deleteAllNews();
        resetNewsId();

        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEWS_SQL)) {

            String url = "https://baomoi.com/phong-chong-dich-covid-19/top/328.epi";
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.story");
            System.out.println(elements.size());
            for (int i = 0; i < elements.size(); i++) {
                boolean withoutTest = elements.get(i).hasClass("wait-render");
                if (!withoutTest){
                    Element element = elements.get(i);
                    if(i == 50){
                        break;
                    }
                    Element elementImg = element.getElementsByTag("img").first();
                    Element elementTitle = element.select("a[href]").first();
                    Element elementSourceMeta = element.getElementsByClass("source").first();
                    Element elementTime = element.select(".time.friendly").first();


                    statement.setString(1, elementImg.attr("alt"));
                    statement.setString(2, elementTitle.attr("href"));
                    if(elementImg.attr("src").equals("")){
                        statement.setString(3, elementImg.attr("data-src"));
                    }else {
                        statement.setString(3, elementImg.attr("src"));
                    }
                    statement.setString(4,  elementSourceMeta.text());

                    String a =  elementTime.attr("datetime");
                    if(a.length() > 10) {
                        String strOut = a.substring(0, 16);
                        String date1 = strOut.replaceAll("T", " ");
                        statement.setString(5, date1);
                    }

                    rowAutoUpdated = statement.executeUpdate()>0;
                } else {
                    continue;
                }
            }
            return rowAutoUpdated;
        }
    }


    public List<News> selectAllNews() {
        List<News> news = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NEWS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String url = rs.getString("url");
                String imageUrl = rs.getString("imageUrl");
                String sourceMeta = rs.getString("sourceMeta");
                String datePublic = rs.getString("datePublic");

                news.add(new News(id, title, url, imageUrl, sourceMeta, datePublic));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return news;
    }


    private static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    @Override
    public boolean deleteAllNews() throws SQLException {
        boolean rowAllDeleted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_NEWS);) {
            rowAllDeleted = statement.executeUpdate() > 0;
        }
        return rowAllDeleted;
    }


    public boolean resetNewsId() throws SQLException {
        boolean tableReserted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_NEWS_ID);) {
            tableReserted = statement.executeUpdate() > 0;
        }
        return tableReserted;
    }


    @Override
    public void close() throws Exception {

    }
}
