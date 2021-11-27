#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yxkong.common.exception.ParamsRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ${author}
 * @Date: 2021/5/19 9:40 上午
 * @version: ${version}
 */
@Slf4j
public class JsonUtils {
    public static ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = ApplicationContextHolder.getBean(ObjectMapper.class);
    }

    /**
     * 经json转换成字符串，按实际情况
     *
     * @param object
     * @return
     * @author ${author}
     * @createDate 2016年7月6日
     * @updateDate
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return CommonUtil.OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("对象转json失败", e);
            throw new ParamsRuntimeException();
        }
    }

    /**
     * 将json转成对应的对象
     *
     * @param json
     * @param clazz
     * @return
     * @author ${author}
     * @createDate 2016年7月6日
     * @updateDate
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return CommonUtil.OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("json转对象失败", e);
            throw new ParamsRuntimeException();
        }
    }

    /**
     * 按类型转化
     *
     * @param json
     * @param type 例：  new TypeReference<Map<String, Object>>() {}
     * @return
     * @author ${author}
     * @createDate 2016年8月9日
     * @updateDate
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return CommonUtil.OBJECT_MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            log.error("json转对象失败", e);
            throw new ParamsRuntimeException();
        }
    }
}
