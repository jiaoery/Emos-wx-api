package com.jiaoery.emos.wx.db.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: TbWorkdayDao
 * Description:TbWorkdayDao
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/7 11:43
 */
@Mapper
public interface TbWorkdayDao {
    public Integer searchTodayIsWorkday();
}
