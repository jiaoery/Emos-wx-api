package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.TbCity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author YCKJ1729
* @description 针对表【tb_city(疫情城市列表)】的数据库操作Mapper
* @createDate 2022-09-07 18:18:00
* @Entity com.jiaoery.emos.wx.db.pojo.TbCity
*/
@Mapper
public interface TbCityDao {
    public String searchCode(String city);
}
