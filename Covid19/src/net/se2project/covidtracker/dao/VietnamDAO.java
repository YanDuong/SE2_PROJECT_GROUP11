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

import static connect.DBConnect.getConnection;

public class VietnamDAO implements AutoCloseable {

    private static final String INSERT_PROVINCE_SQL = "INSERT INTO cities (country_name, total_cases,active_cases, total_recovered, total_death) VALUES (?, ?, ?, ?, ?);";

    private static final String DELETE_PROVINCE_SQL = "delete from cities where id = ?;";
    private static final String UPDATE_PROVINCES_SQL = "update cities set country_name=?, total_cases=?,active_cases=?, total_recovered=?, total_death=? where id = ?;";
    private static final String DELETE_ALL_PROVINCES = "DELETE FROM cities;";
    private static final String RESET_PROVINCE_ID = "ALTER TABLE cities AUTO_INCREMENT = 1;";

    private static final String SELECT_TOTAL = "SELECT id,country_name, total_cases, active_cases, total_recovered, total_death FROM cities WHERE country_name = \"Vietnam\"";
    private static final String SELECT_ALL_PROVINCE = "select * from cities;";

    private static final String SELECT_PROVINCE_BY_ID = "select id,country_name, total_cases, active_cases,total_recovered, total_death from cities where id =?";

    public VietnamDAO() throws SQLException {
    }

    public boolean autoUpdateVietnam() throws SQLException, IOException, NumberFormatException, ParseException {
        boolean rowAutoUpdated = false;

        deleteAllProvince();
        resetProvincesId();

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

    public Vietnam selectProvince(int id) {
        Vietnam vietnam = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROVINCE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String active_cases = rs.getString("active_cases");
                String total_recovered = rs.getString("total_recovered");
                String total_death = rs.getString("total_death");

                vietnam = new Vietnam(id, country_name, total_cases,active_cases, total_recovered,total_death);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vietnam;
    }


    public boolean deleteAllProvince() throws SQLException {
        boolean rowAllDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_PROVINCES);) {
            rowAllDeleted = statement.executeUpdate() > 0;
        }
        return rowAllDeleted;
    }


    public List<Vietnam> listProvinceTotal() {
        List<Vietnam> total = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOTAL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String active_cases = rs.getString("active_cases");
                String total_recovered = rs.getString("total_recovered");
                String total_death = rs.getString("total_death");

                total.add(new Vietnam(id, country_name, total_cases,active_cases,  total_recovered, total_death));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }


    public List<Vietnam> selectAllProvince() {
        List<Vietnam> provinces = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROVINCE);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String country_name = rs.getString("country_name");
                String total_cases = rs.getString("total_cases");
                String active_cases = rs.getString("active_cases");
                String total_recovered = rs.getString("total_recovered");
                String total_death = rs.getString("total_death");
                provinces.add(new Vietnam(id, country_name,total_cases, active_cases,total_recovered,total_death));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return provinces;
    }


    public boolean deleteProvince(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PROVINCE_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;

        }
        return rowDeleted;
    }


    public boolean resetProvincesId() throws SQLException {
        boolean tableReserted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(RESET_PROVINCE_ID);) {
            tableReserted = statement.executeUpdate() > 0;
        }
        return tableReserted;
    }


    public boolean insertProvince(Vietnam province) throws SQLException {
        boolean a = false;
        String sql = "SELECT * FROM cities WHERE country_name = ?";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, province.getCountry_name());

        try (PreparedStatement checkAccountExists = connection.prepareStatement(sql)) {
            checkAccountExists.setString(1, province.getCountry_name());
            try (ResultSet rs = checkAccountExists.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Account Existed");
                } else {
                    try (PreparedStatement insert = connection.prepareStatement(
                            INSERT_PROVINCE_SQL)) {
                        insert.setString(1, province.getCountry_name());
                        insert.setString(2, province.getTotal_cases());
                        insert.setString(3, province.getActive_cases());
                        insert.setString(4, province.getTotal_recovered());
                        insert.setString(5, province.getTotal_death());

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

    public boolean updateProvince(Vietnam province) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PROVINCES_SQL);) {
            statement.setString(1, province.getCountry_name());
            statement.setString(2, province.getTotal_cases());
            statement.setString(3, province.getActive_cases());
            statement.setString(4, province.getTotal_recovered());
            statement.setString(5, province.getTotal_death());
            statement.setInt(6, province.getId());

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
