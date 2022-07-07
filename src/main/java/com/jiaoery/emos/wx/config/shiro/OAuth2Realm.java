package com.jiaoery.emos.wx.config.shiro;

import com.jiaoery.emos.wx.db.pojo.TbUser;
import com.jiaoery.emos.wx.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:OAuth2Realm
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/21 16:40
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权，验证权限时使用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO 查询用户的权限列表
        //TODO 把权限列表添加到info对象中
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken = (String) authenticationToken.getPrincipal();
        int userId = jwtUtil.getUserId(accessToken);
        TbUser user = userService.searchById(userId);
        if(user==null){
            throw new LockedAccountException("账号已被锁定，请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,accessToken,getName());
        //TODO 往info对象中添加用户信息、Token字符串
        return info;
    }
}
