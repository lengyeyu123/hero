package com.han.hero.project.domain;

import com.han.hero.common.enums.BusinessType;
import com.han.hero.common.enums.OperStatus;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志记录
 */
@Data
public class OperLog extends BaseDomain {

    /**
     * 日志主键
     */
    private Integer id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型 0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据
     */
    private BusinessType businessType;

    /**
     * 业务类型数组
     */
    private Integer[] businessTypes;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 操作人员
     */
    private String operName;

    /**
     * 请求地址
     */
    private String operUrl;

    /**
     * 操作ip地址
     */
    private String operIp;

    /**
     * 操作地点
     */
    private String operLocation;

    /**
     * 请求参数
     */
    private String operParam;

    /**
     * 返回结果
     */
    private String jsonResult;

    /**
     * 操作状态 0异常 1正常
     */
    private OperStatus status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作日期
     */
    private Date operTime;

}
