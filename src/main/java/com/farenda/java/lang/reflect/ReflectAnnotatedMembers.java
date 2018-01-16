package com.farenda.java.lang.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ReflectAnnotatedMembers {

    public static class MyClass {

        @Findable(name = "The X field")
        private int x = 42;

        @Findable(name = "Addition")
        public int add(int a, int b) {
            return a + b;
        }
    }

    public static void main(String[] args) {
        findAnnotatedFields();
        findAnnotatedMethods();
    }

    private static void findAnnotatedFields() {
        for (Field f : MyClass.class.getDeclaredFields()) {
            processAnnotation(f, f);
        }
    }

    private static void findAnnotatedMethods() {
        for (Method m : MyClass.class.getDeclaredMethods()) {
            processAnnotation(m, m);
        }
    }

    private static void processAnnotation(Member member, AccessibleObject obj) {
        if (obj.isAnnotationPresent(Findable.class)) {
            Findable meta = obj.getAnnotation(Findable.class);
            String className = obj.getClass().getSimpleName();
            System.out.printf("Found %s: %s, with name: %s%n",
                    className, member.getName(), meta.name());
        }
    }
}
