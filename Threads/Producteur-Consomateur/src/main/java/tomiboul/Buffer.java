package tomiboul;

import java.util.Arrays;

public class Buffer {
    private int[] buffer = new int[5];
    private boolean full = false;
    private boolean empty = true;

    public Buffer() {}

    public synchronized void put(int value) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Buffer was interrupted : put()");
            }
        }
        addInBuffer(value);
        empty = false;
        notifyAll();
    }

    public synchronized void remove() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Buffer was interrupted : remove()");
            }
        }
        removeInBuffer();
        full = false;
        notifyAll();
    }

    private void addInBuffer(int value) {
        boolean foundPlace = false;

        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == 0) {
                buffer[i] = value;
                foundPlace = true;
                if (isBufferFull()) {
                    full = true;
                }
                break;
            }
        }

        assert foundPlace : "Buffer FULL mais flag full était faux";
    }

    private void removeInBuffer() {
        buffer[0] = 0;

        for (int i = 0; i < buffer.length - 1; i++) {
            buffer[i] = buffer[i+1];
        }
        buffer[buffer.length - 1] = 0;

        if (isBufferEmpty()) {
            empty = true;
        }
    }

    private boolean isBufferFull() {
        for (int val : buffer) {
            if (val == 0) return false;
        }
        return true;
    }

    private boolean isBufferEmpty() {
        for (int val : buffer) {
            if (val != 0) return false;
        }
        return true;
    }

    public void printBuffer() {
        System.out.println("Buffer : " + Arrays.toString(buffer));
    }
}