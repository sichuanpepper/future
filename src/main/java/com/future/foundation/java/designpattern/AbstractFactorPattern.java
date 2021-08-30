package com.future.foundation.java.designpattern;

/**
 * https://refactoring.guru/design-patterns/abstract-factory
 *
 * Abstract pattern is a design pattern that let you produce families of related objects without specifying their concrete classes.
 *
 * Applicability:
 * - You are working with various families of related products.
 *
 *
 */
public class AbstractFactorPattern {
    /**
     * The abstract factory interface defines a set of method that client can use to produce difference instance.
     */
    private static interface AbstractPCAssemble {
        // assemble CPU and return its cost
        int assembleCPU();

        // assemble memory and return its cost
        int assembleMemory();
    }

    private static class EconomyPCAssemble implements AbstractPCAssemble {
        @Override
        public int assembleCPU() {
            System.out.println("Assembled an economy CPU, price is 100");
            return 100;
        }

        @Override
        public int assembleMemory() {
            System.out.println("Assembled an economy memory, price is 50");
            return 50;
        }
    }

    private static class DeluxePCAssemble implements AbstractPCAssemble {
        @Override
        public int assembleCPU() {
            System.out.println("Assembled a deluxe CPU, price is 800");
            return 800;
        }

        @Override
        public int assembleMemory() {
            System.out.println("Assembled a deluxe memory, price is 300");
            return 300;
        }
    }

    private static class Client {
        /**
         * Without specifying a concrete class
         *
         * If we want to assemble a new PC type, there's nothing need to change in the client.
         * @param assemble
         * @return
         */
        public int assemblePC(AbstractPCAssemble assemble) {
            return assemble.assembleCPU() + assemble.assembleMemory();
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
        AbstractPCAssemble pcAssemble = new DeluxePCAssemble();
        System.out.println(c.assemblePC(pcAssemble));
        System.out.println(c.assemblePC(new EconomyPCAssemble()));
    }
}
