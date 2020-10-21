package com.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InOrder;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExampleMockTest {

    private Example example;
    private ExampleMock exampleMock;

    @BeforeAll
    public void prepare() {
        example = mock(Example.class);
        exampleMock = new ExampleMock(example);
    }

    @Test
    public void testMethodAOrder() {
        example = spy(Example.class);

        example.method1();

        InOrder inOrder = inOrder(example);
        inOrder.verify(example).method2();
        inOrder.verify(example).method3();
    }

    @Test
    public void testMethodAValidate() {
        doNothing().when(example).method1();

        example.method1();

        assertTrue(exampleMock.isTrue());
        verify(example, times(1)).method1();
    }

    @AfterEach
    public void resetMock() {
        reset(example);
    }

}