package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume(UUID.randomUUID().toString());
        Class<? extends Resume> resumeClass = r.getClass();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");

        Method method = resumeClass.getMethod("toString");
        Object ob = method.invoke(r);
        System.out.println(ob);
    }
}
