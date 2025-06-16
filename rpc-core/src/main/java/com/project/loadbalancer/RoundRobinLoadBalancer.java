package com.project.loadbalancer;

import com.project.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用轮询算法实现负载均衡
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        int sz = serviceMetaInfoList.size();
        if (sz == 0) return null;
        else if (sz == 1) return serviceMetaInfoList.get(0);
        int currentIdx = atomicInteger.getAndIncrement() % sz;
        return serviceMetaInfoList.get(currentIdx);
    }
}
