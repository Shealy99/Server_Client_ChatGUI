package Practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {

    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldRun = true;
    MyClient client;

    public ClientConnection(Socket socket, MyClient client) {
        s = socket;
        this.client = client;
        try {
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

        } catch (IOException ex) {
        }

    }

    public void sendStringtoServer(String data) {
        try {
            dout.writeUTF(data);
            dout.flush();

        } catch (IOException ex) {
        }
    }

    public void run() {
        while (shouldRun) {
            try {
                while (din.available() == 0) {
                    Thread.sleep(1);
                }
                String reply = din.readUTF();
                client.setText(reply);
            } catch (IOException ex) {
                close();

            } catch (InterruptedException ex) {
            }
        }
    }

    private void close() {
        try {
            din.close();
            dout.close();
            s.close();
        } catch (IOException ex) {
        }
    }

}
