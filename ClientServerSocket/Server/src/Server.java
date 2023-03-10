import zhukov.Phone;

import java.io.*;
import java.net.ServerSocket;


public class Server {

    public static void main(String[] args){

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");

            while (true) {

                Phone phone = new Phone(server);

                new Thread(() -> {
                    String request = phone.readLine();
                    System.out.println("Город: " + request);
                    String response = (int) (Math.random() * 30 - 10) + "";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    phone.writeLine(response);
                    System.out.println("Температура: " + response + " °C");
                    try{ phone.close(); } catch (IOException e ) { }

                }).start();
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

    }
}
