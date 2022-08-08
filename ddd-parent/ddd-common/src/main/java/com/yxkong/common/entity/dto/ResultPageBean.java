package com.yxkong.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用分页返回实体
 * @author yxkong
 * @param <T>
 */
@ApiModel("通用返回实体")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultPageBean<T> extends ResultBean<T> {
	private static final long serialVersionUID = -7602280271453240278L;
	@ApiModelProperty("当前页")
	private Integer pageNum;
	@ApiModelProperty("总条数")
	private Integer totalSize;
	@ApiModelProperty("总页数")
	private Integer pages;
	@ApiModelProperty("每页的数量")
	private Integer  pageSize;
}
