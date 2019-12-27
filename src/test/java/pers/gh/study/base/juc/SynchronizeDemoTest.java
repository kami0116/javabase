package pers.gh.study.base.juc;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SynchronizeDemoTest {
    Phone phone;

    @Before
    public void before() {
        phone = new Phone();
    }

    /**
     * 一般情况，先发邮件，再发短信
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        new Thread(() -> phone.sendEmail0()).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> phone.sendSMS()).start();

        /*
         * send email
         * send SMS
         */
    }

    /**
     * 邮件时间等待，先发短信，再发邮件
     * @throws InterruptedException
     */
    @Test
    public void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> phone.sendSMS());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send SMS
         * send email
         */
    }

    /**
     * 邮件加同步，短信不加，先发短信，再发邮件
     * @throws InterruptedException
     */
    @Test
    public void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> phone.sendSMS());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send SMS
         * send email
         */
    }

    /**
     * 邮件和短信都加同步，先发邮件，再发短信。（同步）
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(() -> phone.sendSMS1());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send email
         * send SMS
         */
    }

    /**
     * 两部手机，短信邮件都加同步，先发短信，再发邮件（不同步）
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException {
        Phone phone2 = new Phone();
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(phone2::sendSMS1);
        t2.start();
        t1.join();
        t2.join();
        /*
         * send SMS
         * send email
         */
    }

    /**
     * 手机邮件都加静态同步，先发邮件，再发短信（同步）
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail4();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->phone.sendSMS2());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send email
         * send SMS
         */
    }

    /**
     * 邮件加静态同步，短信加对象同步，先发短信，后发邮件（不同步）
     * @throws InterruptedException
     */
    @Test
    public void test7() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail4();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->phone.sendSMS1());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send SMS
         * send email
         */
    }

    /**
     * 邮件加对象同步，用对象1；短信加静态同步，用对象2，先发短信，后发邮件（不同步）
     * @throws InterruptedException
     */
    @Test
    public void test8() throws InterruptedException {
        Phone phone2 = new Phone();
        Thread t1 = new Thread(() -> {
            try {
                phone.sendEmail3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->phone2.sendSMS2());
        t2.start();
        t1.join();
        t2.join();
        /*
         * send SMS
         * send email
         */
    }
}
