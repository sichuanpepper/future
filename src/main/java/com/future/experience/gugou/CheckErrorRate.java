package com.future.experience.gugou;

/**
 * 一个server error rate list，Index表示时间，value是这个时间的 error rate。问，给定一个targetTime和threshold，如果存在某个连续的时间段使得：
 * lengthOfTheTimePeriod * mininumErrorRateInThePeriod > threshold则return true.
 *               targetTime必须包含在这个时间段内.
 *               我用的two pointer，很快做完。
 *
 *
 *  We would like to design an alerting system for a server. The input to the system is error rates of the server over time.
 * [4,9,6,14,0,50,45,50,50,50,0,4,4]. This means the server had an error rate of 4% during time [0, 1), 9% during time [1, 2), 6% during the time [2, 3), and so on.
 *
 * Part 1: Write a program that returns True if and only if there exists some interval of interval_length seconds which includes target, time and where all error rates
 * in the interval are greater than or equal to min error rate, The input consists of an array of error rates, and the values for target time, interval length and
 * min error_rate.
 * Eg: target time=5, interval length=5,min error rate=45, returns true, interval [5,10) satisfies
 *
 * Part 2: Suppose that instead of interval_length and min_error_rate, you are given a threshold. Your program should return True if and only if there exists
 * some interval containing target_time for which length(interval) * min_error_rate(interval) >= threshold. The input consists of an array of error rates and the values for target_time and threshold.
 * Example : target time: 6, threshold: 200, Returns true, interval (5, 10) has length 5 and minerror_rate 55, and 5* 45 > 200
 */
public class CheckErrorRate {
    /**
     * Use tow pointers, and consider that target as a pivot, one pointer moves to left and one moves to right until <conditions>
     * Not clear the conditions here, equals to length or greater than?
     * @param errRates
     * @param target
     * @param length
     * @param minErrRate
     * @return
     */
    public boolean problem1(int[] errRates, int target, int length, int minErrRate) {
        if(errRates[target] < minErrRate) return false;
        int left = target, right = target;
        while (left >= 0 && errRates[left] >= minErrRate) {
            if(target - left + 1 == length) return true;
            left--;
        }
        while (right < errRates.length && errRates[right] >= minErrRate) {
            if(right - left == length) return true;
            right++;
        }
        return (right - left - 1) == length;
    }

    /**
     * For any element in the error rates, consider this element as the min rate, and then try to find the maximum rectangle can be formed.
     * Then check the area of rectangle, if greater than or equals to threshold, then check if this rectangle covered the target.
     *
     * To calculate the area of rectangle for each element, for any element E
     * - Consider E as a pivot, and extends it to both left and right side.
     * - For each extension, the extended element may:
     *      - Greater than pivot. (can form rectangle, continue extending)
     *      - Smaller than pivot. (can't form rectangle)
     *      - Equals to pivot.  (can form rectangle, continue extending)
     * - We extends it to both left and right until encounter the element which is smaller than pivot.
     * - After extension, we got left and right that represent the first smaller element in the left and first smaller element in right side.
     * - Area = (right - left - 1) * height[pivot]
     *
     * - How to find the first smaller one in both left and right side?
     * - Let's take the left extension as example.
     * - First, look at the first left element of pivot, it may:
     *      - Samller than pivot. (Then it's the first smaller)
     *      - Greater than or equals to pivot.  We already know the first element of this element, its first smaller element may:
     *              - Greater than or equals to pivot.
     *                  - Then continue find it in the left side.
     *              - Smaller than pivot. (Then it's the first smaller of pivot)
     * @param errRates
     * @param threshold
     * @return
     */
    public boolean problem2(int[] errRates, int target, int threshold) {
        int[] leftSamller = new int[errRates.length];
        leftSamller[0] = -1;
        for(int i = 1; i < leftSamller.length; i++) {
            if(errRates[i - 1] < errRates[i]) {
                leftSamller[i] = i - 1;
            } else {
                int indexOfSamll = leftSamller[i - 1];
                while (indexOfSamll >= 0 && leftSamller[indexOfSamll] >= errRates[i]) {
                    indexOfSamll = leftSamller[indexOfSamll];
                }
                leftSamller[i] = indexOfSamll;
            }
        }

        int[] rightSmaller = new int[errRates.length];
        rightSmaller[errRates.length - 1] = errRates.length;
        for(int i = errRates.length - 2; i >= 0; i--) {
            if(errRates[i + 1] < errRates[i]) {
                rightSmaller[i] = i + 1;
            } else {
                int indexOfSmall = rightSmaller[i + 1];
                while (indexOfSmall < rightSmaller.length && rightSmaller[indexOfSmall] >= errRates[i]) {
                    indexOfSmall = rightSmaller[indexOfSmall];
                }
                rightSmaller[i] = indexOfSmall;
            }
        }

        for(int i = 0; i < errRates.length; i++) {
            int area = (rightSmaller[i] - leftSamller[i] - 1) * errRates[i];
            if(area >= threshold && (target > leftSamller[i]) && (target < rightSmaller[i])) {
                System.out.println("Found result: " + leftSamller[i] + " to " + rightSmaller[i]);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CheckErrorRate p = new CheckErrorRate();
        System.out.println(p.problem2(new int[]{4,9,6,14,0,50,45,50,50,20,0,4,4}, 6, 170));
    }
}
