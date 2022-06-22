package com.jiaoery.emos.wx.config.shiro;

import org.springframework.stereotype.Component;

/**
 * ClassName:ThreadLocalToken
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/21 17:12
 */
@Component
public class ThreadLocalToken {
    private ThreadLocal<String> local = new ThreadLocal();

    public void setToken(String token){
        local.set(token);
    }

    public String getToken(){
        return local.get();
    }

    public void clear(){
        local.remove();
    }
}
