package tomiboul;

public class Lecteur implements Runnable {
    private String name ;
    private Buffer buffer;

    public Lecteur(String name, Buffer buffer) {
        this.name = name;
        this.buffer = buffer;
    }
    @Override
    public void run() {
        while(true){
            System.out.println("\n Lecteur " + name + " is running");
            buffer.startLecture();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            buffer.endLecture();
            System.out.println("\n Lecteur " + name + " stops");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
