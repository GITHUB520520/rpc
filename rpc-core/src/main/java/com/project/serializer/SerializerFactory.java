package com.project.serializer;

import com.project.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory{

//    public static final Map<String, Serializer> serializerMap = new HashMap<>(){{
//        put(SerializerKeys.JDK, new JdkSerializer());
//        put(SerializerKeys.JSON, new JsonSerializer());
//        put(SerializerKeys.KRYO, new KryoSerializer());
//        put(SerializerKeys.HESSIAN, new HessianSerializer());
//    }};;
//
//    public static Serializer getSerializer(String key){
//        return serializerMap.get(key);
//    }

    static {
        SpiLoader.load(Serializer.class);
    }

    public static Serializer getSerializer(String key){
        return SpiLoader.getInstance(Serializer.class, key);
    }


    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

}
