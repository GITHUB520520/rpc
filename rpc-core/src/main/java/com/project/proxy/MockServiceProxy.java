package com.project.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class MockServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        String name = method.getName();
        log.info("MockServiceProxy invoke: method name: {}, return type: {}", name, returnType);
        return getObject(returnType);
    }

    public Object getObject(Class<?> returnType) {
        if (returnType.isPrimitive()){
            if (returnType == boolean.class) {
                return false;
            } else if (returnType == short.class) {
                return (short) 0;
            } else if (returnType == int.class) {
                return 0;
            } else if (returnType == long.class) {
                return 0L;
            }
        }
        return null;
    }
}
