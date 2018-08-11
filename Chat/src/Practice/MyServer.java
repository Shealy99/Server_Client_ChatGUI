package Practice;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MyServer {

    ServerSocket ss;
    boolean shouldRun = true;
    ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    ArrayList<String> names = new ArrayList<>();

    public static void main(String[] args) {
        new MyServer();
    }

    public void close() {
        System.exit(0);
    }

    public MyServer() {
        names.add("Shila");
        names.add("I dyti");

        try {
            ss = new ServerSocket(2805);
            while (shouldRun) {

                Socket s = ss.accept();
                ServerConnection sc = new ServerConnection(s, this);
                sc.start();
                connections.add(sc);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
