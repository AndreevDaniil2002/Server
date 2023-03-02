package main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
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

