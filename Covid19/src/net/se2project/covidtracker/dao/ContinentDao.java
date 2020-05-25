package net.se2project.covidtracker.dao;

import net.se2project.covidtracker.model.Continent;

import net.se2project.covidtracker.model.Vietnam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static connect.DBConnect.getConnection;

//Scraping Data

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table continents in the database.
 *
 * @author Khang
 *
 */
public class ContinentDao implements AutoCloseable {

    private static final String INSERT_CONTINENT_SQL = "INSERT INTO continent" + "  (country_name, total_cases," +
            " new_cases, total_death,new_death,total_recovered,active_cases,critical_cases) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String SELECT_CONTINENT_BY_ID = "select id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases from continent where id =?";
    private static final String SELECT_ALL_CONTINENT = "select * from continent;";
    private static final String DELETE_CONTINENT_SQL = "delete from continent where id = ?;";
    private static final String UPDATE_CONTINENT_SQL = "update continent set country_name=?, total_cases=?,new_cases=?, total_death=?,new_death=?,total_recovered=?,active_cases=?,critical_cases=? where id = ?;";
    private static final String DELETE_ALL_CONTINENT = "DELETE FROM continent;";
    private static final String RESET_CONTINENT_ID = "ALTER TABLE continent AUTO_INCREMENT = 1;";
    private static final String SELECT_TOTAL = "SELECT id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases FROM continent WHERE country_name = \"World\"";
    private static final String SELECT_TOTAL_VIETNAM = "SELECT id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases FROM continent WHERE country_name = \"Vietnam\"";

    private static final String INSERT_PROVINCE_SQL = "INSERT INTO city (country_name, total_cases,active_cases, total_recovered, total_death) VALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_ALL_PROVINCE = "select * from city;";

    public ContinentDao() throws SQLException {
    }


    public boolean autoUpdateContinent() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;
        deleteAllContinent();
        resetContinentId();
        timeUpdate();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CONTINENT_SQL)) {

            String url = "https://www.worldometers.info/coronavirus/";
            Document doc = Jsoup.connect(url).get();
            Element tableElement = doc.select("table").first();

            Elements tableRowElements = tableElement.select(":not(thead) tr");

            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                int b = 0;
                for (int j = 0; j < rowItems.size(); j++) {
                    b += 1;
                    String temp = "";
                    int temp2 = 0;
                    if (rowItems.get(j).text().equals(temp)) {
                        continue;
                    } else {
                        temp = rowItems.get(j).text();
                        if(containsDigit(temp)){
                            temp = temp.replaceAll("[^a-zA-Z0-9]", "");
                            temp2 = Integer.parseInt(temp);
                        }
                    }
                    if (b == 10) {
                        j = j + 3;
                        b = 1;
                    } else if (b == 2) {
                        if (temp.equals("Total:")|| temp.equals("A")) {
                            break;
                        }
                        statement.setString(1, temp);
                    } else if (b == 3) {
                        statement.setInt(2, temp2);
                    } else if (b == 4) {
                        statement.setInt(3, temp2);
                    } else if (b == 5) {
                        statement.setInt(4, temp2);
                    } else if (b == 6) {
                        statement.setInt(5, temp2);
                    } else if (b == 7) {
                        statement.setInt(6, temp2);
                    } else if (b == 8) {
                        statement.setInt(7, temp2);
                    } else if (b == 9) {
                        statement.setInt(8, temp2);
                        System.out.println(statement);
                        rowAutoUpdated = statement.executeUpdate() > 0;
                    } else {
                        break;
                    }
                }
            }
        }
        return rowAutoUpdated;
    }

    //vietnam
    public static final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }



    public List<Vietnam> selectAllProvince() {
        List<Vietnam> provinces = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String province_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int active_cases = rs.getInt("active_cases");
                int total_death = rs.getInt("total_death");
                int total_recovered = rs.getInt("total_recovered");
                provinces.add(new Vietnam(id, province_name, total_cases, active_cases, total_death, total_recovered));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return provinces;
    }


    public boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PROVINCE_SQL)) {

            String url = "https://vi.wikipedia.org/wiki/%C4%90%E1%BA%A1i_d%E1%BB%8Bch_COVID-19_t%E1%BA%A1i_Vi%E1%BB%87t_Nam";
            Document doc = Jsoup.connect(url).get();
            Element tableElement = doc.select("table").get(3);

            Elements tableRowElements = tableElement.select(":not(thead) tr");


            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                int b = 0;
                for (int j = 0; j < rowItems.size(); j++) {
                    b += 1;
                    String temp = rowItems.get(j).text();
                    if (b == 6) {
                        j = j + 3;
                        b = 1;
                    } else if (b == 1) {
                        statement.setString(1, temp);
                    } else if (b == 2) {
                        statement.setString(2, temp);
                    } else if (b == 3) {
                        statement.setString(3, temp);
                    } else if (b == 4) {
                        statement.setString(4, temp);
                    } else if (b == 5) {
                        statement.setString(5, temp);
                        b = 0;
                        System.out.println(statement);
                        rowAutoUpdated = statement.executeUpdate() > 0;
                    } else {
                        System.out.println("Err");
                    }
                }
            }
        }
        return rowAutoUpdated;
    }

    //Vietnam
    public boolean autoUpdateViwetnam() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PROVINCE_SQL)) {

            String url = "https://vi.wikipedia.org/wiki/%C4%90%E1%BA%A1i_d%E1%BB%8Bch_COVID-19_t%E1%BA%A1i_Vi%E1%BB%87t_Nam";
            Document doc = Jsoup.connect(url).get();
            Element tableElement = doc.select("table").get(3);

            Elements tableRowElements = tableElement.select(":not(thead) tr");

            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                Elements rowItems = row.select("td");
                String temp = "";

                for (int j = 0; j < rowItems.size(); j++) {
                    temp = rowItems.get(j).text();
                    statement.setString(1, temp);
                    System.out.println(statement);
                    rowAutoUpdated = statement.executeUpdate() > 0;
                }
            }
        }
        return rowAutoUpdated;
    }


    public String timeUpdate() throws SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeUpdate = dtf.format(now);
        return timeUpdate;
    }
    /*
        public void insertContinent(Continent continent) throws SQLException {
            System.out.println(INSERT_CONTINENT_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTINENT_SQL)) {
                preparedStatement.setString(1, continent.getCountry_name());
                preparedStatement.setString(2, continent.getTotal_cases());
                preparedStatement.setString(3, continent.getNew_cases());
                preparedStatement.setString(4, continent.getTotal_death());
                preparedStatement.setString(5, continent.getNew_death());
                preparedStatement.setString(6, continent.getTotal_recovered());
                preparedStatement.setString(7, continent.getActive_cases());
                preparedStatement.setString(8, continent.getCritical_cases());

                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    */
    public Continent selectContinent(int id) {
        Continent continent = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTINENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int new_cases = rs.getInt("new_cases");
                int total_death = rs.getInt("total_death");
                int new_death = rs.getInt("new_death");
                int total_recovered = rs.getInt("total_recovered");
                int active_cases = rs.getInt("active_cases");
                int critical_cases = rs.getInt("critical_cases");

                continent = new Continent(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return continent;
    }


    public List<Continent> listTotal() {

        List<Continent> total = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int new_cases = rs.getInt("new_cases");
                int total_death = rs.getInt("total_death");
                int new_death = rs.getInt("new_death");
                int total_recovered = rs.getInt("total_recovered");
                int active_cases = rs.getInt("active_cases");
                int critical_cases = rs.getInt("critical_cases");
                total.add(new Continent(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }


    public List<Continent> listTotalProvince() {

        List<Continent> total = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL_VIETNAM);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int new_cases = rs.getInt("new_cases");
                int total_death = rs.getInt("total_death");
                int new_death = rs.getInt("new_death");
                int total_recovered = rs.getInt("total_recovered");
                int active_cases = rs.getInt("active_cases");
                int critical_cases = rs.getInt("critical_cases");
                total.add(new Continent(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }

    public List<Continent> selectAllContinent() {
        List<Continent> continents = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTINENT);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int new_cases = rs.getInt("new_cases");
                int total_death = rs.getInt("total_death");
                int new_death = rs.getInt("new_death");
                int total_recovered = rs.getInt("total_recovered");
                int active_cases = rs.getInt("active_cases");
                int critical_cases = rs.getInt("critical_cases");
                continents.add(new Continent(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return continents;
    }

    public boolean deleteContinent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CONTINENT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        }
        return rowDeleted;
    }

    public boolean deleteAllContinent() throws SQLException {
        boolean rowAllDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_CONTINENT);) {
            rowAllDeleted = statement.executeUpdate() > 0;
        }
        return rowAllDeleted;
    }

    public boolean resetContinentId() throws SQLException {
        boolean tableReserted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_CONTINENT_ID);) {
            tableReserted = statement.executeUpdate() > 0;
        }
        return tableReserted;
    }


    public boolean updateContinent(Continent continent) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CONTINENT_SQL);) {
            statement.setString(1, continent.getCountry_name());
            statement.setInt(2, continent.getTotal_cases());
            statement.setInt(3, continent.getNew_cases());
            statement.setInt(4, continent.getTotal_death());
            statement.setInt(5, continent.getNew_death());
            statement.setInt(6, continent.getTotal_recovered());
            statement.setInt(7, continent.getActive_cases());
            statement.setInt(8, continent.getCritical_cases());
            statement.setInt(9, continent.getId());

            System.out.println(statement);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
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

    public Continent findTotal(int id) {
        Integer i = id;

        if (i == null) {
            return null;
        }

        Continent artc = new Continent();
        artc.setCountry_name("Placeholder");
        try (PreparedStatement ps = getConnection().prepareStatement(SELECT_TOTAL)) {
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ps.setString(1, artc.getCountry_name());
                ps.setInt(2, artc.getTotal_cases());
                ps.setInt(3, artc.getNew_cases());
                ps.setInt(4, artc.getTotal_death());
                ps.setInt(5, artc.getNew_death());
                ps.setInt(6, artc.getTotal_recovered());
                ps.setInt(7, artc.getActive_cases());
                ps.setInt(8, artc.getCritical_cases());
            }
            return artc;

        } catch (SQLException e) {
            System.out.println("exception " + e);

        }
        return artc;
    }

    @Override
    public void close() throws Exception {

    }


    public boolean insertContinent(Continent continent) throws SQLException {
        boolean a = false;
        String sql = "SELECT * FROM continent WHERE country_name = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, continent.getCountry_name());


        try (PreparedStatement checkContinentExists = connection.prepareStatement(sql)) {
            checkContinentExists.setString(1, continent.getCountry_name());
            try (ResultSet rs = checkContinentExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Continent Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_CONTINENT_SQL)) {
                        insert.setString(1, continent.getCountry_name());
                        insert.setInt(2, continent.getTotal_cases());
                        insert.setInt(3, continent.getNew_cases());
                        insert.setInt(4, continent.getTotal_death());
                        insert.setInt(5, continent.getNew_death());
                        insert.setInt(6, continent.getTotal_recovered());
                        insert.setInt(7, continent.getActive_cases());
                        insert.setInt(8, continent.getCritical_cases());

                        System.out.println(insert);
                        insert.executeUpdate();
                    } catch (SQLException e) {
                        printSQLException(e);
                    }
                }
            }
        }
        return a;
    }
}
