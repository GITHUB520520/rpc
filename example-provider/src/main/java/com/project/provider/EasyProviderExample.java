package com.project.provider;


import com.project.RpcApplication;
import com.project.bootstrap.ProviderBootstrap;
import com.project.common.service.UserService;
import com.project.config.RegistryConfig;
import com.project.config.RpcConfig;
import com.project.model.ServiceMetaInfo;
import com.project.model.ServiceRegisterInfo;
import com.project.registry.LocalRegistry;
import com.project.registry.Registry;
import com.project.registry.RegistryFactory;
import com.project.server.HttpServer;
import com.project.server.VertxHttpServer;
import com.project.server.tcp.VertxTcpServer;
import com.project.utils.ConfigUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo();
        String serviceName = UserService.class.getName();
        serviceRegisterInfo.setServiceName(serviceName);
        serviceRegisterInfo.setImplClass(UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
