package com.project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExamplePowerMock.class)
public class ExamplePowerMockTest {

    private ExamplePowerMock example;

    @Before
    public void prepare() {
        example = new ExamplePowerMock();
    }

    @Test
    public void testPrivateVariable() {
        System.out.println("value private int");
        int result = Whitebox.getInternalState(example, "privateInt");
        System.out.println(result);

        System.out.println("change private int value");
        Whitebox.setInternalState(example, "privateInt", 2);
        result = Whitebox.getInternalState(example, "privateInt");
        System.out.println(result);
    }

    @Test
    public void testPrivateStaticVariable() {
        System.out.println("value private static int");
        int result = Whitebox.getInternalState(ExamplePowerMock.class, "privateStaticInt");
        System.out.println(result);

        System.out.println("change private static int value");
        Whitebox.setInternalState(ExamplePowerMock.class, "privateStaticInt", 3);
        result = Whitebox.getInternalState(ExamplePowerMock.class, "privateStaticInt");
        System.out.println(result);
    }

    @Test
    public void testPublicStaticMethod() throws Exception {
        Whitebox.invokeMethod(example, "publicStaticMethod");
        Whitebox.invokeMethod(ExamplePowerMock.class, "publicStaticMethod");
    }

    @Test
    public void testPrivateMethod() throws Exception {
        Whitebox.invokeMethod(example, "privateMethodParam", 1);
        Whitebox.invokeMethod(example, "privateMethod");
    }

    @Test
    public void testPrivateStaticMethod() throws Exception {
        Whitebox.invokeMethod(example, "privateStaticMethod");
        Whitebox.invokeMethod(ExamplePowerMock.class, "privateStaticMethod");
    }
}
