package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final String url = "jdbc:mysql://localhost:3306/main";
    private static final String user = "root";
    private static final String DBpassword = "root";

    public static void main(String[] args) throws IOException {

        BD bd = new BD();
        bd.addUser("Daniil", "1234");
        System.out.println(bd.getUserByLogin("Daniil", "1234"));
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            try (
                    Socket socket = serverSocket.accept();
                    BufferedWriter writer =
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            socket.getOutputStream()));
                    BufferedReader reader =
                            new BufferedReader(
                                    new InputStreamReader(
                                            socket.getInputStream()));
                    )
            {
                String request = reader.readLine();
                System.out.println(request);

            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

}

