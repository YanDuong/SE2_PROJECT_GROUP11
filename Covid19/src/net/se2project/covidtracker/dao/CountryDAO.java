package net.se2project.covidtracker.dao;

import net.se2project.covidtracker.model.Country;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import connect.DBConnection;
import daoi.CountryDAOI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table countries in the database.
 *
 * @author Khang
 *
 */
public class CountryDAO implements AutoCloseable, CountryDAOI {

    private static final String INSERT_COUNTRY_SQL = "INSERT INTO country" + "  (country_name, total_cases," +
            " new_cases, total_death,new_death,total_recovered,active_cases,critical_cases) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_COUNTRY_BY_ID = "select id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases from country where id =?";
    private static final String SELECT_ALL_COUNTRIES = "select * from country;";
    private static final String DELETE_COUNTRIES_SQL = "delete from country where id = ?;";
    private static final String UPDATE_COUNTRIES_SQL = "update country set country_name=?, total_cases=?,new_cases=?, total_death=?,new_death=?,total_recovered=?,active_cases=?,critical_cases=? where id = ?;";
    private static final String DELETE_ALL_COUNTRIES = "DELETE FROM country;";
    private static final String RESET_COUNTRIES_ID = "ALTER TABLE country AUTO_INCREMENT = 1;";
    private static final String SELECT_TOTAL = "SELECT id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases FROM country WHERE country_name = \"World\"";
    private static final String SELECT_TOTAL_VIETNAM = "SELECT id,country_name, total_cases, new_cases, total_death,new_death,total_recovered,active_cases,critical_cases FROM country WHERE country_name = \"Vietnam\"";

    private static final String INSERT_PROVINCE_SQL = "INSERT INTO city (country_name, total_cases,active_cases, total_recovered, total_death) VALUES (?, ?, ?, ?, ?);";

    private static final String SELECT_ALL_PROVINCE = "select * from city;";

    public CountryDAO() throws SQLException {
    }

@Override
    public boolean autoUpdateCountry() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;
        deleteAllCountry();
        resetCountriesId();

        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
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
                    int temp2 = 0;
                    if (rowItems.get(j).text().equals(temp)) {
                        temp2 = 0;
                    } else {
                        temp = rowItems.get(j).text();
                        if(containsDigit(temp)){
                            temp = temp.replaceAll("[^a-zA-Z0-9]", "");
                            temp2 = Integer.parseInt(temp);
                        }else {
                            temp = temp;
                        }
                    }
                    if (b == 11) {
                        j = j + 3;
                        b = 1;
                    } else if (b == 1) {

                    } else if (b == 2) {
                        if(temp.equals("Total:") || temp.equals("North America") || temp.equals("Europe") ||
                                temp.equals("South America") || temp.equals("Asia") || temp.equals("Africa") ||
                                temp.equals("Oceania") ||temp.equals("")){
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
                    } else if (b == 9) {
                        statement.setInt(7, temp2);
                    } else if (b == 10) {
                        statement.setInt(8, temp2);
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
    public static boolean containsDigit(String s) {
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


    @Override
    public Country selectCountry(int id) {
        Country country = null;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);) {
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

                country = new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return country;
    }


    public List<Country> listTotal() {
        List<Country> total = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
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
                total.add(new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }


    public List<Country> listTotalProvince() {

        List<Country> total = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
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
                total.add(new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }


    @Override
    public List<Country> selectAllCountries() {
        List<Country> countries = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRIES);) {
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
                countries.add(new Country(id, country_name, total_cases, new_cases, total_death, new_death, total_recovered, active_cases, critical_cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return countries;
    }


    @Override
    public boolean deleteCountry(int id) throws SQLException {
        boolean rowDeleted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COUNTRIES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        }
        return rowDeleted;
    }


    public boolean deleteAllCountry() throws SQLException {
        boolean rowAllDeleted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_COUNTRIES);) {
            rowAllDeleted = statement.executeUpdate() > 0;
        }
        return rowAllDeleted;
    }


    @Override
    public boolean resetCountriesId() throws SQLException {
        boolean tableReserted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_COUNTRIES_ID);) {
            tableReserted = statement.executeUpdate() > 0;
        }
        return tableReserted;
    }


    @Override
    public boolean updateCountry(Country country) throws SQLException {
        boolean rowUpdated;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_COUNTRIES_SQL);) {
            statement.setString(1, country.getCountry_name());
            statement.setInt(2, country.getTotal_cases());
            statement.setInt(3, country.getNew_cases());
            statement.setInt(4, country.getTotal_death());
            statement.setInt(5, country.getNew_death());
            statement.setInt(6, country.getTotal_recovered());
            statement.setInt(7, country.getActive_cases());
            statement.setInt(8, country.getCritical_cases());
            statement.setInt(9, country.getId());

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


    public Country findTotal(int id) {
        Integer i = id;

        Country artc = new Country();
        artc.setCountry_name("Placeholder");
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
        		PreparedStatement ps = connection.prepareStatement(SELECT_TOTAL)) {
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


    @Override
    public boolean insertCountry(Country country) throws SQLException {
        boolean a = false;
        String sql = "SELECT * FROM country WHERE country_name = ?";
        DBConnection dbhelper = DBConnection.getDBHelper();
		Connection connection = dbhelper.getConnection();

        try (PreparedStatement checkAccountExists = connection.prepareStatement(sql)) {
            checkAccountExists.setString(1, country.getCountry_name());
            try (ResultSet rs = checkAccountExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Account Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_COUNTRY_SQL)) {
                        insert.setString(1, country.getCountry_name());
                        insert.setInt(2, country.getTotal_cases());
                        insert.setInt(3, country.getNew_cases());
                        insert.setInt(4, country.getTotal_death());
                        insert.setInt(5, country.getNew_death());
                        insert.setInt(6, country.getTotal_recovered());
                        insert.setInt(7, country.getActive_cases());
                        insert.setInt(8, country.getCritical_cases());

                        System.out.println(insert);
                        a = insert.executeUpdate()>0;
                    } catch (SQLException e) {
                        printSQLException(e);
                    }
                }
            }
        }
        return a;
    }
}
