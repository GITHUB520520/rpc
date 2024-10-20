package com.project.serializer;

import com.project.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory{

    public static volatile boolean isInit = false;

    public static void init(){
        if (!isInit){
            synchronized (SerializerFactory.class){
                if (!isInit){
                    SpiLoader.load(Serializer.class);
                    isInit = true;
                }
            }
        }
    }

    public static Serializer getSerializer(String key){
        init();
        return SpiLoader.getInstance(Serializer.class, key);
    }

    private static volatile Serializer defaultSerializer;

    public static Serializer getDefaultSerializer() {
        if (defaultSerializer == null) {
            synchronized (SerializerFactory.class) {
                if (defaultSerializer == null) {
                    defaultSerializer = new JdkSerializer();  // 默认序列化器
                }
            }
        }
        return defaultSerializer;
    }

}
