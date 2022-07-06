package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: SysConfigDao
 * Description:SysConfigDao
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/6 17:03
 */
@Mapper
public interface SysConfigDao {
    public List<SysConfig> selectAllParam();
}
