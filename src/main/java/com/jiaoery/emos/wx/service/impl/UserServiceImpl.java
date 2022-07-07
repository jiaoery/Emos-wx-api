package com.jiaoery.emos.wx.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiaoery.emos.wx.db.dao.TbUserDao;
import com.jiaoery.emos.wx.db.pojo.TbUser;
import com.jiaoery.emos.wx.exception.EmosException;
import com.jiaoery.emos.wx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.crypto.ExemptionMechanismException;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * ClassName: UserServiceImpl
 * Description:UserServiceImpl
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/6/24 12:12
 */
@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl implements UserService {
    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private TbUserDao userDao;

    /**
     * 从微信平台获取openid
     * @param code 临时code
     * @return
     */
    private String getOpenId(String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject json = JSON.parseObject(response);
        String openId = json.getString("openid");
        if(StrUtil.isEmpty(openId)){
            throw new RuntimeException("临时登录凭证错误");
        }
        return openId;
    }

    @Override
    public int registerUser(String registerCode, String code, String nickName, String photo) {
        //如果邀请码是000000，代表是超级管理员
        if(registerCode.equals("000000")){
            //查询超级管理员账户是否已经绑定
            boolean bool = userDao.haveRootUser();
            if(!bool){
                //把当前用户绑定到ROOT账户
                String openId = getOpenId(code);
                TbUser user =new TbUser();
                user.setOpenId(openId);
                user.setNickname(nickName);
                user.setPhoto(photo);
                user.setRole("[0]");
                user.setStatus((byte) 1);
                user.setCreateTime(new Date());
                user.setRoot(true);
                userDao.insert(user);
                int id  = userDao.searchIdByOpenId(openId);
                return id;
            }else {
                //若是root已经绑定了，就抛出异常
                throw new EmosException("无法绑定超级管理员账号");
            }
        }
        //TODO 此处还有其他操作
        else {
            return 0;
        }

    }

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permisssions = userDao.searchUserpermission(userId);
        return permisssions;
    }

    @Override
    public Integer login(String code) {
        String openId = getOpenId(code);
        Integer id = userDao.searchIdByOpenId(openId);
        if(id==null){
            throw new EmosException("账户不存在");
        }
        return id;
    }

    @Override
    public TbUser searchById(Integer userId) {
        TbUser user = userDao.searchById(userId);
        return user;
    }
}
