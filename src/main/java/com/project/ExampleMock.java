package com.project;

public class ExampleMock {

    private Example example;

    public ExampleMock(final Example example) {
        this.example = example;
    }

    public void methodA() {
        System.out.println("test");
    }

    public boolean isTrue() {
        return true;
    }
}
