package com.future.experience;

import java.util.BitSet;

/**
 * For segment tree(full binary tree) represent by array:
 * - The total nodes = 2 * leafs - 1.
 * - For any node at position k,
 *      - left_child = k * 2 + 1
 *      - right_child = k * 2 + 2
 *      - parent = (k - 1) / 2
 *      - sibling = k % 2 == 0 ? k - 1 : k + 1
 * - The leafs start from index n - 1
 *
 * Created by xingfeiy on 7/19/18.
 */
public class IDAllocate {
    private BitSet segTree = null;

    private int num;

    public IDAllocate(int n) {
        num = n;
        segTree = new BitSet(num * 2 + 1);
    }

    public int allocate() {
        return findAvailable(0);
    }

    public void dellocate(int id) {
        int segPos = num - 1 + id;
        if(!segTree.get(segPos)) return;
        segTree.set(segPos, false);
        update(segPos);

    }

    private int findAvailable(int segStart) {
        if(segTree.get(segStart)) return Integer.MIN_VALUE;
        if(segStart >= num - 1) {
            segTree.set(segStart);
            //update tree
            update(segStart);
            return segStart - (num - 1);
        }
        int res = findAvailable(segStart * 2 + 1);
        if(res != Integer.MIN_VALUE) return res;
        return findAvailable(segStart* 2 + 2);
    }

    private void update(int segPos) {
        if(segPos < 2) return;
//        int parent = segPos % 2 == 0 ? (segPos - 2) / 2 : (segPos - 1) / 2;  //odd is left, even is right
        int parent = (segPos - 1) / 2;  //odd is left, even is right
        int sibling = segPos % 2 == 0 ? segPos - 1 : segPos + 1;
        if(segTree.get(segPos) && segTree.get(sibling)) {
            segTree.set(parent);
        } else {
            segTree.set(parent, false);
        }
    }



    public static void main(String[] args) {
        IDAllocate id = new IDAllocate(4);
        System.out.println(id.allocate());
        System.out.println(id.allocate());
        System.out.println(id.allocate());
        System.out.println(id.allocate());
        System.out.println(id.allocate());
        id.dellocate(2);
        System.out.println(id.allocate());
        id.dellocate(0);
        id.dellocate(1);
//        id.dellocate(2);
        id.dellocate(3);
        System.out.println(id.allocate());
        System.out.println(id.allocate());
        System.out.println(id.allocate());

    }
}
