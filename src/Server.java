import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static ArrayList<Socket> ClientSockets;
    static ArrayList<String> LoginNames;

    public void start() throws IOException {
        // Code pour démarrer le serveur.
        ClientSockets = new ArrayList<Socket>();
        LoginNames = new ArrayList<String>();

        ServerSocket server = new ServerSocket(5217);
        Socket s;

        while (true) {
            s = server.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread t = new ClientHandler(s, dis, dos);
            t.start();
        }
    }
}

