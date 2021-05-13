package com.future.experience.fsbk.eley;

import com.future.utils.DisplayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 *
 * Thoughts:
 * - Go through logs one by one, and maintain a variable which represents the current function id.
 *  - If current log is a start:
 *      - calculate how long time has been executed of current function and accumulate its result.
 *      - push current function to stack.
 *  - If current log is a end:
 *      - calculate how long time has been executed of current function and accumulate its result.
 *      - pop a function from stack and assign to current function id. (update the start time)
 *
 */
public class ExclusiveTimeofFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        int curId = -1, curStart = 0;
        for(String log : logs) {
            int[] tokens = parse(log);
            if(tokens[1] == 1) {
                //start
                if(curId >= 0) {
                    res[curId] += tokens[2] - curStart;
                }
                curId = tokens[0];
                curStart = tokens[2];
            } else {
                //end
                res[tokens[0]] += (tokens[2] - curStart + 1);
                curStart = tokens[2] + 1;
                curId = -1;
            }
        }
        return res;
    }

    //int[]: {id, status, tstamp}
    private int[] parse(String str) {
        String[] tokens = str.split(":");
        return new int[]{Integer.parseInt(tokens[0]),
                tokens[1].equals("start") ? 1 : 0,
                Integer.parseInt(tokens[2])};
    }

    public static void main(String[] args) {
        //2
        //["0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"]
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8");
        DisplayUtils.printArray(new ExclusiveTimeofFunctions().exclusiveTime(2, logs));
    }
}
