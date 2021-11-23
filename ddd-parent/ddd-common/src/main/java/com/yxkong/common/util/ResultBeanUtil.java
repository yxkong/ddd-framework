package com.yxkong.common.util;

import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.exception.BaseResult;
import com.yxkong.common.exception.CommonException;

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
        return new ResultBean.Builder().success(data).build();
    }

    /**
     * 返回成功，自定义message和数据体
     * @param msg  自定义message
     * @param data  数据体
     * @return
     */
    public static ResultBean success(String msg,Object data){
        return new ResultBean.Builder().success(data).message(msg).build();
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
        return new ResultBean.Builder().fail(data).build();
    }

    /**
     * 返回失败，自定义message和数据体
     * @param msg  自定义message
     * @param data 自定义数据体
     * @return
     */
    public static ResultBean fail(String msg,Object data){
        return new ResultBean.Builder().message(msg).fail(data).build();
    }

    /**
     * 根据ResultStatusEnum返回结果
     * @param resultStatusEnum 枚举
     * @return
     */
    public static ResultBean result(ResultStatusEnum resultStatusEnum){
        return new ResultBean.Builder().statusEnum(resultStatusEnum).build();
    }

    /**
     *  根据BaseResult返回结果
     * @param baseResult 根据BaseResult中的值获取
     * @return
     */
    public static ResultBean result(BaseResult baseResult){
        return new ResultBean.Builder().statusEnum(baseResult).build();
    }

    /**
     * 根据CommonException 返回结果
     * @param commonException
     * @return
     */
    public static ResultBean result(CommonException commonException){
        return new ResultBean.Builder().status(commonException.getStatus()).message(commonException.getMessage()).build();
    }

    /**
     * 返回结果
     * @param status 状态
     * @param msg  消息message
     * @param data  消息体
     * @return
     */
    public static ResultBean result(String status,String msg,String data){
        return new ResultBean.Builder().status(status).message(msg).data(data).build();
    }
}