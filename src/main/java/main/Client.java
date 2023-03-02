package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Statement;

public class Client /*extends AppCompatActivity*/ {
    private static final String URL = "jdbc:mysql://localhost:3306/sales";

    private static int id_global = 210;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) throws SQLException {

        Driver driver = new Driver();
        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();

        try (
                Socket socket = new Socket("127.0.0.1", 8080);
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream()
                                )
                        );
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );
        )
        {
            System.out.println("CLient started");
            String request = "Android";
            writer.write(request);
            writer.newLine();
            writer.flush();
            System.out.println("Messege sent");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}