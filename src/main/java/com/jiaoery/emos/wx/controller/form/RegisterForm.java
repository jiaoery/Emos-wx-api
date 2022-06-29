package com.jiaoery.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ClassName: RegisterForm
 * Description:RegisterForm
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/6/28 17:06
 */
@Data
@ApiModel
public class RegisterForm {
    @NotBlank(message = "注册码不能为空")
    @Pattern(regexp = "^\\d{6}$",message = "注册码必须是6位数字")
    @ApiModelProperty(value = "注册码",required = true,example = "000000")
    private String registerCode;

    @NotBlank(message = "微信临时授权不能为空")
    @ApiModelProperty(value = "微信临时授权",required = true,example = "o4MnO55VdlLlFQ71DKpH1OHeaE3s")
    private String code;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称",example = "jiaoery")
    private String nickname;

    @NotBlank(message = "照片不能为空")
    @ApiModelProperty(value = "照片地址",example = "")
    private String photo;
}
