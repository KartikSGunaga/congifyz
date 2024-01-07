package task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server01 {
    public static void main(String[] args) {
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader inputReader;
        final PrintWriter outputWriter;
        final Scanner scanner = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(5000);
            clientSocket = serverSocket.accept();
            outputWriter = new PrintWriter(clientSocket.getOutputStream());
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread senderThread = new Thread(() -> {
                String message;
                while (true) {
                    message = scanner.nextLine();
                    outputWriter.println(message);
                    outputWriter.flush();
                }
            });
            senderThread.start();

            Thread receiveThread = new Thread(() -> {
                String message;
                try {
                    message = inputReader.readLine();
                    while (message != null) {
                        System.out.println("Client: " + message);
                        message = inputReader.readLine();
                    }

                    System.out.println("Client disconnected");

                    outputWriter.close();
                    clientSocket.close();
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
