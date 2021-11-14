#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * @Author: ${author}
 * @Date: 2021/6/3 5:57 下午
 * @version: ${version}
 */
package ${package}.domain.service;

import ${package}.domain.constant.SysConfigConstant;
import ${package}.domain.dto.context.DistributeContext;
import ${package}.domain.dto.resp.DistributeDTO;
import ${package}.domain.dto.resp.DistributePath;
import ${package}.domain.gateway.ConfigGateway;
import ${package}.domain.gateway.QuotaGateway;
import ${package}.domain.model.quota.QuotaEntity;
import ${groupId}.common.annotation.DomainService;
import ${groupId}.common.entity.dto.ResultBean;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;


/**
 * 授信分发服务
 * @Author: ${author}
 * @create 2021/6/3
 * @since ${version}.0
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
          //获取开关
          /*String  flowTurn = configGateway.query(SysConfigConstant.AUTH_DISTRIBUTE_SWITCH_KEY, SysConfigConstant.DEFAULT_CLOSE);
          if(SysConfigConstant.DEFAULT_OPEN.equals(flowTurn)){
               return resultBean(DistributePath.source);
          }*/
          String mobile = context.getMobile();
          //获取走新流程的手机尾号或者手机号，满足其一就能走新流程
          String  mobileOld = configGateway.query(SysConfigConstant.AUTH_DISTRIBUTE_MOBILE, SysConfigConstant.DEFAULT_CLOSE);
          String  mobileNumOld = configGateway.query(SysConfigConstant.AUTH_DISTRIBUTE_MOBILE_NUM, SysConfigConstant.DEFAULT_CLOSE);
          boolean isXinFlow = false;
          if(StringUtils.isNotBlank(mobileOld) && StringUtils.contains(mobileOld, context.getMobile())){
        	  isXinFlow = true;
          }
          if(StringUtils.isNotBlank(mobileNumOld) && StringUtils.contains(mobileNumOld, mobile.substring(mobile.length()-1))){
        	  isXinFlow = true;
          }
          if(!isXinFlow){
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
