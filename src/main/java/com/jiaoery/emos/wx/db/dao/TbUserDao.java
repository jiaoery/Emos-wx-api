package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface TbUserDao {

    int insert(TbUser record);

     boolean haveRootUser();

    Integer searchIdByOpenId(String openId);

    Set<String> searchUserpermission(int userid);

}