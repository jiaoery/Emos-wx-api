package com.jiaoery.emos.wx.controller;

import cn.hutool.core.date.DateUtil;
import com.jiaoery.emos.wx.common.util.R;
import com.jiaoery.emos.wx.config.shiro.JwtUtil;
import com.jiaoery.emos.wx.service.CheckinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CheckinController
 * Description:CheckinController
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/7/7 15:27
 */
@RequestMapping("/checkin")
@RestController
@Api(tags = "签到模块web接口")
@Slf4j
public class CheckinController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CheckinService checkinService;

    @GetMapping("/validCanCheckIn")
    @ApiOperation("查看用户今天是否可以签到")
    public R validCanCheckin(@RequestHeader("token") String token){
        int userId = jwtUtil.getUserId(token);
        String result = checkinService.validCanChecKin(userId, DateUtil.today());
        return R.ok(result);
    }
}
