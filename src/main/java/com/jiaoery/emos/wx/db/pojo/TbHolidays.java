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
    private static final long serialVersionUID = -4387811330830198141L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;
    
}