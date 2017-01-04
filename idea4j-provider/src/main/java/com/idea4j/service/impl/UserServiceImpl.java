package com.idea4j.service.impl;

import com.idea4j.dao.UserMapper;
import com.idea4j.entity.User;
import com.idea4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by markee on 2017/1/3.
 */
@Component("userService")
//@Service //dubbo的注解
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
