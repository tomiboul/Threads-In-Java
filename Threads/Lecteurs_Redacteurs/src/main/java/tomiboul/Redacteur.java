package tomiboul;

public class Redacteur implements Runnable {
    private String name ;
    private Buffer buffer;
    private int i = 0;
    public Redacteur(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while(true){
            System.out.println("\n Redacteur " + name + " is running");
            buffer.startRedaction();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            buffer.endRedaction();
            System.out.println("\n Redacteur " + name + " stops");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
