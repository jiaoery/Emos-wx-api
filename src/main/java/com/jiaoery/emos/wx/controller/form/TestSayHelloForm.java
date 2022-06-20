package com.jiaoery.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ClassName:TestSayHelloForm
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/20 16:16
 */
@ApiModel
@Data
public class TestSayHelloForm {
    @NotBlank(message = "姓名不可为空")
//    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,15}$",message = "输入姓名不符合规范")
    @ApiModelProperty("姓名")
    private String name;
}