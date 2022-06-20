package com.jiaoery.emos.wx.controller;

/**
 * ClassName:TestController
 * Description:
 *
 * @author jixiang
 * @version v1.0.0
 * @Date 2022/6/20 15:22
 */
import com.jiaoery.emos.wx.common.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api("测试Web接口")
public class TestController {

    @GetMapping("/sayHello")
    @ApiOperation("最简单的测试方法")
    public R sayHello(){
        return R.ok().put("message","HelloWorld");
    }
}

