package com.idea4j.service.impl;


import com.idea4j.ServerLaucher;
import com.idea4j.common.shardb.DatabaseContextHolder;
import com.idea4j.entity.User;
import com.idea4j.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by markee on 2017/1/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerLaucher.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void save() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setName("看见我就没回滚");
        DatabaseContextHolder.setDatabaseKey("db1");
        userService.save(user);
    }

}