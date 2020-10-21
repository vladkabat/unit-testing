package com.project;

public class ExamplePowerMock {

    private int privateInt = 5;
    private static int privateStaticInt = 6;

    private void privateMethod() {
        System.out.println("private method");
    }

    private static void privateStaticMethod() {
        System.out.println("private static method");
    }

    public static void publicStaticMethod() {
        System.out.println("public static method");
    }

    private void privateMethodParam(int i) {
        System.out.println("private method with int param = " + i);
    }
}
