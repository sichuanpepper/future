package com.future.experience.aibiying;

import java.util.*;

/**
 * Created by xingfeiy on 7/2/18.
 */
public class Pagination {
    /**
     * Let's we have a lot of logs which sorted by score.
     * host_id, score, city
     * 2, 1, san francisco
     * 2, 1, san francisco
     * 2, 1, san francisco
     * 3, 1, san jose
     * 2, 2, santa clara
     * 4, 2, santa clara
     * 2, 2, san mateo
     * 1, 3, san mateo
     * 5, 4, san mateo
     * @param logs
     */
    public void pagination(String[] logs, int pageSize) {
        List<List<String>> res = new ArrayList<>();
        List<String> logList = new LinkedList<>();
        for(String str : logs) logList.add(str);
        Iterator<String> iter = logList.iterator();
        List<String> curRes = new ArrayList<>(pageSize);
        Set<Integer> visited = new HashSet<>();
        boolean reachEnd = false;
        while (iter.hasNext()) {
            if(curRes.size() == pageSize) {
                res.add(new ArrayList<>(curRes));
                curRes.clear();
                visited.clear();
                iter = logList.iterator();
            }

            String log = iter.next();
            String[] tokens = log.split(",");
            if(tokens.length != 3) continue;
            if(visited.contains(Integer.parseInt(tokens[0])) && !reachEnd) continue;

            //add
            curRes.add(log);
            visited.add(Integer.parseInt(tokens[0]));
            iter.remove();

            if(!iter.hasNext()) {
                reachEnd = true;
                iter = logList.iterator();
            }

        }
        if(curRes.size() > 0) res.add(curRes);

        for(List<String> page : res) {
            for(String str : page) System.out.println(str);
            System.out.println("=================");
        }
    }


    /**
     *
     * @param logs
     * @param pageSize
     */
    public void pagination2(String[] logs, int pageSize) {
        //key is host id, and array is the positions
        LinkedHashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
        for(int i = 0; i < logs.length; i++) {
            String[] tokens = logs[i].split(",");
            if(tokens.length != 3) continue;
            int hostId = Integer.parseInt(tokens[0]);
            List<Integer> positions = map.getOrDefault(hostId, new LinkedList<>());
            positions.add(i);
            map.put(hostId, positions);
        }

        List<List<String>> res = new ArrayList<>();
        List<Logs> page = new ArrayList<>();
        while (true) {
            boolean hasValue = false;
            for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                if(entry.getValue().size() > 0) {
                    page.add(new Logs(logs[entry.getValue().get(0)]));
                    entry.getValue().remove(0);
                    hasValue = true;
                }
                if(page.size() == pageSize) {
                    Collections.sort(page, (o1, o2) -> (Integer.compare(o1.score, o2.score)));
                    List<String> pageStr = new ArrayList<>();
                    for(Logs l : page) pageStr.add(l.str);
                    res.add(pageStr);
                    page.clear();
                }
            }
            if(!hasValue) {
                if(page.size() > 0) {
                    Collections.sort(page, (o1, o2) -> (Integer.compare(o1.score, o2.score)));
                    List<String> pageStr = new ArrayList<>();
                    for(Logs l : page) pageStr.add(l.str);
                    res.add(pageStr);
                }
                break;
            }

        }


        System.out.println("Second Implementation");
        for(List<String> p : res) {
            for(String s : p) System.out.println(s);
            System.out.println("==================");
        }
    }

    private class Logs {
        public int hostId;
        public int score;
        public String city;
        public String str;
        public Logs(String str) {
            this.str = str;
            String[] tokens = this.str.split(",");
            if(tokens.length == 3) {
                this.hostId = Integer.parseInt(tokens[0]);
                this.score = Integer.parseInt(tokens[1].trim());
                this.city = tokens[2];
            }
        }

    }

    public static void main(String[] args) {
        Pagination p = new Pagination();
        String[] logs = new String[]{"2, 1, san francisco", "1, 1, san francisco", "2, 1, san francisco",
                "3, 1, san jose", "2, 2, santa clara",
        "4, 2, santa clara", "2, 2, san mateo", "1, 3, san mateo", "5, 4, san mateo"};
        p.pagination(logs, 3);
        p.pagination2(logs, 3);
    }
}

