package com.future.foundation.java;

/**
 * There are four types of inner classes
 * - Nested inner class
 * - Method local inner class
 * - Anonymous inner class
 * - Static inner class
 */
public class InnerClassExample {
    private int outerVal = 100;

    private static int outerStaticVal = 1000;

    /**
     * Nested inner class associated with instance of outer class.
     * So static variable or function are not allowed in the inner class.
     */
    public class NestedInnerClass {
        // static is not allowed
//        private static int innerStaticVal = 200;

        public void print() {
            System.out.println("This is a nested inner class, is able to access variable in outer class: " + outerVal);
            System.out.println("This is a nested inner class, is able to access static variable in outer class: " + outerStaticVal);
        }
    }

    public static class StaticInnerClass {
        private int nonStaticInInnerClass = 300;
        public void print() {
            //it's not allowed to access non-static variable
//            System.out.println("This is a static inner class, is able to access variable in outer class: " + outerVal);
            System.out.println("This is a static inner class, is able to access variable in inner class: " + nonStaticInInnerClass);
            System.out.println("This is a static inner class, is able to access static variable in outer class: " + outerStaticVal);
        }

        public static void staticPrint() {
            //it's not allowed
//            System.out.println("This is a static inner class, is able to access variable in inner class: " + nonStaticInInnerClass);
        }
    }


    public void methodLocalInnerClass() {

        class MethodLocalInnerClass {
            public void print() {
                System.out.println("This is a method local inner class, is able to access variable in outer class: " + outerVal);
                System.out.println("This is a method local inner class, is able to access static variable in outer class: " + outerStaticVal);
            }
        }

        MethodLocalInnerClass mlic = new MethodLocalInnerClass();
        mlic.print();

    }


    public static void main(String[] args) {
        //since the nested inner class belongs to an instance of outer class, so to create an instance of inner class, we should do it like
        InnerClassExample outer = new InnerClassExample();
        NestedInnerClass nestedInner = outer.new NestedInnerClass();
        nestedInner.print();

        outer.methodLocalInnerClass();

        InnerClassExample.StaticInnerClass staticInnerClass = new InnerClassExample.StaticInnerClass();
        staticInnerClass.print();
    }

}
