package com.yxkong.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private LocalDateTime searchStartTime;
    private LocalDateTime searchEndTime;
    /** 请求参数 */
    private transient Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public LocalDateTime getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(LocalDateTime searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public LocalDateTime getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(LocalDateTime searchEndTime) {
        this.searchEndTime = searchEndTime;
    }
}
