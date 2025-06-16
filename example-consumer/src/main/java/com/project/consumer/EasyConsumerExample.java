package com.project.consumer;


import com.project.RpcApplication;
import com.project.bootstrap.ConsumerBootstrap;
import com.project.common.model.User;
import com.project.common.service.UserService;
import com.project.config.RpcConfig;
import com.project.proxy.ServiceProxyFactory;
import com.project.utils.ConfigUtils;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {

    public static void main(String[] args) {

        ConsumerBootstrap.init();
        // 静态代码
        // UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yupi");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        int num = userService.getNumber();
        System.out.println(num);
    }
}
