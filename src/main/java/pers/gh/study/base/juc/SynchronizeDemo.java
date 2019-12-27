package pers.gh.study.base.juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public void sendEmail0() {
        System.out.println("send email");
    }

    public void sendEmail1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send email");
    }

    public synchronized void sendEmail3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send email");
    }

    public static synchronized void sendEmail4() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("send email");
    }

    public void sendSMS() {
        System.out.println("send SMS");
    }

    public synchronized void sendSMS1() {
        System.out.println("send SMS");
    }

    public static synchronized void sendSMS2() {
        System.out.println("send SMS");
    }
}