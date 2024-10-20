package com.project.serializer;

import java.util.HashMap;
import java.util.Map;

public class SerializerFactory{

    public static final Map<String, Serializer> serializerMap = new HashMap<>(){{
        put(SerializerKeys.JDK, new JdkSerializer());
        put(SerializerKeys.JSON, new JsonSerializer());
        put(SerializerKeys.KRYO, new KryoSerializer());
        put(SerializerKeys.HESSIAN, new HessianSerializer());
    }};;

    public static Serializer getSerializer(String key){
        return serializerMap.get(key);
    }


    private static final Serializer DEFAULT_SERIALIZER = serializerMap.get(SerializerKeys.JDK);

}
