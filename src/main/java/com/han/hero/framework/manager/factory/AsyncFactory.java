package com.han.hero.framework.manager.factory;

import com.han.hero.common.util.SpringUtil;
import com.han.hero.project.domain.OperLog;
import com.han.hero.project.service.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 异步工厂
 */
public class AsyncFactory {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");


    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final OperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(OperLogService.class).insertOperLog(operLog);
            }
        };
    }


}
