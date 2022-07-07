package com.jiaoery.emos.wx.service;

import com.jiaoery.emos.wx.db.pojo.TbUser;

import java.util.Set;

/**
 * ClassName: UserService
 * Description:UserService
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/6/24 12:12
 */
public interface UserService {
    public int registerUser(String registerCode,String code,String nickName,String photo);

    public Set<String> searchUserPermissions(int userId);

    public Integer login(String code);

    public TbUser searchById(Integer userId);
}
