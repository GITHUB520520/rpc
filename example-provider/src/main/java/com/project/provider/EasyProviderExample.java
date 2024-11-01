package com.project.provider;


import com.project.RpcApplication;
import com.project.common.service.UserService;
import com.project.config.RegistryConfig;
import com.project.config.RpcConfig;
import com.project.model.ServiceMetaInfo;
import com.project.registry.LocalRegistry;
import com.project.registry.Registry;
import com.project.registry.RegistryFactory;
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
//        rpc = RpcApplication.getRpcConfig();
//        System.out.println(rpc);

        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(UserService.class.getName());
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort()); // 启动服务器
    }
}
