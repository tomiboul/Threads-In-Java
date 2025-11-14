package tomiboul;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programme started");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of producers : ");
        String numberOfProducers = scanner.nextLine();
        System.out.print("Number of consumers : ");
        String numberOfConsumers = scanner.nextLine();


        Buffer buffer = new Buffer();
        ArrayList<Producteur> producteurs = new ArrayList<Producteur>();
        ArrayList<Consomateur> consomateurs = new ArrayList<Consomateur>();
        ArrayList<Thread> threads = new ArrayList();


        for(int i = 0; i < Integer.parseInt(numberOfProducers); i++) {
            Producteur producteur = new Producteur(i, buffer);
            producteurs.add(producteur);
            Thread thread = new Thread(producteur);
            threads.add(thread);

        }
        for(int i = 0; i < Integer.parseInt(numberOfConsumers); i++) {
            Consomateur consomateur = new Consomateur(i, buffer);
            consomateurs.add(consomateur);
            Thread thread = new Thread(consomateur);
            threads.add(thread);
        }

        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }


        System.out.println("Programme ended");
    }
}
