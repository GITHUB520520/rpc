package com.project;

import com.project.serializer.Serializer;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        // 指定序列化器
        Serializer serializer = null;
        ServiceLoader<Serializer> serviceLoader = ServiceLoader.load(Serializer.class);
        for (Serializer service : serviceLoader) {
            serializer = service;
            System.out.println(serializer);
        }

    }
}