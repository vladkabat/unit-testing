package com.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Common.class)
public class CommonTest {

    @Test
    public void testCreateContainerConstructorIntRuntimeException() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int size = 10;
        Set<String> set = new HashSet<>();
        Class<HashSet> clazz = HashSet.class;
        PowerMockito.mockStatic(clazz);
        Constructor constructor = PowerMockito.mock(PowerMockito.defaultConstructorIn(clazz).getClass());

        PowerMockito.when(clazz.getDeclaredConstructor(int.class)).thenThrow(NoSuchMethodException.class);
        PowerMockito.when(clazz.getDeclaredConstructor()).thenReturn(constructor);
        PowerMockito.when(constructor.newInstance()).thenReturn(set);

        assertSame(set, Common.createContainer(size, clazz));
    }

    @Test
    public void testCreateContainerDefaultConstructorRuntimeException() throws NoSuchMethodException {
        int size = 10;
        Class<HashSet> clazz = HashSet.class;
        PowerMockito.mockStatic(clazz);

        PowerMockito.when(clazz.getDeclaredConstructor(int.class)).thenThrow(NoSuchMethodException.class);
        PowerMockito.when(clazz.getDeclaredConstructor()).thenThrow(NoSuchMethodException.class);

        Collection<Object> res = Common.createContainer(size, clazz);

        assertEquals(0, res.size());
        assertTrue(res instanceof ArrayList<?>);
    }

    @Test
    public void testCreateContainerClazzNotNull() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int size = 10;
        Set<String> set = new HashSet<>();
        Class<HashSet> clazz = HashSet.class;
        PowerMockito.mockStatic(clazz);
        Constructor c = PowerMockito.mock(PowerMockito.constructor(clazz, int.class).getClass());

        PowerMockito.when(clazz.getDeclaredConstructor(int.class)).thenReturn(c);
        PowerMockito.when(c.newInstance(size)).thenReturn(set);

        assertSame(set, Common.createContainer(size, clazz));
    }

    @Test
    public void testCreateContainerClazzNull() {
        int size = 10;
        Class<? extends Collection<?>> clazz = null;
        Collection<Object> res = Common.createContainer(size, clazz);

        assertEquals(0, res.size());
        assertTrue(res instanceof ArrayList<?>);
    }
}