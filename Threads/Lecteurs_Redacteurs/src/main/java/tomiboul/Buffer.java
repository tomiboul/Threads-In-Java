package tomiboul;

public class Buffer {

    private int lecteursActifs = 0;
    private int redacteursEnAttente = 0;
    private boolean redacteurActif = false;

    public synchronized void startLecture() {
        while (redacteurActif || redacteursEnAttente > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        lecteursActifs++;
    }

    public synchronized void endLecture() {
        lecteursActifs--;
        if (lecteursActifs == 0) {
            notifyAll();
        }
    }

    public synchronized void startRedaction() {
        redacteursEnAttente++;
        while (lecteursActifs > 0 || redacteurActif) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        redacteursEnAttente--;
        redacteurActif = true;
    }

    public synchronized void endRedaction() {
        redacteurActif = false;
        notifyAll();
    }
}
