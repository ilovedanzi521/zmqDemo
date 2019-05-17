package com.yss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static int i=100;
    @Autowired
    UserService userService;
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "test")
    public Result welcome() {

        return userService.update(11);
    }

}
