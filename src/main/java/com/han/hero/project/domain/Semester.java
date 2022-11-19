package com.han.hero.project.domain;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.han.hero.common.enums.OpenStatus;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class Semester extends BaseDomain {

    private Integer id;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 是否开启
     */
    private OpenStatus openState;


    public static Semester getCurSemester() {
        Semester semester = new Semester();
        Date date = new Date();
        int year = DateUtil.year(date);
        int month = DateUtil.month(date);
        if (month < 9) {
            semester.setStartDate(DateUtil.parse(year + "-02-01", DatePattern.NORM_DATE_FORMAT));
            semester.setEndDate(DateUtil.parse(year + "-08-31", DatePattern.NORM_DATE_FORMAT));
        } else {
            semester.setStartDate(DateUtil.parse(year + "-09-01", DatePattern.NORM_DATE_FORMAT));
            semester.setEndDate(DateUtil.parse(year + 1 + "-01-31", DatePattern.NORM_DATE_FORMAT));
        }
        return semester;
    }

}
