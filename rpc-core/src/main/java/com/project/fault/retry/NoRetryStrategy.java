package com.project.fault.retry;

import com.project.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 不重试 - 重试策略
 *
 */
@Slf4j
public class NoRetryStrategy implements RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        RpcResponse rpcResponse = callable.call();
        log.info("触发不重试机制，内容为{}", rpcResponse);
        return rpcResponse;
    }

}
