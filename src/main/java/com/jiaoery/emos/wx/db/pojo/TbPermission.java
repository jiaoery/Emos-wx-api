package com.jiaoery.emos.wx.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * tb_permission
 * @author 
 */
@Data
public class TbPermission implements Serializable {
    private static final long serialVersionUID = -9173949650150920114L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 权限
     */
    private String permissionName;

    /**
     * 模块ID
     */
    private Integer moduleId;

    /**
     * 行为ID
     */
    private Integer actionId;
    
}