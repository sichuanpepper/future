package com.future.foundation.java.multiplethreads;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class Account {
    private int amount = 0;

    public void withdraw(int amount) {
        this.amount -= amount;
    }

    public void deposit(int amount) {
        this.amount += amount;
    }

    public int getAmount() {
        return amount;
    }
}
