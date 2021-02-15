package com.future.round2;

import com.future.utils.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.cnblogs.com/grandyang/p/8552586.html
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * Example 1:
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 *
 * Example 2:
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
 *
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * Note:
 *
 * schedule and schedule[i] are lists with lengths in range [1, 50].
 * 0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class Problem759 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<int[]> points = new ArrayList<>();
        for(List<Interval> intervals : schedule) {
            for(Interval interval : intervals) {
                points.add(new int[]{interval.start, 1});
                points.add(new int[]{interval.end, -1});
            }
        }

        Collections.sort(points, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });

        int start = -1, startCnt = 0;
        List<Interval> res = new ArrayList<>();
        for(int[] point : points) {
            startCnt += point[1];
            if(startCnt == 0) {
                start = point[0];
            }
            if(start >= 0 && point[1] == 1 && start < point[0]) {
                res.add(new Interval(start, point[0]));
                start = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
        schedule.add(Arrays.asList(new Interval(2, 4)));
        schedule.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
        Problem759 p = new Problem759();
        p.employeeFreeTime(schedule).forEach(System.out::println);
    }
}
