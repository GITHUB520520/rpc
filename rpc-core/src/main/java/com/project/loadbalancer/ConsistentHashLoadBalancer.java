package com.project.loadbalancer;

import com.project.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于哈希一致性算法实现负载均衡
 */
public class ConsistentHashLoadBalancer implements LoadBalancer {

    /**
     * key 为哈希值，value 为对应的服务信息
     */
    private final TreeMap<Integer, ServiceMetaInfo> map = new TreeMap<>();

    /**
     * 虚拟节点个数
     */
    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) return null;
        if (serviceMetaInfoList.size() == 1) return serviceMetaInfoList.get(0);
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 1; i <= VIRTUAL_NODE_NUM; i++) {
                String key = serviceMetaInfo.getServiceAddress() + "#" + i;
                int hash = getHash(key);
                map.put(hash, serviceMetaInfo);
            }
        }
        int hash = getHash(requestParams);
        Map.Entry<Integer, ServiceMetaInfo> serviceMetaInfoEntry = map.ceilingEntry(hash);
        if (serviceMetaInfoEntry == null) {
            return map.firstEntry().getValue();
        } else {
            return serviceMetaInfoEntry.getValue();
        }
    }

    public int getHash(Object key) {
        return key.hashCode();
    }
}
