package com.idea4j.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.idea4j.common.shardb.DatabaseContants;
import com.idea4j.common.shardb.DatabaseContextHolder;
import com.idea4j.dao.UserMapper;
import com.idea4j.entity.User;
import com.idea4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by markee on 2017/1/3.
 */
@Service("userService")
//@Service //dubbo的注解
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Long id) {
        System.out.println("###############"+ RpcContext.getContext().getAttachment(DatabaseContants.DB_KEY));
        //DatabaseContextHolder.setDatabaseKey("db2");
        return userMapper.selectByPrimaryKey(id);
    }
}
