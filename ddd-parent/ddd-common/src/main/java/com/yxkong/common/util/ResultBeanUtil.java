package com.yxkong.common.util;

import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.entity.dto.ResultPageBean;
import com.yxkong.common.exception.BaseResult;
import com.yxkong.common.exception.CommonException;

import java.util.Objects;

/**
 * ResultBean工具类
 * @Author: yxkong
 * @Date: 2021/9/10 2:50 下午
 * @version: 1.0
 */
public class ResultBeanUtil {

    /**
     * 返回成功
     * @return
     */
    public static ResultBean success(){
        return success(null);
    }

    /**
     * 返回成功，携带数据体
     * @param data 数据体
     * @return
     */
    public static ResultBean success(Object data){
        return statusEnum(ResultStatusEnum.SUCCESS,data);
    }

    /**
     * 返回泛型成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ResultBean<T> succ(T data){
        return statusEnum(ResultStatusEnum.SUCCESS,data);
    }
    /**
     * 返回成功，自定义message和数据体
     * @param msg  自定义message
     * @param data  数据体
     * @return
     */
    public static ResultBean success(String msg,Object data){
        return result(ResultStatusEnum.SUCCESS.getStatus(),msg,data);
    }
    /**
     * 返回泛型成功,自定义message和数据体
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ResultBean<T> succ(String msg,T data){
        return result(ResultStatusEnum.SUCCESS.getStatus(),msg,data);
    }
    private static ResultBean statusEnum(ResultStatusEnum statusEnum,Object data){
        return result(statusEnum.getStatus(), statusEnum.getMessage(), data);
    }


    /**
     * 返回失败
     * @return
     */
    public static ResultBean fail(){
        return fail(null);
    }

    /**
     * 返回失败，自定义数据体
     * @param data 自定义数据体
     * @return
     */
    public static ResultBean fail(Object data){
        return statusEnum(ResultStatusEnum.ERROR,data);
    }

    /**
     * 返回失败，自定义message和数据体
     * @param msg  自定义message
     * @param data 自定义数据体
     * @return
     */
    public static ResultBean fail(String msg,Object data){
        return result(ResultStatusEnum.ERROR.getStatus(),msg,data);
    }

    /**
     * 根据ResultStatusEnum返回结果
     * @param resultStatusEnum 枚举
     * @return
     */
    public static ResultBean result(ResultStatusEnum resultStatusEnum){
        return statusEnum(resultStatusEnum,null);
    }

    /**
     *  根据BaseResult返回结果
     * @param baseResult 根据BaseResult中的值获取
     * @return
     */
    public static ResultBean result(BaseResult baseResult){
        return result(baseResult.getStatus(), baseResult.getMessage(), null);
    }

    /**
     * 根据CommonException 返回结果
     * @param commonException
     * @return
     */
    public static ResultBean result(CommonException commonException){
        return result(commonException.getStatus(),commonException.getMessage(),commonException.getCause());
    }

    /**
     * 返回结果
     * @param status 状态
     * @param msg  消息message
     * @param data  消息体
     * @return
     */
    public static <T>ResultBean<T> result(String status,String msg,T data){
        if (Objects.nonNull(data)){
            return ResultBean.<T>builder().status(status).message(msg).data(data).build();
        }
        return (ResultBean<T>) ResultBean.builder().status(status).message(msg).build();

    }
}