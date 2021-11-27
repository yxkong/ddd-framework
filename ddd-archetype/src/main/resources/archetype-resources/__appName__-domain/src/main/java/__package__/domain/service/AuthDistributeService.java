#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * @Author: ${author}
 * @Date: 2021/6/3 5:57 下午
 * @version: ${version}
 */
package ${package}.domain.service;

import ${groupId}.common.util.ResultBeanUtil;
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
 * @author ducongcong
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
          String mobile = context.getMobile();
          //获取走新流程的手机尾号或者手机号，满足其一就能走新流程
          String  mobileOld = configGateway.query("xx", "close");
          String  mobileNumOld = configGateway.query("xx", "close");
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

          return resultBean(DistributePath.waiting);
     }
     private ResultBean<DistributeDTO> resultBean(DistributePath distributePath){
          return ResultBeanUtil.success(DistributeDTO.builder().distributePath(distributePath).build()) ;
     }
}
