package com.zzz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.mapper.UsersMapper;
import com.zzz.pojo.Users;
import com.zzz.service.UsersService;
import org.springframework.stereotype.Service;

/**
* @author zhangzhongzhen
* @description 针对表【users(用户表 )】的数据库操作Service实现
* @createDate 2024-01-30 22:49:15
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public boolean queryUsernameIsExist(String username) {
        return false;
    }
}




