package com.zzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.pojo.Users;

/**
* @author zhangzhongzhen
* @description 针对表【users(用户表 )】的数据库操作Service
* @createDate 2024-01-30 22:49:15
*/
public interface UsersService extends IService<Users> {

    public boolean queryUsernameIsExist(String username);

}
