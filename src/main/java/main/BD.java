package main;

import java.sql.*;
import java.util.Objects;

public class BD {
    private static final String url = "jdbc:mysql://localhost:3306/PI";
    private static final String user = "root";
    private static final String DBpassword = "root";

    public String getUserByLogin(String username, String userPassword){
        String query = new String("""
                SELECT * FROM PI.Dim_Users WHERE Login = ?
                """);

        try (
                // opening database connection to MySQL server
                Connection con = DriverManager.getConnection(url, user, DBpassword);

                // getting Statement object to execute query
                PreparedStatement preparedStatement = con.prepareStatement(query);)

        {

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String password = rs.getString(3);
                if (Objects.equals(password, userPassword)) {
                    return rs.getString(2);
                } else {
                    return "Неверный пароль";
                }
            } else {
                return "Пользователь не существует";
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public void addUser(String username, String userPassword) {
        String query = """
                        INSERT PI.Dim_Users(Login, Password)
                        VALUES (?, ?);
                        """;
        try (
                Connection con = DriverManager.getConnection(url, user, DBpassword);
                PreparedStatement preparedStatement = con.prepareStatement(query);)
        {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userPassword);
            int rows = preparedStatement.executeUpdate();
            System.out.println("User added");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

}
