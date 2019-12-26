package pers.gh.study.base.reflect;

import pers.gh.study.base.entities.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflect {
    public static void main(String[] args) throws Exception {
        User user = new User(1, "gh", "123456");
        Field nameField = User.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(user, "guhao");
        Method sayHello = User.class.getMethod("sayHello");
        sayHello.invoke(user);
    }
}
