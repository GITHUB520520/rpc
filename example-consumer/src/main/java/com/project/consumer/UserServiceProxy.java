package com.project.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.project.RpcApplication;
import com.project.common.model.User;
import com.project.common.service.UserService;
import com.project.config.RpcConfig;
import com.project.model.RpcRequest;
import com.project.model.RpcResponse;
import com.project.serializer.JdkSerializer;
import com.project.serializer.Serializer;

import java.io.IOException;

/**
 * 静态代理
 */
public class UserServiceProxy implements UserService {

    public User getUser(User user) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            String url = "http://" + rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort();
            try (HttpResponse httpResponse = HttpRequest.post(url)
                    .body(bodyBytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
