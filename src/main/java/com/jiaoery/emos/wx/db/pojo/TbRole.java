package com.jiaoery.emos.wx.db.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * tb_role
 * @author 
 */
@Data
public class TbRole implements Serializable {
    private static final long serialVersionUID = -4541103189774880684L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限集合
     */
    private Object permissions;
    
}