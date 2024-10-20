package com.project.provider;


import com.project.RpcApplication;
import com.project.common.service.UserService;
import com.project.config.RpcConfig;
import com.project.register.LocalRegistry;
import com.project.server.HttpServer;
import com.project.server.VertxHttpServer;
import com.project.utils.ConfigUtils;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        RpcApplication.init(rpc);
        rpc = RpcApplication.getRpcConfig();
        System.out.println(rpc);
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort()); // 启动服务器
    }
}
