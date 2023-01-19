import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void start() throws IOException {
        // Code pour démarrer le client.
        Scanner c = new Scanner(System.in);
        System.out.print("Entrez l'adresse IP du serveur : ");
        String serverIP = c.nextLine();
        try {
            // Utilisez l'adresse IP saisie pour se connecter au serveur
            Socket s = new Socket(serverIP, 5217);
            // Code pour envoyer et recevoir des messages
            Scanner sc = new Scanner(System.in);
            //
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            //
            System.out.print("Entrez votre nom d'utilisateur : ");
            String loginName = sc.nextLine();
            dos.writeUTF(loginName);
            //
            System.out.println(dis.readUTF());
            System.out.println(dis.readUTF());
            while (true) {
                System.out.print("Entrez votre message : ");
                String msg = sc.nextLine();
                dos.writeUTF(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
