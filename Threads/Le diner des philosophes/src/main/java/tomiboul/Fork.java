package tomiboul;

public class Fork {
    private int id;
    private boolean took = false;


    public  Fork(int id, boolean took) {
        this.id = id;
        this.took = took;
    }
    // make method to take fork
    public synchronized void takeFork() throws InterruptedException {
        while(took){
            try {
                wait();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        took = true;
        notifyAll();
    }
    // make methode to release fork
    public synchronized void releaseFork() {
        took = false;
        notifyAll();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isTook() {
        return took;
    }
    public void setTook(boolean took) {
        this.took = took;
    }
    public void print(){
        System.out.println("\n\nId : " + id
                + "\n Took : " + took);
    }
}
