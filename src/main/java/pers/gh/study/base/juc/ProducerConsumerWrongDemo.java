package pers.gh.study.base.juc;

public class ProducerConsumerWrongDemo {
    int i = 0;

    public synchronized void increase() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" 抢到了锁");
        //1.判断
        if (i != 0) {
            System.out.println(Thread.currentThread().getName()+" 释放了锁");
            this.wait();
            System.out.println(Thread.currentThread().getName()+" 抢到了锁");
        }
        //2.干活
        i++;
        System.out.println(Thread.currentThread().getName()+" "+i);
        //3.通知
        System.out.println(Thread.currentThread().getName()+" 释放了锁");
        this.notifyAll();
    }

    public synchronized void decline() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" 抢到了锁");
        //1.判断
        if (i == 0) {
            System.out.println(Thread.currentThread().getName()+" 释放了锁");
            this.wait();
            System.out.println(Thread.currentThread().getName()+" 抢到了锁");
        }
        //2.干活
        i--;
        System.out.println(Thread.currentThread().getName()+" "+i);
        //3.通知
        System.out.println(Thread.currentThread().getName()+" 释放了锁");
        this.notifyAll();
    }

    /**
     *
     * 1.高内聚低耦合的情况下，线程操作资源类
     * 2.判断、干活、通知
     * 3.防止虚假唤醒
     */
    public static void main(String[] args) {
        ProducerConsumerWrongDemo pcd = new ProducerConsumerWrongDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.decline();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    pcd.decline();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

        while(Thread.activeCount()>2){}
        /**
         *
         * 错误示范：
         *
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * D 抢到了锁
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * B 抢到了锁
         * B 释放了锁
         * D 抢到了锁
         * D 0
         * D 释放了锁
         * B 抢到了锁
         * B 1
         * B 释放了锁
         * A 抢到了锁
         * A 2         //此处，A在waiting状态中抢到锁之后，退出了if语句，进入了生产流程；如果把if改成while，A线程会再次判断，发现i！=0会再次释放锁
         * A 释放了锁
         * A 抢到了锁
         * A 释放了锁
         * C 抢到了锁
         * C 1
         * C 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         * C 抢到了锁
         * C 释放了锁
         * A 抢到了锁
         * A 1
         * A 释放了锁
         * C 抢到了锁
         * C 0
         * C 释放了锁
         *
         * Process finished with exit code 0
         */
    }
}
