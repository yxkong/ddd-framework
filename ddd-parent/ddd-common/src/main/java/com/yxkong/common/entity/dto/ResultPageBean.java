package com.yxkong.common.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * 通用分页返回实体,
 *   使用SuperBuilder解决Builder 无法继承的问题
 * @author yxkong
 * @param <T>
 */
@ApiModel("通用返回实体")
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
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
