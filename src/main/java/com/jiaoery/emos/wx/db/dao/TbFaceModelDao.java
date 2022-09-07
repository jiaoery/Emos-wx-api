package com.jiaoery.emos.wx.db.dao;

import com.jiaoery.emos.wx.db.pojo.TbFaceModel;

/**
* @author YCKJ1729
* @description 针对表【tb_face_model】的数据库操作Mapper
* @createDate 2022-09-07 10:40:15
* @Entity com.jiaoery.emos.wx.db.pojo.TbFaceModel
*/
public interface TbFaceModelDao {

    int deleteFaceModel(Integer userId);

    int insert(TbFaceModel faceModel);

    String searchFaceModel(Integer userId);

}
 