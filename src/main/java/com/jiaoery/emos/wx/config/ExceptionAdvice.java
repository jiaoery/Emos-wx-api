package com.jiaoery.emos.wx.config;

import com.jiaoery.emos.wx.common.util.R;
import com.jiaoery.emos.wx.exception.EmosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ClassName:ExceptionAdvice
 * Description:全局异常封装类
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/22 14:57
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e){
        log.error("执行异常",e);
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return exception.getBindingResult().getFieldError().getDefaultMessage();
        }else if(e instanceof EmosException){
            EmosException exception = (EmosException) e;
            return exception.getMsg();
        }else if(e instanceof UnauthenticatedException){
            return "你不具备相关权限";
        }else {
            return "后端执行异常";
        }
    }
}
