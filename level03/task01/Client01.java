package task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client01 {
    public static void main(String[] args) {
        final Socket socket;
        final BufferedReader reader;
        final PrintWriter writer;
        final Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread senderThread = new Thread(() -> {
                String message;
                while (true) {
                    message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                }
            });
            senderThread.start();

            Thread receiverThread = new Thread(() -> {
                String message;
                try {
                    message = reader.readLine();
                    while (message != null) {
                        System.out.println("Server: " + message);
                        message = reader.readLine();
                    }
                    System.out.println("Server out of service");
                    writer.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
