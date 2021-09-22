package com.dongguo.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Dongguo
 * @date 2021/9/12 0012-21:32
 * @description:
 */
public class UnsafeAccessor {
    static Unsafe unsafe;
    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }
    static Unsafe getUnsafe() {
        return unsafe;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);

        Field id = Student.class.getDeclaredField("id");
        Field name = Student.class.getDeclaredField("name");
        // 获得成员变量的偏移量
        long idOffset = unsafe.objectFieldOffset(id);
        long nameOffset = unsafe.objectFieldOffset(name);
        Student student = new Student();
        // 使用 cas 方法替换成员变量的值
        unsafe.compareAndSwapInt(student, idOffset, 0, 20); // 返回 true   0为旧值 20为新值
        unsafe.compareAndSwapObject(student, nameOffset, null, "张三"); // 返回 true 旧值为null，新值为张三
        System.out.println(student);
    }
}
@Data
class Student {
    volatile int id;
    volatile String name;
}