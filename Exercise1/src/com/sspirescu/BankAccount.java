package com.sspirescu;

/**
 * Created by timbuchalka on 16/08/2016.
 */
public class BankAccount {

    private double balance;
    private String accountNumber;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized void withdraw(double amount) {
        balance -= amount;
    }

//    public void deposit(double amount) {
//        synchronized (this) {
//            balance += amount;
//        }
//    }
//
//    public void withdraw(double amount) {
//        synchronized (this) {
//            balance -= amount;
//        }
//    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
//
//        String auxAcc;
//        synchronized (this) {
//            auxAcc = accountNumber;
//        }
//        return auxAcc;
          return accountNumber;
    }

    public void printAccountNumber() {
//        String auxAcc;
//        synchronized (this) {
//            auxAcc = accountNumber;
//        }

//        System.out.println("Account number = " + auxAcc);
        System.out.println("Account number = " + accountNumber);

        //No need to syncronize these fields, as they ar only Read.
        // Synchronizing would harm performance

    }
}