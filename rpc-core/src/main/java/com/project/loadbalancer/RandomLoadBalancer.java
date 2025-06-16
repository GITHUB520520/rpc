package com.project.loadbalancer;

import com.project.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 使用随机算法实现负载均衡
 */
public class RandomLoadBalancer implements LoadBalancer {

    private final Random random = new Random();

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        int sz = serviceMetaInfoList.size();
        if (sz == 0) return null;
        else if (sz == 1) return serviceMetaInfoList.get(0);
        int currentIdx = random.nextInt(sz);
        return serviceMetaInfoList.get(currentIdx);
    }
}
