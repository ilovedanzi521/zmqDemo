package com.yss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2019年05月17日 08:58:00
 */
@Slf4j
@Service(value = "userService")
public class UserService implements IUserService {
    private static Result SUCC =  new Result(200,true,"succ");
    private static Result ERR =   new Result(500,false,"error");
    @Override
    public Result update(Integer id) {
        log.debug("调用server -update");
        return SUCC;
    }
}
