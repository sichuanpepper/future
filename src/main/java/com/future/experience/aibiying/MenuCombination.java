package com.future.experience.aibiying;

import com.future.foundation.java.multiplethreads.SyncExample;
import com.future.utils.DisplayUtils;
import com.future.utils.DoublyLinkedList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a menu (list of items prices), find all possible combinations of items that sum a particular value K.
 * (A variation of the typical 2sum/Nsum questions).
 * Return the coins combination with the minimum number of coins. Time complexity O(MN), where M is
 * the target value and N is the number of distinct coins. Space complexity O(M).
 * <p>
 * Created by xingfeiy on 6/13/18.
 */
public class MenuCombination {
    private void search(List<List<Double>> res, int[] centsPrices, int start,
                        int centsTarget,
                        List<Double> curCombo, double[] prices) {
        if (centsTarget == 0) {
            res.add(new ArrayList<>(curCombo));
            return;
        }
        for (int i = start; i < centsPrices.length; i++) {
            if (i > start && centsPrices[i] == centsPrices[i - 1]) {
                continue;
            }
            if (centsPrices[i] > centsTarget) {
                break;
            }
            curCombo.add(prices[i]);
            search(res, centsPrices, i + 1, centsTarget - centsPrices[i],
                    curCombo, prices);
            curCombo.remove(curCombo.size() - 1);
        }
    }

    /**
     * Ask precision since we are process double elements.
     * @param prices
     * @param target
     * @return
     */
    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        if (prices == null || prices.length == 0 || target <= 0) {
            return res;
        }
        int centsTarget = (int) Math.round(target * 100);
        Arrays.sort(prices);
        int[] centsPrices = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            centsPrices[i] = (int) Math.round(prices[i] * 100);
        }
        search(res, centsPrices, 0, centsTarget, new ArrayList<>(), prices);
        return res;
    }


    public List<List<Double>> getCombosMy(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        helper(prices, 0, 0.0, new ArrayList<>(), res, target);
        return res;
    }

    private void helper(double[] prices, int start, double curVal, List<Double> curRes, List<List<Double>> res, double target) {
        if(Math.abs(curVal - target) < 0.01) res.add(new ArrayList<>(curRes));
        if(start >= prices.length || (curVal - target) > 0.01) return;

        for(int i = start; i < prices.length; i++) {
            curRes.add(prices[i]);
            helper(prices, i + 1, curVal + prices[i], curRes, res, target);
            curRes.remove(curRes.size() - 1);
        }
    }

    public List<List<Double>> getCombosMy2(double[] prices, double target) {
        BigDecimal[] bPrices = new BigDecimal[prices.length];
        for(int i = 0; i < prices.length; i++) bPrices[i] = new BigDecimal(Double.toString(prices[i]));
        BigDecimal bTarget = new BigDecimal(Double.toString(target));
        List<List<Double>> res = new ArrayList<>();
        helper2(bPrices, 0, new BigDecimal("0"), bTarget, new ArrayList<>(), res, prices);
        return res;
    }

    private void helper2(BigDecimal[] bPrices, int start, BigDecimal curVal, BigDecimal target, List<Double> curRes,
                         List<List<Double>> res, double[] prices) {
        if(curVal.equals(target)) res.add(new ArrayList<>(curRes));
        if(start >= bPrices.length || curVal.compareTo(target) > 0) return;
        for(int i = start; i < bPrices.length; i++) {
            curRes.add(prices[i]);
            helper2(bPrices, i + 1, curVal.add(bPrices[i]), target, curRes, res, prices);
            curRes.remove(curRes.size() - 1);
        }
    }




    public static void main(String[] args) {
        MenuCombination m = new MenuCombination();
        System.out.println("======> case 1 - 1");
        List<List<Double>> res = m.getCombos(new double[]{1.6, 2.8,1.0001, 5.20001, 0.0}, 10.6);
        for(List<Double> combin : res) {
            DisplayUtils.printList(combin);
            System.out.println("=====================");
        }

        m = new MenuCombination();
        System.out.println("======> case 1 - 2");
        res = m.getCombos(new double[]{1.6, 2.8,1.0001, 5.20001, 0.0}, 10.60011);
        for(List<Double> combin : res) {
            DisplayUtils.printList(combin);
            System.out.println("=====================");
        }

        System.out.println("======> case 2 - 1");
        res = m.getCombosMy(new double[]{1.6, 2.8, 1.0001, 5.20001, 0.0}, 10.6);
        for(List<Double> combin : res) {
            DisplayUtils.printList(combin);
            System.out.println("=====================");
        }

        System.out.println("======> case 2 - 2");
        res = m.getCombosMy(new double[]{1.6, 2.8, 1.0001, 5.20001, 0.0}, 10.60011);
        for(List<Double> combin : res) {
            DisplayUtils.printList(combin);
            System.out.println("=====================");
        }

        System.out.println("======> case 3 - 1");
        res = m.getCombosMy2(new double[]{1.6, 2.8, 1.0001, 5.20001, 0.0}, 10.60011);
        for(List<Double> combin : res) {
            DisplayUtils.printList(combin);
            System.out.println("=====================");
        }
    }
}
