package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserDao {

    int insert(TbUser record);

     boolean haveRootUser();

    Integer searchIdByOpenId(String openId);

}