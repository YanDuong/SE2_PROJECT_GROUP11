package net.se2project.covidtracker.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

//Scraping Data

import net.se2project.covidtracker.model.Vietnam;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import connect.DBConnection;
import daoi.VietnamDAOI;


public class VietnamDAO implements AutoCloseable, VietnamDAOI {

    private static final String INSERT_PROVINCE_SQL = "INSERT INTO city (country_name, total_cases,active_cases, total_recovered, total_death) VALUES (?, ?, ?, ?, ?);";

    private static final String DELETE_PROVINCE_SQL = "delete from city where id = ?;";
    private static final String UPDATE_PROVINCES_SQL = "update city set country_name=?, total_cases=?,active_cases=?, total_recovered=?, total_death=? where id = ?;";
    private static final String DELETE_ALL_PROVINCES = "DELETE FROM city;";
    private static final String RESET_PROVINCE_ID = "ALTER TABLE city AUTO_INCREMENT = 1;";

    private static final String SELECT_TOTAL = "SELECT id,country_name, total_cases, active_cases, total_recovered, total_death FROM city WHERE country_name = \"Vietnam\"";
    private static final String SELECT_ALL_PROVINCE = "select * from city;";

    private static final String SELECT_PROVINCE_BY_ID = "select id,country_name, total_cases, active_cases,total_recovered, total_death from city where id =?";

    public VietnamDAO() throws SQLException{
    }

    @Override
    public boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;

        deleteAllProvince();
        resetProvincesId();

        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
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
                    if (b == 6) {
                        j = j + 3;
                        b = 1;
                    } else if (b == 1) {
                        statement.setString(1, temp);
                    } else if (b == 2) {
                        statement.setInt(2, temp2);
                    } else if (b == 3) {
                        statement.setInt(3, temp2);
                    } else if (b == 4) {
                        statement.setInt(4, temp2);
                    } else if (b == 5) {
                        statement.setInt(5, temp2);
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

    @Override
    public Vietnam selectProvince(int id) {
        Vietnam vietnam = null;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int active_cases = rs.getInt("active_cases");
                int total_recovered = rs.getInt("total_recovered");
                int total_death = rs.getInt("total_death");

                vietnam = new Vietnam(id, country_name, total_cases,active_cases, total_recovered,total_death);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vietnam;
    }

    @Override
    public boolean deleteAllProvince() throws SQLException {
        boolean rowAllDeleted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_PROVINCES);) {
            rowAllDeleted = statement.executeUpdate() > 0;
            System.out.println(statement);
        }
        return rowAllDeleted;
    }

    public List<Vietnam> listProvinceTotal() {
        List<Vietnam> total = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int active_cases = rs.getInt("active_cases");
                int total_recovered = rs.getInt("total_recovered");
                int total_death = rs.getInt("total_death");

                total.add(new Vietnam(id, country_name, total_cases,active_cases,  total_recovered, total_death));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }

    public List<Vietnam> selectAllProvince() {
        List<Vietnam> provinces = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                int total_cases = rs.getInt("total_cases");
                int active_cases = rs.getInt("active_cases");
                int total_recovered = rs.getInt("total_recovered");
                int total_death = rs.getInt("total_death");
                provinces.add(new Vietnam(id, country_name,total_cases, active_cases,total_recovered,total_death));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return provinces;
    }

    public boolean deleteProvince(int id) throws SQLException {
        boolean rowDeleted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROVINCE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        }
        return rowDeleted;
    }


    public boolean resetProvincesId() throws SQLException {
        boolean tableReserted;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_PROVINCE_ID);) {
            tableReserted = statement.executeUpdate() > 0;
            System.out.println(statement);
        }
        return tableReserted;
    }

    @Override
    public boolean insertProvince(Vietnam v) throws SQLException {
        boolean a = false;
        String sql = "SELECT * FROM city WHERE country_name = ?";
        DBConnection dbhelper = DBConnection.getDBHelper();
		Connection connection = dbhelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, v.getCountry_name());

        try (PreparedStatement checkAccountExists = connection.prepareStatement(sql)) {
            checkAccountExists.setString(1, v.getCountry_name());
            try (ResultSet rs = checkAccountExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Account Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_PROVINCE_SQL)) {
                        insert.setString(1, v.getCountry_name());
                        insert.setInt(2, v.getTotal_cases());
                        insert.setInt(3, v.getActive_cases());
                        insert.setInt(4, v.getTotal_recovered());
                        insert.setInt(5, v.getTotal_death());

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
    @Override
    public boolean updateProvince(Vietnam v) throws SQLException {
        boolean rowUpdated;
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROVINCES_SQL);) {
            statement.setString(1,v.getCountry_name());
            statement.setInt(2, v.getTotal_cases());
            statement.setInt(3, v.getActive_cases());
            statement.setInt(4, v.getTotal_recovered());
            statement.setInt(5, v.getTotal_death());
            statement.setInt(6, v.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public void close() throws Exception {

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
}
