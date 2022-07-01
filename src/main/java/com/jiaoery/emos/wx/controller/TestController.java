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
import com.jiaoery.emos.wx.controller.form.TestSayHelloForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
@Api(tags = "测试Web接口")
public class TestController {

    @PostMapping("/sayHello")
    @ApiOperation("最简单的测试方法")
    public R sayHello(@Valid @RequestBody TestSayHelloForm form){
        return R.ok().put("message","Hello,"+form.getName());
    }
}

