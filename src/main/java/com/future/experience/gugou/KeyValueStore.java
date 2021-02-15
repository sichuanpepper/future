package com.future.experience.gugou;

import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=668674&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26orderby%3Ddateline&page=1
 * 不断有<Key, Value>pairs进来的input stream. Key: String, Value: positive integer. 要求自己定义数据结构，写两个函数，第一个，addNewPair, 第二个：return the top K pairs.
 *
 * 怎么算TOP呢，对于某个key，算出他所有input  pair里面带这个key 的value的和（有点拗口，不过很好理解，看例子）
 *
 * example:
 * K = 2
 * input addpair顺序：<a, 10> <b, 20> <c, 15> <a, 10>
 *
 * 每次addPair之后call findTopK() 的结果如下：
 * <a,10>
 * <a, 10> <b, 20>
 * <c, 15> <b, 20>
 * <a, 20> <b, 20>
 */
public class KeyValueStore {
    private class KeyValueObj {
        public String key;
        public int val;

        public KeyValueObj(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Frequency of add and get??
     *
     * Map: Key and counter
     *
     * Time complexity O(1)
     *
     * @param obj
     */
    public void addPair(KeyValueObj obj) {}

    /**
     * Clarify the question: k is a parameter of the function or it's a value initial it when the object is created?
     * If it's initial with object.
     * - Maintain a priority queue
     *
     * @param k
     * @return
     */
    public List<KeyValueObj> getTopK(int k) {
        return null;
    }


    private static class TestObj {
        public int key;
        public int val;

        public TestObj(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    public static void main(String[] args) {
        //modify the object in priority queue won't trigger sort.
        PriorityQueue<TestObj> queue = new PriorityQueue<>((a, b)->Integer.compare(a.val, b.val));
        TestObj obj1 = new TestObj(1, 3);
        TestObj obj2 = new TestObj(2, 1);
        TestObj obj3 = new TestObj(3, 4);
        queue.offer(obj1);
        queue.offer(obj2);
        queue.offer(obj3);
        System.out.println(queue.peek().key + " " + queue.peek().val);
        obj3.val = 0;
        System.out.println(queue.peek().key + " " + queue.peek().val);
    }
}
