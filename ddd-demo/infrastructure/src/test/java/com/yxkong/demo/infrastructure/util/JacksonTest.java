package com.yxkong.demo.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yxkong.demo.infrastructure.common.util.CommonUtil;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

/**
 * @author navyzhung
 * @date 2021-07-08 14:34
 */
public class JacksonTest {

    public static void init() {
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai")).modules(new JavaTimeModule())
                .simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .build();
        CommonUtil.OBJECT_MAPPER = objectMapper;
    }



}
