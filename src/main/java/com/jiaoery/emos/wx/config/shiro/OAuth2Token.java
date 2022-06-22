package com.jiaoery.emos.wx.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * ClassName:Auth2Token
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/21 14:26
 */
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
