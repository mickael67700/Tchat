import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandler extends Thread {

    DataInputStream dis;
    DataOutputStream dos;
    Socket s;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {
        String loginName = "";

        try {
            loginName = dis.readUTF();
            Server.LoginNames.add(loginName);

            dos.writeUTF("Vous êtes connecté");
            dos.writeUTF("Bienvenue " + loginName);

            Server.ClientSockets.add(s);

            broadcast(loginName + " a rejoint le chat");

            while (true) {
                String msg = dis.readUTF();
                broadcast(loginName + " : " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String msg) throws IOException {
        for (int i = 0; i < Server.ClientSockets.size(); i++) {
            Socket sc = Server.ClientSockets.get(i);
            DataOutputStream dout = new DataOutputStream(sc.getOutputStream());
            dout.writeUTF(msg);
        }
    }
}

