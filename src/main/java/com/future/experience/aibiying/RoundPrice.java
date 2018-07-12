package com.future.experience.aibiying;

import com.future.utils.DisplayUtils;
import com.future.utils.DoublyLinkedList;

import java.util.*;

/**
 * Created by xingfeiy on 6/13/18.
 */
public class RoundPrice {
    //Wrong answer
    public int[] roundUp(double[] arr) {
        int n = arr.length;
        NumWithDiff[] arrWithDiff = new NumWithDiff[n];
        double sum = 0.0;
        int floorSum = 0;
        for (int i = 0; i < n; i++) {
            int floor = (int) arr[i];
            int ceil = floor;
            if (floor < arr[i]) ceil++;
            floorSum += floor;
            sum += arr[i];
            arrWithDiff[i] = new NumWithDiff(ceil, ceil - arr[i]);
        }
        int num = (int) Math.round(sum);
        int diff = num - floorSum;
        Arrays.sort(arrWithDiff, new Comparator<NumWithDiff>() {
            @Override
            public int compare(NumWithDiff n1, NumWithDiff n2) {
                if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
                else return 1;
            }
        });
        Arrays.sort(arrWithDiff, (a, b) ->
                (Double.compare(b.diffWithCeil, a.diffWithCeil)));
        int[] res = new int[n];
        int i = 0;
        for (; i < diff; i++) {
            res[i] = arrWithDiff[i].num; // 这些放 ceil
        }
        for (; i < n; i++) {
            res[i] = arrWithDiff[i].num - 1; // 剩下的只放 floor
        }
        return res;
    }

    class NumWithDiff {
        int num;
        double diffWithCeil;

        public NumWithDiff(int n, double c) {
            this.num = n;
            this.diffWithCeil = c;
        }
    }

    public int[] mySolution(double[] prices) {
        if(prices == null) return null;
        int[] res = new int[prices.length];
        double total = 0;
        for(double p : prices) total += p;
        int roundRes = (int)Math.round(total);
        helper(prices, 0, 0, 0.0, roundRes, res);
        return bestRes;
    }

    private double minDiff = Double.MAX_VALUE;
    private int[] bestRes = null;

    private void helper(double[] prices, int start, int curIntSum, double curMinDiff, int roundRes, int[] res) {
        if(curIntSum > roundRes) return;
        if(start == prices.length) {
            if(curIntSum == roundRes && Double.compare(curMinDiff, minDiff) < 0) {
                minDiff = curMinDiff;
                bestRes = Arrays.copyOf(res, res.length);
            }
            return;
        }
        int tmp = (int)Math.floor(prices[start]);
        res[start] = tmp;
        helper(prices, start + 1, curIntSum + tmp, curMinDiff + (prices[start] - (double) tmp), roundRes, res);
        res[start] = ++tmp;
        helper(prices, start + 1, curIntSum + tmp, curMinDiff + ((double)tmp - prices[start]), roundRes, res);
    }


    public static void main(String[] args) {
        RoundPrice r = new RoundPrice();
        DisplayUtils.printArray(new RoundPrice().mySolution(new double[]{1.2, 2.3, 3.4}));  //1, 2, 4
        DisplayUtils.printArray(new RoundPrice().mySolution(new double[]{1.2, 1.9, 1.8}));  //1, 2, 2
        DisplayUtils.printArray(new RoundPrice().mySolution(new double[]{1.2, 1.3, 1.1}));  //1, 2, 1
        DisplayUtils.printArray(new RoundPrice().mySolution(new double[]{1.0, 1.0, 1.0}));  //1 ,1, 1
        DisplayUtils.printArray(new RoundPrice().mySolution(new double[]{1.2, 3.7, 2.3, 4.8}));  //1 ,1, 1

        System.out.println("Another....");
        DisplayUtils.printArray(new RoundPrice().roundUp(new double[]{1.2, 2.3, 3.4}));  //1, 2, 4
        DisplayUtils.printArray(new RoundPrice().roundUp(new double[]{1.2, 1.9, 1.8}));  //1, 2, 2
        DisplayUtils.printArray(new RoundPrice().roundUp(new double[]{1.2, 1.3, 1.1}));  //1, 2, 1
        DisplayUtils.printArray(new RoundPrice().roundUp(new double[]{1.0, 1.0, 1.0}));  //1 ,1, 1
        DisplayUtils.printArray(new RoundPrice().roundUp(new double[]{1.2, 3.7, 2.3, 4.8}));  //1 ,1, 1
    }

}
