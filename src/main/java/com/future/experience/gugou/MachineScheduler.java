package com.future.experience.gugou;

/**
 * 关于机器schedule的，其实本质上跟刷题网meeting room差不多，给一个新的interval，能不能加到现有的schedule里面，
 * 不能跟现有的interval 重合。可以就加，返回true，不行就不加，返回false。先写了个linear的解法，后来写了个logn的解法。
 */
public class MachineScheduler {
    public boolean canInsert(int[][] intervals, int[] interval) {
        //assume inputs are valid and intervals are sorted by start time and there's no overlap.
        if(interval[0] > intervals[intervals.length - 1][1] || interval[1] < intervals[0][0]) {
            return true;
        }
        for(int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i][1] < interval[0] && interval[1] < intervals[i + 1][0]) {
                return true;
            }
        }
        return false;
    }

    public boolean canInsertBS(int[][] intervals, int[] interval) {
        if(interval[0] > intervals[intervals.length - 1][1] || interval[1] < intervals[0][0]) {
            return true;
        }
        int start = 0, end = intervals.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(intervals[mid][0] == interval[0]) {
                return false;
            } else if(intervals[mid][0] < interval[0]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if(interval[0] < intervals[start][0]) {
            return interval[1] < intervals[start][1];
        } else if(interval[0] > intervals[end][0]) {
            return interval[1] < intervals[end + 1][0];
        } else {
            return interval[0] > intervals[start][1] && interval[1] < intervals[end][0];
        }
    }


    public static void main(String[] args) {
        int[][] intervals = new int[][] {
                new int[]{1, 3},
                new int[]{4, 5},
                new int[]{8, 9},
        };

        MachineScheduler p = new MachineScheduler();
        System.out.println(p.canInsert(intervals, new int[]{2, 6}));
        System.out.println(p.canInsert(intervals, new int[]{6, 7}));
        System.out.println(p.canInsert(intervals, new int[]{10, 11}));
        System.out.println(p.canInsertBS(intervals, new int[]{2, 6}));
        System.out.println(p.canInsertBS(intervals, new int[]{6, 7}));
        System.out.println(p.canInsertBS(intervals, new int[]{10, 11}));
    }
}
