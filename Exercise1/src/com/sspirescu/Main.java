package com.sspirescu;

public class Main {

    public static void main(String[] args) {

        final BankAccount account = new BankAccount("12345-678", 1000.00);

//        Thread trThread1 = new Thread() {
//            public void run() {
//                account.deposit(300.00);
//                account.withdraw(50.00);
//
//                System.out.println(account.getBalance());
//            }
//        };
//
//        Thread trThread2 = new Thread () {
//
//            public void run() {
//                account.deposit(203.75);
//                account.withdraw(100.00);
//
//                System.out.println(account.getBalance());
//            }
//        };

        Thread trThread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                int i = 0;
                while (i<55) {
                    account.deposit(300.00);
                    account.withdraw(50.00);
//
                    System.out.println(account.getBalance());
                    i++;
                }
            }
        });

        Thread trThread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            int i = 0;
            while (i<55)

            {
                account.deposit(203.75);
                account.withdraw(100.00);
//
                System.out.println(account.getBalance());

                account.printAccountNumber();
                i++;
            }
            }
        });

        trThread1.start();
        trThread2.start();
    }
}
