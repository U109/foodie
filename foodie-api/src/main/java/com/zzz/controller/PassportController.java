package com.zzz.controller;

import com.zzz.pojo.Users;
import com.zzz.pojo.bo.UserBO;
import com.zzz.service.UsersService;
import com.zzz.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangzhongzhen wrote on 2024/1/30
 * @version 1.0
 * @description:
 */
@RestController
@RequestMapping("passport")
@Api(value = "注册登录", tags = {"用户注册登录的相关接口"}) // API 分组
public class PassportController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户名是否存在", notes = "判断用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名")
    })
    public JSONResult usernameIsExist(@RequestParam String username) {
        // 1. 判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return JSONResult.errorMsg("用户名不能为空");
        }

        // 2. 查找注册的用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已存在");
        }
        // 3. 用户名没有重复
        return JSONResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户用户注册", httpMethod = "POST")
    @PostMapping("/registerUser")
    public JSONResult registerUser(@RequestBody UserBO userBO) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();
        // 0. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return JSONResult.errorMsg("用户名或密码不能为空");
        }
        // 1. 查询用户名是否存在
        boolean isExist = usersService.queryUsernameIsExist(username);
        if (isExist) {
            return JSONResult.errorMsg("用户名已经存在");
        }
        // 2. 密码长度不能少于 6 位
        if (password.length() < 6) {
            return JSONResult.errorMsg("密码长度不能少于 6");
        }
        // 3. 判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return JSONResult.errorMsg("两次密码输入不一致");
        }
        // 4. 实现注册
        Users user = usersService.createUser(userBO);
        return JSONResult.ok(user);
    }
}
