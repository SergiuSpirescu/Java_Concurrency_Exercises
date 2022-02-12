package com.sspirescu;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
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

        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not get the Lock!");
            }
        } catch (InterruptedException e) {
            //handle the exception thrown by the tryLock
        }
       System.out.println("Transactrion status: " + status);
   }

   public void withdraw(double amount) {

       boolean status = false;

        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            //handle exception
        }
       System.out.println("Transactrion status: " + status);

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