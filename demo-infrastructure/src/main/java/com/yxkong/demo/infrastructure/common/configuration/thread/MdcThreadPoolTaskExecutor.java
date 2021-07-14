package com.yxkong.demo.infrastructure.common.configuration.thread;

import com.google.common.base.Strings;
import com.yxkong.demo.infrastructure.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Slf4j
public class MdcThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final long serialVersionUID = 53094863482765933L;

    private final static String LSH = "lsh";

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        if (log.isInfoEnabled()) {
            log.info("----MDC content:{}", context);
        }
        return super.submit(() -> {
            // 将父线程的MDC内容传给子线程
            T result;
            if (Objects.nonNull(context) && !context.isEmpty() && !Strings.isNullOrEmpty(context.get(LSH))) {
                MDC.setContextMap(context);
            } else {
                MDC.put(LSH, "demo_" + StringUtils.randomUUIDSplit()); //为空设置新值
            }
            try {
                result = task.call();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e2) {
                    log.error("mdc clear exception.", e2);
                }
            }
            return result;
        });
    }
}
