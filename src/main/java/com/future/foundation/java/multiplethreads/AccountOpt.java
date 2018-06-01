package com.future.foundation.java.multiplethreads;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class AccountOpt implements Runnable {
    private Account account;

    public AccountOpt(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        synchronized (this.account) {
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            this.account.deposit(1);
            System.out.println(Thread.currentThread().getName() + ", after deposit, the amount " + this.account.getAmount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.account.withdraw(1);
        System.out.println(Thread.currentThread().getName() + ", after withdraw, the amount " + this.account.getAmount());

    }

    public int getAmount() {
        return this.account.getAmount();
    }
}
