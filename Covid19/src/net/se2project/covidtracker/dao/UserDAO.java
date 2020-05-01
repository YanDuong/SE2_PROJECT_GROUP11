package net.se2project.covidtracker.dao;
import java.sql.*;
import net.se2project.covidtracker.model.Admin;
import static connect.DBConnect.getConnection;

public class UserDAO {

    public Admin checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        Connection connection = getConnection();
        String sql = "SELECT * FROM admin WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        Admin admin = null;

        if (result.next()) {
            admin = new Admin();
            admin.setName(result.getString("name"));
            admin.setEmail(email);
        }

        connection.close();

        return admin;
    }

}
