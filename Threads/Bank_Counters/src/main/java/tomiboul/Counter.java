package tomiboul;

import java.util.ArrayList;
import java.util.Random;

public class Counter implements Runnable{

    Random random = new Random();

    private int id;
    private ArrayList<Account> accounts;

    public Counter(int id, ArrayList<Account> accounts) {
        this.id = id;
        this.accounts = accounts;
    }


    @Override
    public void run() {
        while (true) {
            int randomAccount1 = random.nextInt(accounts.size());
            int randomAccount2 = random.nextInt(accounts.size());
            while(randomAccount1 == randomAccount2){
                randomAccount2 = random.nextInt(accounts.size());
            }

            int randomAction = random.nextInt(4);

            if(randomAction == 1){ // dépot d'argents
                accounts.get(randomAccount1).deposit(200);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (randomAction == 2){ // retrait d'argents
                accounts.get(randomAccount1).retrait(150);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (randomAction == 3) {
                Account account1 = accounts.get(randomAccount1);
                Account account2 = accounts.get(randomAccount2);
                Account.virement(account1, account2, 300);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
