package pers.gh.study.base.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JUCUnsafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
