package com.future.experience.fsbk;

/**
 * count non-increasing and non-decreasing subarray, [1,1,2,1] return 2 因为1，1，2 non-dec; 2,1 non-inc。
 */
public class FindNonincAndNondecSubArray {
    /**
     * There are two status: non-decreasing and non-increasing
     * When the status changed, a new subarray generated.
     * Compare to previous num,
     *  - equals to: keep status
     *  - greater than: if current status is non-increasing, status changed.
     *  - smaller than: if current status is non-decreasing, status changed.
     *
     * @param array
     * @return
     */
    public int findSubArray(int[] array) {
        int p2 = 1, res = 1, status = 0; // status: 0 - default, 1 - non-decreasing, -1 - non -increasing
        while (p2 < array.length) {
            if(array[p2] > array[p2 - 1]) {
                if(status == -1) {
                    res++;
                }
                status = 1;
            } else {
                if(status == 1) {
                    res++;
                }
                status = -1;
            }
            p2++;
        }
        return res;
    }

    public static void main(String[] args) {
        FindNonincAndNondecSubArray p = new FindNonincAndNondecSubArray();
        System.out.println(p.findSubArray(new int[]{1}));
        System.out.println(p.findSubArray(new int[]{1, 2, 3}));
        System.out.println(p.findSubArray(new int[]{3, 2, 1}));
        System.out.println(p.findSubArray(new int[]{1, 2, 3, 2, 1, 2}));
    }
}
