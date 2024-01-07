package task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    @Override
    public void run() {
        try{
            client = new Socket("127.0.0.1", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            InputHandler inHandler = new InputHandler();
            Thread thread1 = new Thread(inHandler);
            thread1.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage);
            }
        } catch (Exception e){
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try{
            in.close();
            out.close();

            if (!client.isClosed())
                client.close();
        } catch (Exception e) {
            // ignore
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {
            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while (!done){
                    String message = inReader.readLine();
                    if (message.equals("/quit")){
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(message);
                    }
                }
            } catch (Exception e){
                shutdown();
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
//        client.run();
        new Thread(client).start();
    }
}
