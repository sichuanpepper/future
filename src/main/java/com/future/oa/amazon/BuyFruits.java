package com.future.oa.amazon;

import java.util.List;

/**
 * Created by xingfeiy on 3/9/18.
 */
public class BuyFruits {
    /**
     * The task is, we bought some fruits as company required.
     * The fruits we bought are placed in multiple baskets(lists represented here), and we are going to check if
     * the fruits we bought is a sub sequence of required list, order matter.
     *
     * And the bought baskets may have 'anything', which means it could be anything.
     *
     * example 1:
     * bought: [
     *             [apple, apple]
     *             [orange, banana, orange]
     *         ]
     * required: [orange, apple, apple, orange, banana, orange]
     * return true since we can find a sub sequence after first orange in required list.
     *
     * example 2:
     * bought: [
     *             [apple, apple]
     *             [orange, pear, orange]
     *         ]
     * required: [orange, apple, apple, orange, banana, orange]
     * return false.
     *
     * example 1:
     * bought: [
     *             [apple, apple]
     *             [orange, anything, orange]
     *         ]
     * required: [orange, apple, apple, orange, banana, orange]
     * return true.
     *
     * @param bought
     * @param required
     * @return
     */
    public boolean achieved(List<List<String>> bought, List<String> required) {
        return true;
    }
}
