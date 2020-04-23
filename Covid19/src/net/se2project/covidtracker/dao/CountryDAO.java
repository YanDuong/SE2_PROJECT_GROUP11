package net.se2project.covidtracker.dao;

import net.se2project.covidtracker.model.Country;
//import net.se2project.covidtracker.dao.VietnamDAO;

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
 * table countries in the database.
 *
 * @author Khang
 *
 */
public class CountryDAO implements AutoCloseable {

    private static final String INSERT_COUNTRY_SQL = "INSERT INTO countries" + "  (country_name, total_cases," +
            " new_cases, total_death,new_death,total_recovered,active_cases,critical_cases) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?);";


    private static final String SELECT_COUNTRY_BY_ID = "select id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases from countries where id =?";
    private static final String SELECT_ALL_COUNTRIES = "select * from countries;";
    private static final String DELETE_COUNTRIES_SQL = "delete from countries where id = ?;";
    private static final String UPDATE_COUNTRIES_SQL = "update countries set country_name=?, total_cases=?,new_cases=?, total_death=?,new_death=?,total_recovered=?,active_cases=?,critical_cases=? where id = ?;";
    private static final String DELETE_ALL_COUNTRIES = "DELETE FROM countries;";
    private static final String RESET_COUNTRIES_ID = "ALTER TABLE countries AUTO_INCREMENT = 1;";
    private static final String SELECT_TOTAL = "SELECT id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases FROM countries WHERE country_name = \"World\"";

    private static final String INSERT_PROVINCE_SQL = "INSERT INTO cities (country_name, total_cases,active_cases, total_recovered, total_death) VALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_ALL_PROVINCE = "select * from cities;";

    public CountryDAO() throws SQLException {
    }


    public boolean autoUpdateCountry() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;
        deleteAllCountry();
        resetCountriesId();
        timeUpdate();
        autoUpdateVietnam();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_COUNTRY_SQL)) {

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
                    if (rowItems.get(j).text().equals(temp)) {
                        temp = "";
                    } else {
                        temp = rowItems.get(j).text();
                    }
                    if (b == 9) {
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
                    } else if (b == 6) {
                        statement.setString(6, temp);
                    } else if (b == 7) {
                        statement.setString(7, temp);
                    } else if (b == 8) {
                        statement.setString(8, temp);
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

    //vietnam

    public List<Vietnam> selectAllProvince() {
        List<Vietnam> provinces = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String province_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String active_cases = rs.getString("active_cases");
                String total_death = rs.getString("total_death");
                String total_recovered = rs.getString("total_recovered");
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
    public void insertCountry(Country country) throws SQLException {
        System.out.println(INSERT_COUNTRY_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COUNTRY_SQL)) {
            preparedStatement.setString(1, country.getCountry_name());
            preparedStatement.setString(2, country.getTotal_cases());
            preparedStatement.setString(3, country.getNew_cases());
            preparedStatement.setString(4, country.getTotal_death());
            preparedStatement.setString(5, country.getNew_death());
            preparedStatement.setString(6, country.getTotal_recovered());
            preparedStatement.setString(7, country.getActive_cases());
            preparedStatement.setString(8, country.getCritical_cases());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
*/
    public Country selectCountry(int id) {
        Country country = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String new_cases = rs.getString("new_cases");
                String total_death = rs.getString("total_death");
                String new_death = rs.getString("new_death");
                String total_recovered = rs.getString("total_recovered");
                String active_cases = rs.getString("active_cases");
                String critical_cases = rs.getString("critical_cases");

                country = new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return country;
    }


    public List<Country> listTotal() {

        List<Country> total = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String new_cases = rs.getString("new_cases");
                String total_death = rs.getString("total_death");
                String new_death = rs.getString("new_death");
                String total_recovered = rs.getString("total_recovered");
                String active_cases = rs.getString("active_cases");
                String critical_cases = rs.getString("critical_cases");
                total.add(new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }

    public List<Country> selectAllCountries() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String new_cases = rs.getString("new_cases");
                String total_death = rs.getString("total_death");
                String new_death = rs.getString("new_death");
                String total_recovered = rs.getString("total_recovered");
                String active_cases = rs.getString("active_cases");
                String critical_cases = rs.getString("critical_cases");
                countries.add(new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return countries;
    }

    public boolean deleteCountry(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRIES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        }
        return rowDeleted;
    }

    public boolean deleteAllCountry() throws SQLException {
        boolean rowAllDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_COUNTRIES);) {
            rowAllDeleted = statement.executeUpdate() > 0;
        }
        return rowAllDeleted;
    }

    public boolean resetCountriesId() throws SQLException {
        boolean tableReserted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_COUNTRIES_ID);) {
            tableReserted = statement.executeUpdate() > 0;
        }
        return tableReserted;
    }


    public boolean updateCountry(Country country) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTRIES_SQL);) {
            statement.setString(1, country.getCountry_name());
            statement.setString(2, country.getTotal_cases());
            statement.setString(3, country.getNew_cases());
            statement.setString(4, country.getTotal_death());
            statement.setString(5, country.getNew_death());
            statement.setString(6, country.getTotal_recovered());
            statement.setString(7, country.getActive_cases());
            statement.setString(8, country.getCritical_cases());
            statement.setInt(9, country.getId());

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

    public Country findTotal(int id) {
        Integer i = id;

        if (i == null) {
            return null;
        }

        Country artc = new Country();
        artc.setCountry_name("Placeholder");
        try (PreparedStatement ps = getConnection().prepareStatement(SELECT_TOTAL)) {
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                ps.setString(1, artc.getCountry_name());
                ps.setString(2, artc.getTotal_cases());
                ps.setString(3, artc.getNew_cases());
                ps.setString(4, artc.getTotal_death());
                ps.setString(5, artc.getNew_death());
                ps.setString(6, artc.getTotal_recovered());
                ps.setString(7, artc.getActive_cases());
                ps.setString(8, artc.getCritical_cases());
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


    public boolean insertCountry(Country country) throws SQLException {
        boolean a = false;
        String sql = "SELECT * FROM countries WHERE country_name = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, country.getCountry_name());


        try (PreparedStatement checkAccountExists = connection.prepareStatement(sql)) {
            checkAccountExists.setString(1, country.getCountry_name());
            try (ResultSet rs = checkAccountExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Account Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_COUNTRY_SQL)) {
                        insert.setString(1, country.getCountry_name());
                        insert.setString(2, country.getTotal_cases());
                        insert.setString(3, country.getNew_cases());
                        insert.setString(4, country.getTotal_death());
                        insert.setString(5, country.getNew_death());
                        insert.setString(6, country.getTotal_recovered());
                        insert.setString(7, country.getActive_cases());
                        insert.setString(8, country.getCritical_cases());

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
