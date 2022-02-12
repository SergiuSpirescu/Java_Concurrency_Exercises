package com.sspirescu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by timbuchalka on 16/08/2016.
 */
public class BankAccount {

    private double balance;
    private String accountNumber;

    private Lock lock;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

   public void deposit(double amount) {
        lock.lock();
        try {
            balance+=amount;
        } finally {
            lock.unlock();
        }
   }

   public void withdraw(double amount) {

        lock.lock();
        try {
            balance-=amount;
        } finally {
            lock.unlock();
        }
   }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
          return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);

        //No need to syncronize these fields, as they ar only Read.
        // Synchronizing would harm performance

    }
}