#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.event;

import java.io.Serializable;

public class DelayTaskEntity<T> implements Serializable {
    private Long delayTime;
    private String topic;
    private T data;
    private int tenantId;

    public DelayTaskEntity() {
    }

    public DelayTaskEntity(Long delayTime, String topic, T data, int tenantId) {
        this.delayTime = delayTime;
        this.topic = topic;
        this.data = data;
        this.tenantId = tenantId;
    }

    public Long getDelayTime() {
        return this.delayTime;
    }

    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }
}

