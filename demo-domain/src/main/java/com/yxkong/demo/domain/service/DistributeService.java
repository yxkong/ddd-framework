package com.yxkong.demo.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.constant.SysConfigConstant;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.dto.resp.DistributePath;
import com.yxkong.demo.domain.gateway.ConfigGateway;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.gateway.AuthTaskGateway;
import com.yxkong.demo.domain.model.authtask.AuthTask;
import com.yxkong.demo.domain.model.quota.QuotaEntity;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 分发服务
 * @Author: yxkong
 * @Date: 2021/7/13 2:14 下午
 * @version: 1.0
 */
@DomainService
@Builder
public class DistributeService {
    private final ConfigGateway configGateway;
    private final QuotaGateway quotaGateway;
    private final AuthTaskGateway authTaskGateway;

    public DistributeService(ConfigGateway configGateway, QuotaGateway quotaGateway, AuthTaskGateway authTaskGateway) {
        this.configGateway = configGateway;
        this.quotaGateway = quotaGateway;
        this.authTaskGateway = authTaskGateway;
    }

    /**
     * 流程分发业务逻辑
     * @param context
     * @return
     */
    public ResultBean<DistributeDTO> distribute(DistributeContext context){

        String mobile = context.getMobile();
        String tail = mobile.substring(mobile.length()-1);
        //获取走新流程的手机尾号或者手机号，满足其一就能走新流程
        String  accessMobile = configGateway.query(SysConfigConstant.DISTRIBUTE_MOBILE, SysConfigConstant.DEFAULT_CLOSE);
        String  accessMobileTail = configGateway.query(SysConfigConstant.DISTRIBUTE_MOBILE_TAIL, SysConfigConstant.DEFAULT_CLOSE);
        /**
         * 业务流程：
         * 1，包含指定手机号、或者是以某个手机尾号的直接走source流程
         * 2，用户未实名，直接走flow流程
         * 3，额度不为空，且额度不是初始化，直接走list流程；
         * 4，任务为空，直接走flow流程
         * 5，都没拦截到走waiting流程
         */
        if((StringUtils.isNotBlank(accessMobile) && StringUtils.contains(accessMobile, context.getMobile()))
                || (StringUtils.isNotBlank(accessMobileTail) && StringUtils.contains(accessMobileTail,tail))) {
            return resultBean(DistributePath.source);
        }
        if (!context.getCustomerId().isRealName()){
            return resultBean(DistributePath.flow);
        }
        final QuotaEntity quotaEntity = quotaGateway.queryQuota(context.getCustomerId());
        if(Objects.nonNull(quotaEntity) && !quotaEntity.isInit()){
            return resultBean(DistributePath.list);
        }
        AuthTask authTask = authTaskGateway.queryLatest(context.getCustomerId());
        if (authTask == null){
            return resultBean(DistributePath.flow);
        }
        return new ResultBean.Builder<DistributeDTO>()
                .success(DistributeDTO.builder()
                        .distributePath(DistributePath.waiting).taskId(authTask.getTaskId().getId()).build()
                ).build();
    }
    private ResultBean<DistributeDTO> resultBean(DistributePath distributePath){
        return new ResultBean.Builder<DistributeDTO>()
                .success(DistributeDTO.builder()
                        .distributePath(distributePath).build()
                ).build();
    }
}