/**
 * @Author: yxkong
 * @Date: 2021/6/3 5:57 下午
 * @version: 1.0
 */
package com.yxkong.demo.domain.service;

import com.yxkong.demo.domain.constant.SysConfigConstant;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.dto.resp.DistributePath;
import com.yxkong.demo.domain.gateway.ConfigGateway;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.model.quota.QuotaEntity;
import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.dto.ResultBean;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


/**
 * 授信分发服务
 * @author ducongcong
 * @create 2021/6/3
 * @since 1.0.0
 */
@DomainService
@Builder
public class AuthDistributeService {
     private final ConfigGateway configGateway;
     private final QuotaGateway quotaGateway;

     public AuthDistributeService(ConfigGateway configGateway, QuotaGateway quotaGateway) {
          this.configGateway = configGateway;
          this.quotaGateway = quotaGateway;
     }

     /**
      * 流程分发业务逻辑
      * @param context
      * @return
      */
     public ResultBean<DistributeDTO> distribute(DistributeContext context){
          String mobile = context.getMobile();
          //获取走新流程的手机尾号或者手机号，满足其一就能走新流程
          String  mobileOld = configGateway.query(SysConfigConstant.AUTH_DISTRIBUTE_MOBILE, SysConfigConstant.DEFAULT_CLOSE);
          String  mobileNumOld = configGateway.query(SysConfigConstant.AUTH_DISTRIBUTE_MOBILE_NUM, SysConfigConstant.DEFAULT_CLOSE);
          boolean isNewFlow = false;
          if(StringUtils.isNotBlank(mobileOld) && StringUtils.contains(mobileOld, context.getMobile())){
               isNewFlow = true;
          }
          if(StringUtils.isNotBlank(mobileNumOld) && StringUtils.contains(mobileNumOld, mobile.substring(mobile.length()-1))){
               isNewFlow = true;
          }
          if(!isNewFlow){
               return resultBean(DistributePath.source);
          }
          if (!context.getCustomerId().isRealName()){
               return resultBean(DistributePath.flow);
          }
          final QuotaEntity quotaEntity = quotaGateway.queryQuota(context.getCustomerId());
          if(Objects.nonNull(quotaEntity) && !quotaEntity.isInit()){
               return resultBean(DistributePath.list);
          }

          return new ResultBean.Builder<DistributeDTO>()
                  .success(DistributeDTO.builder()
                          .distributePath(DistributePath.waiting).taskId(1L).build()
             ).build();
     }
     private ResultBean<DistributeDTO> resultBean(DistributePath distributePath){
          return new ResultBean.Builder<DistributeDTO>()
                  .success(DistributeDTO.builder()
                               .distributePath(distributePath).build()
                  ).build();
     }
}
