package com.jiaoery.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: LoginForm
 * Description:LoginForm
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/6/29 15:28
 */
@ApiModel
@Data
public class LoginForm {

    @NotBlank(message = "临时授权码不能为空")
    @ApiModelProperty(value = "微信临时授权",required = true,example = "o4MnO55VdlLlFQ71DKpH1OHeaE3s")
    private String code;
}
