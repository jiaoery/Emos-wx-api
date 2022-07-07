package com.jiaoery.emos.wx.db.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 节假日表
 * @TableName tb_holidays
 */
@Data
public class TbHolidays implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;

    private static final long serialVersionUID = 1L;
}