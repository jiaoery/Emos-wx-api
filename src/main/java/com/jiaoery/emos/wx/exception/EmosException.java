package com.jiaoery.emos.wx.exception;

import lombok.Data;

/**
 * ClassName:EmosException
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/20 15:04
 */
@Data
public class EmosException extends RuntimeException{
    private String msg;
    private int code = 500;

    public EmosException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public EmosException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public EmosException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public EmosException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
