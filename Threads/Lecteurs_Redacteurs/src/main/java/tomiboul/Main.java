package tomiboul;


public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Lecteur lec1 = new Lecteur("lec1", buffer);
        Lecteur lec2 = new Lecteur("lec2", buffer);
        Lecteur lec3 = new Lecteur("lec3", buffer);

        Redacteur red1 = new Redacteur("red1", buffer);
        Redacteur red2 = new Redacteur("red2", buffer);

        Thread t1 = new Thread(red1);
        Thread t2 = new Thread(red2);
        Thread t3 = new Thread(lec1);
        Thread t4 = new Thread(lec2);
        Thread t5 = new Thread(lec3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}