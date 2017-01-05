package com.idea4j.service;

import com.idea4j.entity.User;

/**
 * Created by markee on 2017/1/3.
 */
public interface UserService {

    User findById(Long id);

    void save(User user);
}
