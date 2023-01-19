import java.io.IOException;
import java.util.Scanner;

/**
 * deux threads, un pour le serveur et un pour le client. Chacun de ces threads exécute le code pour démarrer le serveur ou le client.
 * En utilisant des threads, le code du serveur et du client s'exécute en parallèle, ce qui signifie que le serveur et le client sont en cours d'exécution en même temps,
 * et vous pouvez avoir accès aux logs de chacun d'eux à tout moment.
 * Il est important de noter que lorsque vous utilisez des threads, vous devez gérer soigneusement les problèmes de synchronisation
 * qui peuvent survenir lorsque plusieurs threads accèdent aux mêmes données simultanément.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Lancer le serveur et le client");
        System.out.println("2. Seulement lancer le client");
        System.out.print("Votre choix : ");

        int choice = sc.nextInt();
        sc.nextLine(); // pour vider le buffer du scanner

        if (choice == 1) {
            // Code pour lancer le serveur et le client en parallèle
            Thread serverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Server server = new Server();
                    try {
                        server.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            serverThread.start();

            Thread clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Client client = new Client();
                    try {
                        client.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            clientThread.start();
        } else if (choice == 2) {
            // Code pour lancer seulement le client
            new Client().start();
        } else {
            System.out.println("Choix non valide, veuillez réessayer.");
        }
    }
}
