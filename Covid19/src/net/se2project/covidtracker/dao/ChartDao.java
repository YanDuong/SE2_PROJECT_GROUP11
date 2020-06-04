package net.se2project.covidtracker.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;
import connect.DBConnection;
import daoi.ChartDAOI;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.se2project.covidtracker.model.Chart;

public class ChartDao implements AutoCloseable, ChartDAOI {

    private static final String INSERT_VIETNAM_CASES_SQL = "INSERT INTO dailycase (name, date, year, cases) VALUES (?, ?, ?, ?);";
    private static final String DELETE_ALL_PROVINCES = "DELETE FROM dailycase;";
    private static final String RESET_PROVINCE_ID = "ALTER TABLE dailycase AUTO_INCREMENT = 1;";
    private static final String SELECT_ALL_VIETNAM_CHART = "SELECT id,name, date, year, cases from dailycase WHERE name=\"Vietnam\"";
    private static final String SELECT_ALL_WORLD_CHART = "SELECT id,name, date, year, cases from dailycase WHERE name=\"World\"";

    public ChartDao() {
    }

    @Override
    public void autoUpdateChart() throws SQLException, IOException, NumberFormatException, ParseException {
        deleteAllChart();
        resetChartId();

        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_VIETNAM_CASES_SQL)) {
            try {
            // CSV file delimiter
            String DELIMITER = ",";
                System.out.println("run");
            // create a reader
            BufferedReader br = Files.newBufferedReader(Paths.get("/Users/khangtu/IdeaProjects/Covid19/src/daily-cases-covid-19.csv"));

            // read the file line by line
            String line;
            int temp2 = 0;
            while ((line = br.readLine()) != null) {
                // convert line into tokens
                String[] tokens = line.split(DELIMITER);
                // print all tokens
                int b = 0;
                for (String token : tokens) {
                    b += 1;
                    if (b == 6) {
                        b = 1;
                    }else if (b == 1){
                        if(token.equals("Vietnam") || token.equals("North America") || token.equals("Europe")
                                || token.equals("South America")|| token.equals("Asia")|| token.equals("Africa")|| token.equals("Oceania")
                                || token.equals("World")|| token.equals("USA")
                        ){
                            statement.setString(1, token);
                            System.out.println(statement);
                        }else {
                            break;
                        }
                    }else if(b == 3){
                        if (containsCharacter(token)){
                            token = token.replaceAll("\"", "");
                            token = token.replaceAll(" ", "");
                        }
                        statement.setString(2, token);
                        System.out.println(statement);

                    }else if(b == 4){
                        if (containsCharacter(token)){
                            token = token.replaceAll("\"", "");
                            token = token.replaceAll(" ", "");

                        }
                        statement.setString(3, token);
                        System.out.println(statement);

                    }else if(b == 5){
                        int a = Integer.parseInt(token);
                        statement.setInt(4, a);
                        System.out.println(statement);
                        statement.executeUpdate();
                    }
                }
            }
            // close the reader
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }

    public static boolean containsCharacter(String s) {
        boolean containsCharacter = false;
        if (s.contains("\"")){
            containsCharacter = true;
        }
        return containsCharacter;
    }

    @Override
    public void deleteAllChart() throws SQLException {
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ALL_PROVINCES);) {
            statement.executeUpdate();
            System.out.println(statement);
        }
    }
    
    @Override
    public List<Chart> selectAllChart() {
        List<Chart> charts = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VIETNAM_CHART);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String year = rs.getString("year");
                int cases = rs.getInt("cases");
                charts.add(new Chart(id, name,date, year,cases));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return charts;
    }
    @Override
    public boolean resetChartId() throws SQLException {
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
    public List<Chart> selectAllWorldChart() {
        List<Chart> vietnamCharts = new ArrayList<>();
        try (DBConnection dbhelper = DBConnection.getDBHelper();
        		Connection connection = dbhelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WORLD_CHART);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String year = rs.getString("year");
                int cases = rs.getInt("cases");
                vietnamCharts.add(new Chart(id, name,date, year,cases ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return vietnamCharts;
    }

    @Override
    public void close() {
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
