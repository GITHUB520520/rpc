package com.project.consumer;


import com.project.RpcApplication;
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
        // todo 需要获取 UserService 的实现类对象
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        RpcApplication.init(rpc);
        rpc = RpcApplication.getRpcConfig();
        System.out.println(rpc);
        // 静态代码
//        UserService userService = new UserServiceProxy();
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
