package com.jiaoery.emos.wx.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_config
 */
@Data
public class SysConfig implements Serializable {
    private static final long serialVersionUID = 7213781226906443225L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 参数名
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}