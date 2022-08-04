package com.yxkong.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yxkong.common.constant.ResultStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 通用分页返回实体
 * @param <T>
 */
@ApiModel("通用返回实体")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResultPageBean<T> implements Serializable {
	public static ThreadLocal<String> STATUS = new ThreadLocal<>();
	public static ThreadLocal<String> MESSAGE = new ThreadLocal<>();
	private static final long serialVersionUID = -7602280271453240278L;
	@ApiModelProperty("返回状态对应的描述")
	private String message;
	@ApiModelProperty("返回状态")
	private String status;
	@ApiModelProperty("当前页")
	private Integer pageNum;
	@ApiModelProperty("总页数")
	private Integer pages;
	@ApiModelProperty("每页的数量")
	private Integer  pageSize;
	@ApiModelProperty("返回数据体")
	private List<T> data;
	@ApiModelProperty("请求返回时间")
	private Long timestamp;

	public ResultPageBean(List<T> data,Integer pageNum,Integer pageSize,Integer totalSize) {
		this.data = data;
	}

	/**
	 * 格式化message
	 * @param args
	 */
	@JsonIgnore
	public ResultPageBean format(Object... args){
		this.message = String.format(message, args);
		return this;
	}

	@JsonIgnore
	public boolean isSucc() {
		if (ResultStatusEnum.SUCCESS.getStatus().equals(this.status)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	@JsonIgnore
	public static void remove() {
		STATUS.remove();
		MESSAGE.remove();
	}
	@Override
	public String toString() {
		return "ResultBean{" +
				"message='" + message + '\'' +
				", status='" + status + '\'' +
				", data=" + data +
				", timestamp=" + timestamp +
				'}';
	}
}
