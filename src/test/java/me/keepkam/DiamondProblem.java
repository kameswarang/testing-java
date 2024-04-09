package me.keepkam;

import org.junit.jupiter.api.Test;

class DiamondProblem {
    private class A {
        public void test() { System.out.println("This is A"); }
    }
    private class B extends A {
        @Override
        public void test() {
            System.out.println("This is B");
        }
    }
    private class C extends A {
        @Override
        public void test() {
            System.out.println("This is C");
        }
    }
    private class D extends B {}

    private interface IA {
//        void testabs();
        void test();
    }
    private interface IB extends IA {
//        void testabs();
        default void test() {
            System.out.println("This is IB");
        }
    }

    private interface IC extends IA {
//        void testabs();
        default void test() {
            System.out.println("This is IC");
        }
    }

    private class ID implements IB, IC {
        @Override
        public void test() {
            IB.super.test();
        }
    }

    @Test
    void test() {
        D d = new D();
        d.test();
        B b = d;
        b.test();


    }
}
