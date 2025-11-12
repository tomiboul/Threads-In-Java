package tomiboul;

public class Philosophe implements Runnable{
    private int philosophe;
    private State state;
    private Fork forkLeft;
    private Fork forkRight;

    Philosophe(int philosophe, State state, Fork forkLeft, Fork forkRight) {
        assert forkLeft.getId() == philosophe;
        assert forkRight.getId() == (philosophe+1)%5;

        this.philosophe = philosophe;
        this.state = state;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
    }

    public synchronized void thinkingToStarving() throws InterruptedException {
        state = State.STARVING;
        long start = System.currentTimeMillis();
        long MAX_WAIT = 2000;   // 2 sec d att

        boolean ate = false;

        // applique la méthode COURTOIS
        while (!ate &&  System.currentTimeMillis() - start < MAX_WAIT) {
            if (philosophe%2==0) {
                if (tryTakeForkRight()){
                    if (tryTakeForkLeft()){
                        eat();
                        ate = true;
                    }else {
                        forkRight.releaseFork();
                    }
                }
            }else {
                if (tryTakeForkLeft()){
                    if (tryTakeForkRight()){
                        eat();
                        ate = true;
                    }else {
                        forkLeft.releaseFork();
                    }
                }
            }
            if (ate == false){
                Thread.sleep(100);
            }
        }
        if (ate == false){
            state = State.THINKING;
        }
    }


    public void eat() throws InterruptedException {
        state = State.EATING;
        System.out.println("Philosophe " + philosophe + " is eating");
        Thread.sleep(2000);
        forkLeft.releaseFork();
        forkRight.releaseFork();
        System.out.println("Philosophe " + philosophe + " is stoping to eat");
        state = State.THINKING;
        System.out.println("Philosophe " + philosophe + " is thinking");
    }


    public boolean tryTakeForkLeft() throws InterruptedException {
        try {
            forkLeft.takeFork();
            return true;
        }catch(InterruptedException e){
            return false;
        }
    }
    public boolean tryTakeForkRight() throws InterruptedException {
        try {
            forkRight.takeFork();
            return true;
        }catch(InterruptedException e){
            return false;
        }
    }

    public void think() throws InterruptedException {
        state = State.THINKING;
        System.out.println("\nPhilosophe " + philosophe + " is now thinking");
        Thread.sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) { // tourne en boucle
                think();
                thinkingToStarving(); // essaie de manger
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public enum State {
        THINKING,
        STARVING,
        EATING,
    }

    public void print(){
        System.out.println("\n\nPhilosophe : " + philosophe
                + "\n State : " + state
                + "\n ForkLeft : " + forkLeft
                + "\n ForkRight : " + forkRight);
    }
}
