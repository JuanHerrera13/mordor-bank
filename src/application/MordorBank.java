package application;

import entities.Account;

import java.util.ArrayList;
import java.util.Scanner;

public class MordorBank {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    showAccountAndBalance();
                case 6:
                    System.out.println("Exiting Mordor Bank. Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("Choose an option:");
        System.out.println("1. Create account");
        System.out.println("2. Make a deposit");
        System.out.println("3. Make a withdrawal");
        System.out.println("4. Make a transfer");
        System.out.println("5. Show account and balance");
        System.out.println("6. Exit");
    }

    public static void createAccount() {
        System.out.println("----- Create Account -----");
        System.out.print("Enter agency number: ");
        int agency = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter holder's CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Enter holder's profession: ");
        String profession = scanner.nextLine();

        Account account = new Account(agency, number);
        account.getHolder().setName(name);
        account.getHolder().setSsn(cpf);
        account.getHolder().setProfession(profession);

        accounts.add(account);
        System.out.println("Account created successfully.");
    }

    public static void deposit() {
        System.out.println("----- Deposit -----");
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        Account account = findAccount(number);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: " + account.getBalance());
        } else {
            System.out.println("Account not found. Please try again.");
        }
    }

    public static void withdraw() {
        System.out.println("----- Withdraw -----");
        System.out.print("Enter account number: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        Account account = findAccount(number);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            boolean success = account.withdraw(amount);
            if (success) {
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance. Please try again.");
            }
        } else {
            System.out.println("Account not found. Please try again.");
        }
    }

    public static void transfer() {
        System.out.println("----- Transfer -----");
        System.out.print("Enter source account number: ");
        int sourceNumber = scanner.nextInt();
        scanner.nextLine();
        Account sourceAccount = findAccount(sourceNumber);

        if (sourceAccount != null) {
            System.out.print("Enter destination account number: ");
            int destinationNumber = scanner.nextInt();
            scanner.nextLine();
            Account destinationAccount = findAccount(destinationNumber);

            if (destinationAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                boolean success = sourceAccount.transfer(amount, destinationAccount);
                if (success) {
                    System.out.println("Transfer successful.");
                    System.out.println("Source account balance: " + sourceAccount.getBalance());
                    System.out.println("Destination account balance: " + destinationAccount.getBalance());
                } else {
                    System.out.println("Insufficient balance in source account. Please try again.");
                }
            } else {
                System.out.println("Destination account not found. Please try again.");
            }
        } else {
            System.out.println("Source account not found. Please try again.");
        }
    }

    private static Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private static void showAccountAndBalance() {
        while (true) {
            System.out.println("Enter account number:");
            int accountNumber = scanner.nextInt();
            scanner.nextLine();

            Account account = findAccount(accountNumber);

            if (account != null) {
                System.out.println("Account number: " + account.getNumber());
                System.out.println("Holder name: " + account.getHolder().getName());
                System.out.println("Current balance: R$" + account.getBalance());
            } else {
                System.out.println("Account not found.");
            }

            System.out.println("Press 'q' to quit or any other key to continue.");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }
    }
}
