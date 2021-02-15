package com.future.foundation.java;

/**
 * Created by xingfeiy on 5/30/18.
 */
public class OuterClass {
    private int num = 10;

    private static int sNum = 100;

    /**
     * Purpose: Logical grouping
     */
    private class NestedClass {
        // private static int innerNum = 0; //inner class can't have any static member since it belong a instance.

        public void print() {
            System.out.println("num in nested class: " + num);
            System.out.println("sNum in nested class: " + sNum);
        }
    }

    private static class StaticNestClass {
        public void print() {
            //don't have access to member num
            System.out.println("sNum in static nested class: "+ sNum);
        }
    }


    public static void main(String[] args) {
        OuterClass o = new OuterClass(); //create instance of outer class first.
        OuterClass.NestedClass n = o.new NestedClass();
        n.print();

        OuterClass.StaticNestClass sn = new StaticNestClass();
        sn.print();
    }
}
