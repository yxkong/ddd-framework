package com.yxkong.demo.infrastructure.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxkong.common.exception.ParamsRuntimeExeception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: yxkong
 * @Date: 2021/5/19 9:40 上午
 * @version: 1.0
 */
@Slf4j
@Component
public class JacksonUtils {
    public static ObjectMapper objectMapper;
    @Autowired
    public void setJacksonObjectMapper(ObjectMapper objectMapper) {
        objectMapper = objectMapper;
    }
    /**
     * 经json转换成字符串，按实际情况
     *
     * @param object
     * @return
     * @author yxkong
     * @createDate 2016年7月6日
     * @updateDate
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("对象转json失败", e);
            throw new ParamsRuntimeExeception();
        }
    }

    /**
     * 将json转成对应的对象
     *
     * @param json
     * @param clazz
     * @return
     * @author yxkong
     * @createDate 2016年7月6日
     * @updateDate
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("json转对象失败", e);
            throw new ParamsRuntimeExeception();
        }
    }

    /**
     * 按类型转化
     *
     * @param json
     * @param type 例：  new TypeReference<Map<String, Object>>() {}
     * @return
     * @author yxkong
     * @createDate 2016年8月9日
     * @updateDate
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            log.error("json转对象失败", e);
            throw new ParamsRuntimeExeception();
        }
    }
}
