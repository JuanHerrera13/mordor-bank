package entities;

public class Account {
    private double balance;
    private int agency;
    private int number;
    private Client holder = new Client();
    private static int accountsTotal;

    public Account() {

    }

    public Account(int agency, int number) {
        accountsTotal++;
        System.out.println("The number of accounts created is " + accountsTotal);
        this.agency = agency;
        this.number = number;
        System.out.println("Creating account with number " + number);
    }

    public void deposit(double value) {
        this.balance += value;
    }

    public boolean withdraw(double value) {
        if (balance >= value) {
            balance -= value;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(double value, Account destiny) {
        if (balance >= value) {
            balance -= value;
            destiny.deposit(value);
            return true;
        }
        return false;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            System.out.println("Value can't be less than or equal to zero");
            return;
        }
        this.number = number;
    }

    public int getAgency() {
        return this.agency;
    }

    public void setAgency(int agency) {
        if (agency <= 0) {
            System.out.println("Value can't be less than or equal to zero");
            return;
        }
        this.agency = agency;
    }

    public Client getHolder() {
        return holder;
    }

    public void setHolder(Client holder) {
        this.holder = holder;
    }

    public static int getAccountsTotal() {
        return Account.accountsTotal;
    }
}
