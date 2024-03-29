package com.jiaoery.emos.wx.controller;

import com.jiaoery.emos.wx.common.util.R;
import com.jiaoery.emos.wx.config.shiro.JwtUtil;
import com.jiaoery.emos.wx.controller.form.LoginForm;
import com.jiaoery.emos.wx.controller.form.RegisterForm;
import com.jiaoery.emos.wx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: UserController
 * Description:UserController
 *
 * @author YCKJ1729
 * @version 1.1.0
 * @date 2022/6/28 18:06
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块web接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${emos.jwt.cache-expire}")
    private int cacheExpire;

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public R register(@Valid @RequestBody RegisterForm registerForm){
        int id = userService.registerUser(registerForm.getRegisterCode(), registerForm.getCode(), registerForm.getNickname(), registerForm.getPhoto());
        String token = jwtUtil.createToken(id);
        Set<String> permissions = userService.searchUserPermissions(id);
        saveCacheToken(token,id);
        return R.ok("用户注册成功").put("token",token).put("permission",permissions); 
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public R login(@Valid @RequestBody LoginForm loginForm){
        int id  = userService.login(loginForm.getCode());
        String token = jwtUtil.createToken(id);
        saveCacheToken(token,id);
        Set<String> permissions = userService.searchUserPermissions(id);
        return R.ok("登录成功").put("token",token).put("permissions",permissions);
    }

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    @RequiresPermissions(value = {"ROOT","USER:ADD"},logical = Logical.OR)
    public R addUser(){
        return R.ok("用户添加成功");
    }
    private void saveCacheToken(String token,int userId){
        redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
    }
}
