package com.idea4j.controller;

import com.idea4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by markee on 2017/1/4.
 */
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return userService.findById(1L).getName();
    }
}
