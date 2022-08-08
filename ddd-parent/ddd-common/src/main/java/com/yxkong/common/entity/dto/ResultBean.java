package com.yxkong.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yxkong.common.constant.ResultStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * api接口返回数据包装体
 *
 * @Author: yxkong
 * @Date: 2021/4/5 8:28 下午
 * @version: 1.0
 */
@ApiModel("通用返回实体")
@Data
@SuperBuilder
public class ResultBean<T> implements Serializable {
	public static ThreadLocal<String> STATUS = new ThreadLocal<>();
	public static ThreadLocal<String> MESSAGE = new ThreadLocal<>();
	private static final long serialVersionUID = -7602280271453240278L;
	@ApiModelProperty("返回状态对应的描述")
	private String message;
	@ApiModelProperty("返回状态")
	private String status;
	@ApiModelProperty("返回数据体")
	private T data;
	@ApiModelProperty("请求返回时间")
	private Long timestamp;

	/**
	 * 格式化message
	 * @param args
	 */
	@JsonIgnore
	public ResultBean format( Object... args){
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
