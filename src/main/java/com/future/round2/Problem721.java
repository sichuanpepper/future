package com.future.round2;

import java.util.*;

/**
 * Created by xingfeiy on 5/14/18.
 */
public class Problem721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if(accounts == null || accounts.size() < 1) return res;

        //build graph
        //account => account numbers
        Map<String, Set<Integer>> map = new HashMap<>();
        for(int num = 0; num < accounts.size(); num ++) {
            List<String> account = accounts.get(num);
            for(int i = 1; i < account.size(); i++) {
                Set<Integer> set = map.getOrDefault(account.get(i), new HashSet<>());
                set.add(num);
                map.put(account.get(i), set);
            }
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < accounts.size(); i++) {
            Set<Integer> neibors = new HashSet<>();
            for(int j = 1; j < accounts.get(i).size(); j++) {
                neibors.addAll(map.get(accounts.get(i).get(j)));
            }
            graph.put(i, neibors);
        }

        Set<Integer> visited = new HashSet<>();
        for(int i = 0; i < accounts.size(); i++) {
            if(visited.contains(i)) continue;
            Set<Integer> curRes = new HashSet<>();
            helper(i, graph, visited, curRes);
            Set<String> tmp = new TreeSet<>();
            for(int num : curRes) tmp.addAll(accounts.get(num).subList(1, accounts.get(num).size()));
            List<String> tmpRes = new ArrayList<>(tmp);
            tmpRes.add(0, accounts.get(i).get(0));
            res.add(tmpRes);
        }
        return res;
    }

    private void helper(int account, Map<Integer, Set<Integer>> graph, Set<Integer> visited, Set<Integer> curRes) {
        if(visited.contains(account)) return;
        visited.add(account);
        curRes.add(account);
        for(Integer a : graph.get(account)) {
            helper(a, graph, visited, curRes);
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        Problem721 p = new Problem721();
        List<List<String>> res = p.accountsMerge(accounts);
        System.out.println(res);

    }
}
