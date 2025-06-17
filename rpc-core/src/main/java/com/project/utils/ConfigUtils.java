package com.project.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.yaml.YamlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) throws IOException {
//        return loadConfig(tClass, prefix, "");
        return loadYamlConfig(tClass, prefix, "");
    }


    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());

        // 将以 prefix 为前缀的配置映射到 tClass 类中
        return props.toBean(tClass, prefix);
    }

    /**
     * 读取 YAML 文件内容
     * @param tClass
     * @param prefix
     * @param environment
     * @return
     * @param <T>
     * @throws IOException
     */
    public static <T> T loadYamlConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".yaml");
        String configFileName = configFileBuilder.toString();
        Map<String, Object> allConfig = YamlUtil.loadByPath(configFileName);
        Object prefixConfigObject = allConfig.get(prefix);
        return BeanUtil.toBean(prefixConfigObject, tClass);
    }

}
