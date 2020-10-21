package com.project;

import java.util.ArrayList;
import java.util.Collection;

public class Common {

    @SuppressWarnings("unchecked")
    public static <T> Collection<T> createContainer(int size, Class<? extends Collection> clazz) {
        Collection<T> resultCollection;
        if (clazz == null) {
            resultCollection = new ArrayList<>(size);
        } else {
            try {
                resultCollection = (Collection<T>) clazz.getDeclaredConstructor(int.class).newInstance(size);
            } catch (Exception e) {
                try {
                    resultCollection = (Collection<T>) clazz.getDeclaredConstructor().newInstance();
                } catch (Exception e1) {
                    resultCollection = new ArrayList<>(size);
                }
            }
        }
        return resultCollection;
    }
}