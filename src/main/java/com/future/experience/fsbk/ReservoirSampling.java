package com.future.experience.fsbk;


import java.util.Random;

/**
 * Reservoir sampling is a family of randomized algorithms for randomly choosing k samples from a list of n items,
 * where n is either a very large or unknown number.
 * Typically n is large enough that the list doesn’t fit into main memory. For example, a list of search queries in Google and Facebook.
 *
 * https://www.cnblogs.com/snowInPluto/p/5996269.html
 */
public class ReservoirSampling {
    public int[] selectKItems(int stream[], int k) {
        if(stream == null || k < 1 || stream.length < k) {
            return null;
        }

        int[] res = new int[k];
        int index = 0;
        //the possibility of items between stream[0] to stream[k - 1] are 100%
        for(; index < k; index++) {
            res[index] = stream[index];
        }

        Random random = new Random();
        //from stream[k], for each item in res[], the possibility of replacement is:
        // k+1 item, k/(k+1) * 1/k=1/(k+1), the possibility of pick is 1- 1/(k+1) = k/(k+1)
        // k+2 item, k/(k+2) * 1/k=1/(k+2), the possibility of pick is 1 - 1/(k+2) = (k+1)/(k+2)
        // ...
        // total possibility is 1*k/(k+1)*(k+1)/(k+2)...*(n-1)/n = k/n
        for(; index < stream.length; index++) {
            int rIndex = random.nextInt(index + 1);
            if(rIndex < k) {
                res[rIndex] = stream[index];
            }
        }
        return res;
    }
}
