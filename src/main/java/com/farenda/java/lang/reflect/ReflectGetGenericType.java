package com.farenda.java.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ReflectGetGenericType {

    public static class SampleClass {

        public void returnsNothing() {
            System.out.println("Returns nothing");
        }

        public String returnsString() {
            return "Hello reflection";
        }

        public List<Integer> returnsListOfInts() {
            return Arrays.asList(1, 2, 3);
        }
    }

    public static void main(String[] args) {
        for (Method method : SampleClass.class.getDeclaredMethods()) {
            System.out.println("Method: " + method.getName());
            getReturnType(method);
            System.out.println();
        }
    }

    private static Class<?> getReturnType(Method method) {
        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("Return type: " + parameterizedType.getTypeName());
            type = parameterizedType.getActualTypeArguments()[0];
            System.out.println("Parameter type: " + type.getTypeName());
        } else {
            System.out.println("Return type: " + type);
        }
        return (Class<?>) type;
    }
}
