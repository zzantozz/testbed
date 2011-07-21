package rds.testbed;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/15/11
 * Time: 3:02 PM
 */
public class SerializeOverSocket {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws Exception {
        // Start a server to listen for a client
        executorService.submit(new Server());
        Thread.sleep(100);
        // Send an ArrayList from a client
        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        Socket s = new Socket();
        s.connect(new InetSocketAddress("localhost", 1234));
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        out.writeObject(integers);
        s.close();
    }

    static class Server implements Runnable {
        public void run() {
            try {
                ServerSocket server = new ServerSocket(1234);
                Socket clientSocket = server.accept();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                Object o = in.readObject();
                System.out.println("Received this object on the server: " + o);
                clientSocket.close();
                server.close();
                executorService.shutdown();
            } catch (IOException e) {
                // TODO: Write me
                throw new UnsupportedOperationException("Not written");
            } catch (ClassNotFoundException e) {
                // TODO: Write me
                throw new UnsupportedOperationException("Not written");
            }
        }
    }
}
