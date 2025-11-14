package tomiboul;

import java.util.Arrays;

public class Buffer {
    private int[] buffer = new int[5];
    private boolean full = false;
    private boolean empty = true;

    public Buffer() {}

    public Buffer(int[] buffer,  boolean full,  boolean empty) {
        this.buffer = buffer;
        this.full = full;
        this.empty = empty;
    }

    public synchronized void put(int value) {
        while (full) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Buffer was interrupted : 1");
            }
        }
        addInBuffer(value);
        empty = false;
        notifyAll();
    }

    public synchronized void remove(){
        while(empty){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Buffer was interrupted : 2");
            }
        }
        removeInBuffer();
        full = false;
        notifyAll();
    }


    private void addInBuffer(int value) {
        boolean bufferReallyFull = true;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == 0) {
                buffer[i] = value;
                bufferReallyFull = false;
                if (buffer[buffer.length - 1] != 0) {
                    full = true;
                }
                break;
            }
        }
        assert !bufferReallyFull : "buffer is full";
    }

    public void removeInBuffer() {
        assert buffer.length > 1 : "buffer doesn't contain more than 1 element";
        buffer = Arrays.copyOfRange(buffer, 1,  buffer.length);
        if (buffer[0] == 0){
            empty = true;
        }
    }


    public int[] getBuffer() {
        return buffer;
    }
    public void setBuffer(int[] buffer) {
        this.buffer = buffer;
    }
    public void printBuffer(){
        StringBuilder sb = new StringBuilder();
        sb.append("Buffer: ");
        for (int i = 0; i < buffer.length; i++) {
            if (i != buffer.length-1 ){
                sb.append(buffer[i]).append(", ");
            }
            else  {
                sb.append(buffer[i]);
            }
        }
        System.out.println(sb);
    }
}
