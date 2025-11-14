package tomiboul;

import java.util.Random;

public class Producteur implements Runnable {
    private Random rand = new Random(); // rand.nextInt(100) + 1;

    private int producteur;
    private Buffer buffer;

    public Producteur(int producteur, Buffer buffer) {
        this.producteur = producteur;
        this.buffer = buffer;
    }

    public  void run() {
        while(true) {
            try {
                int randomValue = rand.nextInt(100) + 1;
                buffer.put(randomValue);
                System.out.println("Producer "+ producteur+ " add " + randomValue + " in the buffer");
                Thread.sleep(2000);

            }catch (Exception e){
                System.out.println("Error in producer");
            }
        }
    }

}
