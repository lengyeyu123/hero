package com.han.hero.project.domain;

import com.han.hero.common.constants.DateConstants;
import com.han.hero.common.enums.OpenStatus;
import com.han.hero.framework.web.BaseDomain;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 学年学期
 */
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
     * 是否开启 默认开启
     */
    private OpenStatus openState;


    @SneakyThrows
    public static Semester getCurSemester() {
        Semester semester = new Semester();
        Date date = new Date();
        Integer year = Integer.parseInt(DateFormatUtils.format(date, "yyyy"));
        int month = Integer.parseInt(DateFormatUtils.format(date, "MM"));
        if (month < 9) {
            // 当年的2月1日到当年的8月31日为当前的第一学期
            semester.setStartDate(DateUtils.parseDate(year + "-02-01", DateConstants.yyyyMMdd));
            semester.setEndDate(DateUtils.parseDate(year + "-08-31", DateConstants.yyyyMMdd));
        } else {
            // 当年的9月1日到来年的1月31日为当前的第一学期
            semester.setStartDate(DateUtils.parseDate(year + "-09-01", DateConstants.yyyyMMdd));
            semester.setEndDate(DateUtils.parseDate(year + 1 + "-01-31", DateConstants.yyyyMMdd));
        }
        return semester;
    }

}
