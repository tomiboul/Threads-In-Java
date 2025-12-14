package tomiboul;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Counter> counters = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            accounts.add(new Account(i, i*100));
        }
        for (int i = 0; i < 3; i++) {
            counters.add(new Counter(i, accounts));
        }

        Thread t1 = new Thread(counters.get(0));
        Thread t2 = new Thread(counters.get(1));
        Thread t3 = new Thread(counters.get(2));
        t1.start();
        t2.start();
        t3.start();

    }
}