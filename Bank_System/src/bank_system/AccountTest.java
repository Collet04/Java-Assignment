package bank_system;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Abstract class Bank:
 * ---------------------
 * - Acts as a blueprint for all types of bank accounts.
 * - Holds common "state" (data: account name, balance).
 * - Declares abstract methods (deposit, withdraw, getBalance)
 *   which subclasses must implement.
 */
abstract class Bank {
    // Fields (State of the account)
    protected String accountName;  // Stores the account holder's name
    protected double balance;      // Stores the account balance

    // Constructor to initialize state when an object is created
    public Bank(String accountName, double balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    // Abstract methods (Behaviors)
    // These must be implemented in subclasses
    public abstract void deposit(double amount);   // Add money to account
    public abstract void withdraw(double amount);  // Take money out of account
    public abstract double getBalance();           // Check balance
}

/**
 * Account class:
 * ------------------
 * - Extends (inherits from) Bank.
 * - Provides actual implementation of deposit, withdraw, and getBalance.
 * - Adds functionality to log transactions into a file (Bank.txt).
 */
class Account extends Bank {

    // Constructor: calls the parent (Bank) constructor
    public Account(String accountName, double balance) {
        super(accountName, balance);
    }

    /**
     * Deposit Method:
     * -----------------
     * - Only accepts positive amounts.
     * - Increases the balance by the deposit amount.
     * - Logs the successful deposit into Bank.txt.
     * - If deposit is invalid (negative or zero), prints error.
     */
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount; // Increase balance
            logTransaction("Deposited: " + amount + " | Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    /**
     * Withdraw Method:
     * -----------------
     * - Allows withdrawal only if:
     *      1. The amount is positive
     *      2. The amount is <= balance
     * - Deducts the withdrawal amount from balance if successful.
     * - Logs both successful and failed attempts in Bank.txt.
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount; // Deduct amount
            logTransaction("Withdrew: " + amount + " | Balance: " + balance);
        } else {
            System.out.println("Withdrawal amount exceeded account balance");
            logTransaction("Failed Withdrawal Attempt: " + amount + " | Balance unchanged: " + balance);
        }
    }

    /**
     * getBalance Method:
     * -------------------
     * - Returns the current balance of the account.
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * logTransaction Method:
     * -----------------------
     * - Private helper method.
     * - Appends transaction details into a text file named "Bank.txt".
     * - Each line has the account holder's name and the transaction performed.
     */
    private void logTransaction(String message) {
        try (FileWriter fw = new FileWriter("Bank.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(accountName + ": " + message);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

/**
 * AccountTest Class:
 * --------------------
 * - Contains the main() method.
 * - Used to test the banking functionality by:
 *      1. Creating an Account object.
 *      2. Performing deposits and withdrawals.
 *      3. Displaying final account balance.
 */
public class AccountTest {
    public static void main(String[] args) {
        // Create an account with initial balance of 1000
        Account acc1 = new Account("Alice Moyo", 1000);

        // Test deposit (Valid case)
        acc1.deposit(500);   // Adds 500, balance becomes 1500

        // Test deposit (Invalid case)
        acc1.deposit(-200);  // Invalid, ignored

        // Test withdraw (Valid case)
        acc1.withdraw(700);  // Deducts 700, balance becomes 800

        // Test withdraw (Invalid case - too much)
        acc1.withdraw(2000); // Fails, balance stays 800

        // Display final balance
        System.out.println("Final Balance: " + acc1.getBalance());
    }
}
