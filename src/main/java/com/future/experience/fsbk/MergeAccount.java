package com.future.experience.fsbk;

import com.future.utils.DisplayUtils;

import java.util.*;

/**
 * https://leetcode.com/problems/accounts-merge/description/
 *
 Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 and the rest of the elements are emails representing emails of the account.

 Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is
 common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could
 have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

 After merging the accounts, return the accounts in the following format: the first element of each account is the name,
 and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

 Example 1:
 Input:
 accounts = [
 ["John", "johnsmith@mail.com", "john00@mail.com"],
 ["John", "johnnybravo@mail.com"],
 ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
 ["Mary", "mary@mail.com"]
 ]

 Output:
 [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
 ["John", "johnnybravo@mail.com"],
 ["Mary", "mary@mail.com"]]

 Explanation:
 The first and third John's are the same person as they have the common email "johnsmith@mail.com".
 The second John and Mary are different people as none of their email addresses are used by other accounts.
 We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 Note:

 The length of accounts will be in the range [1, 1000].
 The length of accounts[i] will be in the range [1, 10].
 The length of accounts[i][j] will be in the range [1, 30].
 * Created by xingfeiy on 5/23/18.
 */
public class MergeAccount {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if(accounts == null || accounts.size() < 1) return res;
        //init account ids
        int[] aIds = new int[accounts.size()];
        for(int i = 0;i < aIds.length; i++) aIds[i] = i;

        Map<String, Integer> mailMap = new HashMap<>();
        for(int i = 0; i < accounts.size(); i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                if(mailMap.containsKey(accounts.get(i).get(j))) {
                    //union
                    union(aIds, i, mailMap.get(accounts.get(i).get(j)));
                } else {
                    mailMap.put(accounts.get(i).get(j), i);
                }
            }
        }

        //merge results
        Map<Integer, TreeSet<String>> mergedMap = new HashMap<>();
        for(int i = 0; i < aIds.length; i++) {
            if(mergedMap.containsKey(aIds[i])) {
                mergedMap.get(aIds[i]).addAll(accounts.get(i).subList(1, accounts.get(i).size()));
            } else {
                TreeSet<String> ts = new TreeSet<>();
                ts.addAll(accounts.get(i).subList(1, accounts.get(i).size()));
                mergedMap.put(aIds[i], ts);
            }
        }

        for(Map.Entry<Integer, TreeSet<String>> entry : mergedMap.entrySet()) {
            List<String> tmp = new ArrayList<>(entry.getValue());
            tmp.add(0, accounts.get(entry.getKey()).get(0));
            res.add(tmp);
        }
        return res;
    }

    private void union(int[] aIds, int a, int b) {
        if(a == b) return;
        int aPos = find(aIds, a);
        int bPos = find(aIds, b);
        if(aPos == bPos) return;
        for(int i = 0; i < aIds.length; i++) {
            if(aIds[i] == bPos) aIds[i] = aPos;
        }
    }

    private int find(int[] aIds, int a) {
        return aIds[a];
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john00@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John", "johnnybravo@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john_newyork@mail.com"}));
        accounts.add(Arrays.asList(new String[]{"Mary", "mary@mail.com"}));

        MergeAccount p = new MergeAccount();
        List<List<String>> res = p.accountsMerge(accounts);
        for(List<String> list : res) {
            DisplayUtils.printList(list);
        }
    }
}
