package com.future.foundation.java.multiplethreads;

/**
 * Created by xingfeiy on 5/31/18.
 */
public class SyncExample {
    public static void main(String[] args) {
        AccountOpt opt = new AccountOpt(new Account());
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(opt, "thread - " + i);
            thread.start();
        }
    }
}
