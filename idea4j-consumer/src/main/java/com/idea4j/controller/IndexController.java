package com.idea4j.controller;

import com.idea4j.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by markee on 2017/1/4.
 */
@Api(value="swagger2 api")
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="swagger2", notes="swagger2 note")
    @RequestMapping("/")
    public String index(){
        return userService.findById(1L).getName();
    }
}
