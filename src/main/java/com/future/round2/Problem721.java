package com.future.round2;

import java.util.*;

/**
 * Created by xingfeiy on 5/14/18.
 */
public class Problem721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if(accounts == null || accounts.size() < 1) return accounts;
        int[] users = new int[accounts.size()];
        for(int i = 0;i < users.length; i++) users[i] = i;

        //(mail=>user)
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for(int j = 1; j < account.size(); j++) {
                if(map.containsKey(account.get(j))) {
                    connect(i, map.get(account.get(j)), users);
                } else {
                    map.put(account.get(j), i);
                }
            }
        }

        Map<Integer, TreeSet<String>> resMap = new HashMap<>();
        for(int i = 0; i < users.length; i++) {
            if(resMap.containsKey(users[i])) {
                resMap.get(users[i]).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
            } else {
                resMap.put(users[i], new TreeSet<>(accounts.get(i).subList(1, accounts.get(i).size())));
            }
        }

        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<Integer, TreeSet<String>> entry : resMap.entrySet()) {
            List<String> account = new ArrayList<>(entry.getValue());
            account.add(0, accounts.get(entry.getKey()).get(0));
            res.add(account);
        }
        return res;
    }

    private void connect(int usr1, int usr2, int[] users) {
        for(int i = 0; i < users.length; i++) {
            if(users[i] == usr1) users[i] = usr2;
        }
    }
}
