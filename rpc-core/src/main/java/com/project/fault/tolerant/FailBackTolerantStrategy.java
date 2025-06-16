package com.project.fault.tolerant;

import com.project.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 降级到其他服务 - 容错策略
 *
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setMessage("系统繁忙，请稍后重试！");
        return rpcResponse;
    }
}
