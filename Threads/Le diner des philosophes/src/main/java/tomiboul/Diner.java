package tomiboul;

public class Diner {
    private static Philosophe[] philosophes = new Philosophe[5];
    private static Fork[] forks = new Fork[5];


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i, false);
        }
        for (int i = 0; i < 5; i++) {
            philosophes[i] = new Philosophe(i,
                    Philosophe.State.THINKING,
                    forks[i],
                    forks[(i+1)%5]);
        }

        Thread t0 = new Thread(philosophes[0]);
        Thread t1 = new Thread(philosophes[1]);
        Thread t2 = new Thread(philosophes[2]);
        Thread t3 = new Thread(philosophes[3]);
        Thread t4 = new Thread(philosophes[4]);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
