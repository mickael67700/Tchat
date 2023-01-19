import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Socket s = new Socket("127.0.0.1", 5217);

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        System.out.print("Entrez votre nom d'utilisateur : ");
        String loginName = sc.nextLine();
        dos.writeUTF(loginName);

        System.out.println (dis.readUTF());
        System.out.println(dis.readUTF());
        while (true) {
            System.out.print("Entrez votre message : ");
            String msg = sc.nextLine();
            dos.writeUTF(msg);
        }
    }
}
