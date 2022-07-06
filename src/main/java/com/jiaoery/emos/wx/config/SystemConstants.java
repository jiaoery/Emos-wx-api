package com.jiaoery.emos.wx.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * ClassName: SystemConstants
 * Description:SystemConstants
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/6 17:08
 */
@Data
@Component
public class SystemConstants {
    public String attendanceStartTime;
    public String attendanceTime;
    public String attendanceEndTime;
    public String closingStartTime;
    public String closingTime;
    public String closingEndTime;
}
