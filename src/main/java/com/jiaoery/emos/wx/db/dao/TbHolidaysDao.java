package com.jiaoery.emos.wx.db.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: TbHolidaysDao
 * Description:TbHolidaysDao
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/7 11:43
 */
@Mapper
public interface TbHolidaysDao {
    public Integer searchTodayIsHoliday();
}
