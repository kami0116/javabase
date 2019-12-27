package pers.gh.study.base.jmm;

import java.util.concurrent.TimeUnit;

class Data {
    volatile int i = 0;
    int j = 0;

    public void update() {
        i = 1;
        j = 1;
        System.out.println("data的i和j的值已经由0改为1");
    }
}
class Data2 {
    int i = 0;

    public void update() {
        i = 1;
        System.out.println("data2的i的值已经由0改为1");
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        Data data = new Data();
        Data2 data2 = new Data2();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                data.update();
                data2.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        while (data.i == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "已获悉data.i被改变");
        while(data.j == 0){
        }
        System.out.println(Thread.currentThread().getName() + "已获悉data.j被改变");
        while (data2.i == 0) {
        }
        System.out.println(Thread.currentThread().getName() + "已获悉data2.i被改变");
    }
}
