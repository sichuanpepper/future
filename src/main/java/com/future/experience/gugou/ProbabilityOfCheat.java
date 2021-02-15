package com.future.experience.gugou;

/**
 * 一个m * n的矩阵，每个点是一个学生，第一排互传小抄被老师抓到的概率是a%，第二排变成a%/2,  第三。。。n排依次递减半。然后如果是第一排传给第二排，
 * 概率是b%，然后第二排到第三排依次递减一半。问给你一个路径，求成功传递小抄的概率。
 *
 * follow-up是每个学生有自己的妙招可以增加成功概率，问成功传递的概率。
 */
public class ProbabilityOfCheat {
    /**
     * 1 2 3   -> prob: a%
     * 4 5 6   -> prob: a%/2
     * 7 8 9   -> prob: a%/2/2
     *
     * If 1 want to pass the answer to 9, there are multiple ways to do that:
     * for example: 1->2->5->6->9, prob=a% * b% * a%/2 * b%/2 =
     * @param matrix
     * @return
     */
    public double prob(int[][] matrix) {
        return 0;
    }
}
