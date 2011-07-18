package rds.testbed;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 4:39 PM
 */
public class URLConnectionToServerSocket {
    public static void main(String[] args) {
//        Executors.newSingleThreadExecutor().submit(new Server());
        try{
                URL url = new URL("http://192.168.1.101:62666");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.connect();
                OutputStream outputStream = connection.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream);
                writer.print("Hello");
                writer.print("Hello");
                writer.print("Hello");
                writer.print("Hello");
            ((HttpURLConnection) connection).getResponseCode();
                writer.print("Hello");
                writer.print("Hello");
                writer.print("Hello");
                writer.print("Hello");
                writer.print("Hello");
                System.out.println("should have worked");
                writer.flush();
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
    }
}

class Server implements Runnable {
    private boolean doRun = true;

    public void run() {
        try{

                ServerSocket serverSock = new ServerSocket(62666);

                while(doRun){
                    Socket sock = serverSock.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                    PrintWriter writer = new PrintWriter(sock.getOutputStream());

                    System.out.println(reader.readLine() + " From IP: " + sock.getInetAddress() + "\n");
                    writer.println("Testing123");

                    writer.close();

                    reader.close();

                }
            }catch(IOException e){
                e.printStackTrace();
            }

    }
}