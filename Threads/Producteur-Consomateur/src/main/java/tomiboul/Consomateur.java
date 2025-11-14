package tomiboul;

public class Consomateur implements Runnable{

    private int consomateur;
    private Buffer buffer;
    public Consomateur(int consomateur, Buffer buffer) {
        this.consomateur = consomateur;
        this.buffer = buffer;
    }


    public  void run() {
        while(true){
            try {
                buffer.remove();
                System.out.println("Consumer "+ consomateur+ " remove the first value in the buffer");
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println("Error in producer");
            }
        }

    }
}
