package com.yxkong.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.exception.BaseResult;
import com.yxkong.common.exception.ParamsRuntimeException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * api接口返回数据包装体
 *
 * @Author: yxkong
 * @Date: 2021/4/5 8:28 下午
 * @version: 1.0
 */
@ApiModel("通用返回实体")
@NoArgsConstructor
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

	private ResultBean(Builder<T> builder) {
		this.message = builder.message;
		this.status = builder.status;
		this.data = builder.data;
		this.timestamp = builder.timestamp;
		ResultBean.STATUS.set(status);
		ResultBean.MESSAGE.set(message);
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public Long getTimestamp() {
		return timestamp;
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

	public static class Builder<T> {
		private String message = ResultStatusEnum.SUCCESS.getMessage();
		private String status = ResultStatusEnum.SUCCESS.getStatus();
		private T data;
		/**
		 * 请求返回时间
		 */
		private Long timestamp = System.currentTimeMillis();

		public Builder() {
		}

		public ResultBean<T> build() {
			if (Objects.isNull(status)) {
				throw new ParamsRuntimeException("status must be exist");
			}
			return new ResultBean<T>(this);
		}

		public Builder<T> statusEnum(ResultStatusEnum statusEnum) {
			this.init(statusEnum);
			return this;
		}

		public Builder<T> statusEnum(BaseResult baseResult) {
			this.init(baseResult);
			return this;
		}

		public Builder<T> fail() {
			this.init(ResultStatusEnum.ERROR);
			return this;
		}

		public Builder<T> fail(T data) {
			this.init(ResultStatusEnum.ERROR);
			this.data = data;
			return this;
		}

		public Builder<T> success(T data) {
			this.init(ResultStatusEnum.SUCCESS);
			this.data = data;
			return this;
		}

		public Builder<T> success() {
			this.init(ResultStatusEnum.SUCCESS);
			return this;
		}

		private void init(ResultStatusEnum statusEnum) {
			this.status = statusEnum.getStatus();
			this.message = statusEnum.getMessage();
		}

		private void init(BaseResult baseResult) {
			this.status = baseResult.getStatus();
			this.message = baseResult.getMessage();
		}

		public Builder<T> message(String message) {
			this.message = message;
			return this;
		}

		public Builder<T> status(String status) {
			this.status = status;
			return this;
		}

		public Builder<T> data(T data) {
			this.data = data;
			return this;
		}
	}
}
