package com.jiaoery.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

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

    @ApiModelProperty(value = "准入时间开始")
    @NotNull(message = "face.validDate.length")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",iso = DateTimeFormat.ISO.DATE_TIME)
    private Date accessTimeB;

    @ApiModelProperty(value = "准入时间结束")
    @NotNull(message = "face.validDate.length")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss",iso = DateTimeFormat.ISO.DATE_TIME)
    private Date accessTimeE;
}