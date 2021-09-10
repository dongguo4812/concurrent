package com.dongguo.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;


/**
 * @author Dongguo
 * @date 2021/9/7 0007-10:25
 * @description:
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 24);
        User li4 = new User("li4", 26);

        AtomicReference<User> atomicReferenceUser = new AtomicReference<>();

        atomicReferenceUser.set(z3);
        //如果User是z3对象就修改为li4对象
        System.out.println(atomicReferenceUser.compareAndSet(z3, li4) + "\t" + atomicReferenceUser.get().toString());
        System.out.println(atomicReferenceUser.compareAndSet(z3, li4) + "\t" + atomicReferenceUser.get().toString());
    }
}

@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}