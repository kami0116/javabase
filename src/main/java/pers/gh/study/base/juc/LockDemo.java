package pers.gh.study.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print2() throws InterruptedException {
        lock.lock();
        while (number != 1) {
            c1.await();
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 2;
        c2.signal();
        lock.unlock();

    }

    public void print3() throws InterruptedException {
        lock.lock();
        while (number != 2) {
            c2.await();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 3;
        c3.signal();
        lock.unlock();
    }

    public void print4() throws InterruptedException {
        lock.lock();
        while (number != 3) {
            c3.await();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 1;
        c1.signal();
        lock.unlock();
    }


    public static void main(String[] args) {
        LockDemo demo = new LockDemo();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.print2();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.print3();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    demo.print4();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
