package Practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnection extends Thread {

    Socket socket;
    MyServer server;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun = true;

    public ServerConnection(Socket s, MyServer server) {
        super("Server connection thread");
        socket = s;
        this.server = server;
    }

    public void sendStringtoClient(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendStringtoAllClients(String text) {
        for (int i = 0; i < server.connections.size(); i++) {
            ServerConnection sc = server.connections.get(i);

            sc.sendStringtoClient(server.names.get(i) + " : " + text);
        }
    }

    public void run() {
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            while (shouldRun) {
                while (din.available() == 0) {
                    Thread.sleep(1);
                    String textIn = din.readUTF();
                    sendStringtoAllClients(textIn);
                }
            }
            din.close();
            dout.close();

        } catch (IOException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
