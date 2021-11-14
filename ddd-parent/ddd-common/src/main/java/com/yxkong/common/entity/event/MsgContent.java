package com.yxkong.common.entity.event;


import com.yxkong.common.exception.ParamsRuntimeException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * 事件包装体
 *
 * @Author: yxkong
 * @Date: 2021/4/5 8:28 下午
 * @version: 1.0
 */
@ApiModel("通用事件包装体")
@NoArgsConstructor
public class MsgContent<T> implements Serializable {
    private static final long serialVersionUID = -5115473473963556887L;

    /**
     * 来源的项目名称,使用驼峰，英文
     */
    @ApiModelProperty("来源的项目名称,使用驼峰，英文")
    protected String serviceName;

    /**
     * 消息类型，用于标识事件的唯一性，消息第一级，例如：credit
     */
    private String msgType;
    /**
     * 消息节点
     */
    @ApiModelProperty("消息节点")
    protected String msgNode;
    /**
     * 消息发送的时间戳
     */
    @ApiModelProperty("消息发送的时间戳")
    protected Long sendTime;
    /**
     * 消息唯一标识，如果没有data进行md5
     */
    @ApiModelProperty("消息唯一标识，如果没有data进行md5")
    protected String msgId;

    /**
     * 消息体
     */
    @ApiModelProperty("消息体")
    protected T data;
    /**
     * 消息状态，1成功，0，失败
     */
    @ApiModelProperty("消息状态，1成功，0，失败")
    private Integer status;

    /**
     * 用户操作来源
     */
    private String proId;
    private String source;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 万卡用户唯一标识
     */
    private Long customerId;
    private Integer tenantId;

    /**
     * 身份证号（不建议使用,尽量别传）
     */
    private String certId;

    /**
     * 身份类型 1 mobile 手机号, 2 customerId 用户唯一标识, 4 certId 身份证号, 3 mobile+customerId 6 customerId+certId 7
     * mobile+customerId+certId
     */
    private Integer userType;

    /**
     * 失败信息
     */
    private String errorMsg;

    protected MsgContent(Builder<T> builder) {
        this.serviceName = builder.serviceName;
        this.msgType = builder.msgType;
        this.msgNode = builder.msgNode;
        this.sendTime = builder.sendTime;
        this.msgId = builder.msgId;
        this.data = builder.data;
        this.status = builder.status;
        this.proId = builder.proId;
        this.source = builder.source;
        this.mobile = builder.mobile;
        this.customerId = builder.customerId;
        this.tenantId = builder.tenantId;
        this.certId = builder.certId;
        this.userType = builder.userType;
        this.errorMsg = builder.errorMsg;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getMsgNode() {
        return msgNode;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public String getMsgId() {
        return msgId;
    }

    public T getData() {
        return data;
    }

    public String getMsgType() {
        return msgType;
    }

    public Integer getStatus() {
        return status;
    }

    public String getProId() {
        return proId;
    }

    public String getSource() {
        return source;
    }

    public String getMobile() {
        return mobile;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCertId() {
        return certId;
    }

    public Integer getUserType() {
        return userType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static class Builder<T> {

        /**
         * 消息类型，用于标识事件的唯一性，消息第一级，例如：credit
         */
        private String msgType;
        protected String serviceName;
        protected String msgNode;
        protected Long sendTime;
        protected String msgId;
        protected T data;
        protected Integer status = 1;
        private String proId;
        private String source;
        private String mobile;
        private Long customerId;
        private String certId;
        private Integer userType = 0;
        private String errorMsg;
        private Integer tenantId;

        public Builder() {
        }

        public MsgContent<T> build() {
            if (Objects.isNull(data)) {
                throw new ParamsRuntimeException("data is null");
            }
            if (Objects.isNull(msgId)) {
                this.initMsgId();
            }
            if (Objects.isNull(sendTime)) {
                this.sendTime = System.currentTimeMillis();
            }
            if (Objects.nonNull(this.mobile)) {
                this.userType = this.userType + 1;
            }
            if (Objects.nonNull(this.customerId) && this.customerId > 0L) {
                this.userType = this.userType + 2;
            }
            if (Objects.nonNull(this.certId)) {
                this.userType = this.userType + 4;
            }
            return new MsgContent<T>(this);
        }

        public Builder<T> serviceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder<T> msgNode(String msgNode) {
            this.msgNode = msgNode;
            return this;
        }

        public Builder<T> msgType(String msgType) {
            this.msgType = msgType;
            return this;
        }

        public Builder<T> proId(String proId) {
            this.proId = proId;
            return this;
        }

        public Builder<T> status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder<T> source(String source) {
            this.source = source;
            return this;
        }

        public Builder<T> mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder<T> customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }
        public Builder<T> tenantId(Integer tenantId) {
            this.tenantId = tenantId;
            return this;
        }
        public Builder<T> certId(String certId) {
            this.certId = certId;
            return this;
        }

        public Builder<T> errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public Builder<T> sendTime(long sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public Builder<T> msgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        private static MessageDigest messageDigest;

        static {
            try {
                messageDigest = MessageDigest.getInstance("md5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("没有这个md5算法！");
            }
        }

        protected void initMsgId() {
            if (!Objects.isNull(messageDigest)) {
                try (ByteArrayOutputStream out = new ByteArrayOutputStream(); ObjectOutputStream sOut = new ObjectOutputStream(out);) {
                    sOut.writeObject(this.data);
                    sOut.flush();
                    byte[] bytes = out.toByteArray();
                    messageDigest.update(bytes);
                    this.msgId = messageDigest.digest().toString();
                } catch (IOException e) {
                }
            }
            if (Objects.isNull(this.msgId)) {
                this.msgId = "hash" + this.data.hashCode();
            }
        }
    }
}