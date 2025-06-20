package com.project.hurpcspringbootstarter.annotation;

import com.project.hurpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.project.hurpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.project.hurpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 Rpc 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;
}
