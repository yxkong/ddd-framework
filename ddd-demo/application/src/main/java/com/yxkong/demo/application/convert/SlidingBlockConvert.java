package com.yxkong.demo.application.convert;

import com.yxkong.common.exception.ApplicationException;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import com.yxkong.demo.domain.model.slidingblock.SlidingBlock;

import java.util.Map;

/**
 * 滑块转换器
 * @Author: yxkong
 * @Date: 2021/11/15 2:22 PM
 * @version: 1.0
 */
public class SlidingBlockConvert {

    /**
     * 获取 对应供应商的实现
     * @param map
     * @param slidingBlock
     * @return
     */
    public static  SlidingBlockSupplierGateway get(Map<String,SlidingBlockSupplierGateway> map, SlidingBlock slidingBlock){
        SlidingBlockSupplierGateway gateway = map.get(slidingBlock.getSlidingBlockSupplier()+"SlidingBlockGateway");
        if (gateway == null){
            throw new ApplicationException("3001",String.format("类型：%s 的供应商暂未实现",slidingBlock.getSlidingBlockSupplier() ));
        }
        return gateway;
    }
}
