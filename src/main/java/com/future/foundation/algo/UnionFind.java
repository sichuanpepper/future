package com.future.foundation.algo;

import com.future.utils.DisplayUtils;

/**
 * Simplest approach: Quick find
 * Better approach: Quick union
 * Improved approach: Weighted quick-union
 * Created by someone on 6/13/17.
 */
public class UnionFind {
    public int[] array = null;

    private int size = 0;

    public UnionFind(int length) {
        this.array = new int[length];
        this.size = length;
        for(int i = 0; i < length; i++) {
            this.array[i] = i;
        }
    }

    public int getSize() {
        return size;
    }

    public int find(int val) {
        if(val >= this.array.length) {
            return -1;
        }
        return this.array[val];
    }

    public void union(int first, int second) {
        if(first < 0 || second < 0 || first >= array.length || second >= array.length) {
            return;
        }

        int fPos = this.array[first];
        int sPos = this.array[second];
        if(fPos == sPos) {
            return;
        }

        for(int i = 0; i < this.array.length; i++) {
            if(this.array[i] == fPos) {
                this.array[i] = sPos;
            }
        }
        this.size--;
    }

    public boolean connected(int f, int s) {
        return find(f) == find(s);
    }


    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
        DisplayUtils.printArray(uf.array);
        System.out.println(uf.connected(0, 7));
        System.out.println(uf.connected(2, 7));
        System.out.println(uf.connected(3, 7));
        System.out.println(uf.connected(3, 9));

    }

}
