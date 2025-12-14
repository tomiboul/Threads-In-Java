package tomiboul;

public class Account {
    private int id;
    private int solde;

    public Account(int id, int solde) {
        this.id = id;
        this.solde = solde;
    }

    public synchronized void deposit(int amount) {
        addMoney(amount);
        notifyAll();
        System.out.println("Account " + id + " deposited " + amount + " and have actually " + solde);
    }


    public synchronized void retrait(int amount) {
        while(solde - amount < 0) {
            try {
                wait();
            }catch(InterruptedException e) {}
        }
        retrieveMoney(amount);
        notifyAll();
        System.out.println("Account " + id + " retracted " + amount + " and have actually " + solde);
    }

    public static void virement(Account from, Account to, int amount) {
        Account first;
        Account second;
        if(from.getId() < to.getId()) {
            first = from;
            second = to;
        }else {
            first = to;
            second = from;
        }
        synchronized (first) {
            synchronized (second) {
                if (from.getSolde() - amount >= 0 ){
                    from.retrieveMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from.getId()+ " can do a virement to " + to.getId()  + " and have actually " + from.solde);
                }else  {
                    System.out.println(from.getId()+ " can't do a virement to " + to.getId()  + " and have actually " + from.solde);
                }

            }
        }
    }

    public void addMoney(int amount) {
        this.solde = this.solde + amount;
    }
    public void retrieveMoney(int amount) {
        this.solde = this.solde - amount;
    }
    public int getId() {
        return id;
    }
    public synchronized int getSolde() {
        return solde;
    }
}
