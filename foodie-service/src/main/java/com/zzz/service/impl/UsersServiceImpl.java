package com.zzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.enums.Sex;
import com.zzz.mapper.UsersMapper;
import com.zzz.pojo.Users;
import com.zzz.pojo.bo.UserBO;
import com.zzz.service.UsersService;
import com.zzz.utils.DateUtil;
import com.zzz.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.n3r.idworker.Sid;

import java.util.Date;

/**
 * @author zhangzhongzhen
 * @description 针对表【users(用户表 )】的数据库操作Service实现
 * @createDate 2024-01-30 22:49:15
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    public static final String USER_FACE = "http://localhost:900/xx.jpg";

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return usersMapper.exists(queryWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        Users users = new Users();
        users.setUsername(userBO.getUsername());
        try {
            // 使用自定义工具类对密码进行 MD5 加密
            users.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用 ID 生成器生成 ID
        users.setId(sid.nextShort());
        // 默认同用户昵称一样
        users.setNickname(userBO.getUsername());
        // 设置用户默认头像
        users.setFace(USER_FACE);
        // 默认生日为：1900-01-01
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        // 使用 枚举代替数字类型
        users.setSex(Sex.secret.type);
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());

        usersMapper.insert(users);
        // 返回用户是为了方便前端展示一些信息
        return users;
    }
}




