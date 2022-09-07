package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.TbCheckin;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
* @author YCKJ1729
* @description 针对表【tb_checkin(签到表)】的数据库操作Mapper
* @createDate 2022-07-07 11:45:12
* @Entity com.jiaoery.emos.wx.db.pojo.TbCheckin
*/
@Mapper
public interface TbCheckinDao {
    public Integer haveCheckin(HashMap hashMap);

    public void insert(TbCheckin tbCheckin);
}
