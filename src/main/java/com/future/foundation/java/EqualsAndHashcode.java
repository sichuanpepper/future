package com.future.foundation.java;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsAndHashcode {
    private class InnerClazz {
        private int pk;

        private int val;

        public InnerClazz(int pk, int val) {
            this.pk = pk;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            InnerClazz that = (InnerClazz) o;
            return pk == that.pk;
        }

        @Override
        public int hashCode() {
            return Objects.hash(pk);
        }
    }

    private int key;

    private int val;

    public EqualsAndHashcode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public void test() {
        InnerClazz obj1 = new InnerClazz(1, 10);
        InnerClazz obj2 = new InnerClazz(1, 20);
        if(obj1.equals(obj2)) {
            System.out.println("equals to");
        }
        Set<InnerClazz> set = new HashSet<>();
        set.add(obj1);
        set.add(obj2);
        System.out.println(set.size());
        if(set.contains(new InnerClazz(1, 30))) {
            System.out.println("contains");
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EqualsAndHashcode that = (EqualsAndHashcode) o;
//        return key == that.key;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(key);
//    }

    public static void main(String[] args) {
//        EqualsAndHashcode obj1 = new EqualsAndHashcode(1, 10);
//        EqualsAndHashcode obj2 = new EqualsAndHashcode(1, 20);
//        if(obj1 == obj2) {
//            System.out.println("==");
//        }
//        if(obj1.equals(obj2)) {
//            System.out.println("equals to");
//        }
//        Set<EqualsAndHashcode> set = new HashSet<>();
//        set.add(obj1);
//        set.add(obj2);
//        System.out.println(set.size());
//        if(set.contains(new EqualsAndHashcode(1, 30))) {
//            System.out.println("contains");
//        }

        EqualsAndHashcode obj1 = new EqualsAndHashcode(1, 10);
        obj1.test();
    }
}
