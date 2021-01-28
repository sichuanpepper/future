package com.future.experience.gugou;

import java.util.*;

public class OncallScheduler {
    private class OncallTask {
        public int start;
        public int end;
        public List<Integer> oncaller;

        public OncallTask(int start, int end, List<Integer> oncaller) {
            this.start = start;
            this.end = end;
            this.oncaller = oncaller;
        }

        @Override
        public String toString() {
            return "OncallTask{" +
                    "start=" + start +
                    ", end=" + end +
                    ", oncaller=" + oncaller +
                    '}';
        }
    }


    //each element in list is one oncall task which consists of start time, end time and user id.

    /**
     * We can break an interval into two points, start and end, and sort it.
     * For each point, it means either a new task is coming or a task is ended, it will change the current oncall list.
     * So we can maintain  a current oncall set, if we encounter a start point, add the corresponding user into the current oncall list.
     * Otherwise, remove it from current oncall list.
     * @param inputs
     * @return
     */
    public List<OncallTask> solution(List<int[]> inputs) {
        List<OncallTask> res = new ArrayList<>();
        if(inputs == null || inputs.size() < 1) return res;

        Set<Integer> curOncaller = new HashSet<>();
        //use int[3] represents point obj which consists of time, start/end(0/1), user id.
        List<int[]> pointList = new ArrayList<>();
        for(int[] task : inputs) {
            pointList.add(new int[]{task[0], 0, task[2]});
            pointList.add(new int[]{task[1], 1, task[2]});
        }

        Collections.sort(pointList, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int pre  = -1;
        for(int[] point : pointList) {
            if(pre >= 0 && pre != point[0]) {
                res.add(new OncallTask(pre, point[0], new ArrayList<>(curOncaller)));
            }

            if(point[1] == 0) {
                curOncaller.add(point[2]);
            } else {
                curOncaller.remove(point[2]);
            }

            pre = point[0];
        }
        return res;
    }


    public static void main(String[] args) {
        OncallScheduler scheduler = new OncallScheduler();
        List<int[]> inputs = new ArrayList<>();
        inputs.add(new int[]{1, 5, 100});
        inputs.add(new int[]{5, 7, 101});
        inputs.add(new int[]{2, 10, 102});
        inputs.add(new int[]{12, 15, 103});
        scheduler.solution(inputs).stream().forEach(System.out::println);
    }
}
