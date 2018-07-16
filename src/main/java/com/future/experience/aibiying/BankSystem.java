package com.future.experience.aibiying;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assumption:
 * - Can we simplify the problem, using integer represent the number of money? otherwise, we'd better use BigDecimal
 *
 * Design:
 * - each account has a transaction list which ordered by time stamp.
 * - deposit
 *  - if given account doesn't have any transaction, add new transaction with the given amount.
 *  - otherwise, add one more transaction, and the amount is the previous amount plus given amount.
 * - withdraw
 *  - first, check if given account existed, then check the latest amount of this account.
 *      - if latest amount is greater or equal to given amount, add transaction and update amount
 *      - otherwise, just return.
 *
 * Created by xingfeiy on 6/14/18.
 */
public class BankSystem {
    private Map<Integer, List<Transaction>> map = new HashMap<>();

    public int deposit(int countId, long tsmp, int amount) {
        List<Transaction> transactions = map.getOrDefault(countId, new ArrayList<>());
        int curTotal = transactions.size() > 0 ? transactions.get(transactions.size() - 1).total : 0;
        transactions.add(new Transaction(tsmp, amount, curTotal + amount));
        map.put(countId, transactions);
        return map.get(countId).get(map.get(countId).size() - 1).total;
    }

    public int withdraw(int countId, long tsmp, int amount) {
        int total = check(countId);
        if(amount <= 0 || total < amount) return 0;
        map.get(countId).add(new Transaction(tsmp, amount, total - amount));
        return amount;
    }

    public int check(int countId) {
        if(!map.containsKey(countId) || map.get(countId).size() < 1) return 0;
        return map.get(countId).get(map.get(countId).size() - 1).total;
    }

    public int balance(int countId, long start, long end) {
        if(!map.containsKey(countId) || map.get(countId).size() < 0 || start >= end) return 0;
        Transaction startTrans = find(map.get(countId), start, false);
        Transaction endTrans = find(map.get(countId), end, true);
        if(startTrans == null || endTrans == null) return 0;
        return endTrans.total - startTrans.total;
    }

    private Transaction find(List<Transaction> transactions, long tmsp, boolean pre) {
        int start = 0, end = transactions.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(transactions.get(mid).tmsp == tmsp) {
                return pre ? transactions.get(mid -  1) :transactions.get(mid + 1);
            } else if(transactions.get(mid).tmsp < tmsp) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if(transactions.get(start).tmsp > tmsp) {
            return pre ? null : transactions.get(start);
        } else if(transactions.get(end).tmsp < tmsp) {
            return pre ? transactions.get(end) : null;
        } else if(transactions.get(start).tmsp == tmsp) {
            return pre ? null : transactions.get(end);
        }  else if (transactions.get(end).tmsp == tmsp) {
            return pre ? transactions.get(start) : null;
        } else {
            return pre ? transactions.get(start) : transactions.get(end);
        }
    }


    private class Transaction {
        public long tmsp;

        public int amount;

        public int total;

        public Transaction(long tmsp, int amount, int total) {
            this.tmsp = tmsp;
            this.amount = amount;
            this.total = total;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BankSystem bs = new BankSystem();
        System.out.println(bs.deposit(1, System.currentTimeMillis(), 100));
        System.out.println(bs.deposit(2, System.currentTimeMillis(), 1000));
        System.out.println(bs.deposit(1, System.currentTimeMillis(), 200));
        System.out.println(bs.deposit(1, System.currentTimeMillis(), 300));
        long start = System.currentTimeMillis();
        Thread.sleep(10);
        System.out.println(bs.withdraw(1, System.currentTimeMillis(), 100));
        System.out.println(bs.withdraw(1, System.currentTimeMillis(), 200));
        System.out.println(bs.withdraw(1, System.currentTimeMillis(), 300));
        System.out.println(bs.withdraw(1, System.currentTimeMillis(), 400));
        Thread.sleep(10);
        long end = System.currentTimeMillis();
        System.out.println(bs.deposit(1, System.currentTimeMillis(), 1000));
        System.out.println(bs.balance(1, start, end));
    }
}
