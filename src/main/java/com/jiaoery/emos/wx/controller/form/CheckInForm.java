package com.jiaoery.emos.wx.controller.form;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ClassName: CheckInForm
 * Description:签到入参
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/9/7 14:40
 */
@Data
@ApiModel
public class CheckInForm {

    private String address;
    private String country;
    private String province;
    private String city;
    private String district;

}
