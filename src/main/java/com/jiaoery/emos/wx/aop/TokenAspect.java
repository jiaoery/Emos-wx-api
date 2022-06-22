package com.jiaoery.emos.wx.aop;

import com.jiaoery.emos.wx.common.util.R;
import com.jiaoery.emos.wx.config.shiro.ThreadLocalToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:TokenAspect
 * Description:切面类，做Shira和Jwt切面
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/22 14:43
 */
@Aspect
@Component
public class TokenAspect {
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * com.jiaoery.emos.wx.controller.*.*(..)))")
    public void aspect() {

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        R r = (R) point.proceed(); //方法执行结果
        String token = threadLocalToken.getToken();
        //如果ThreadLocal中存在Token，说明是更新的Token
        if (token != null) {
            r.put("token", token); //往响应中放置Token
            threadLocalToken.clear();
        }
        return r;
    }
}
