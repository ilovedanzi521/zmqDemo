package com.yss;

import io.protostuff.Tag;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName RpcRequest.java
 * @Description TODO
 * @createTime 2019年05月17日 09:41:00
 */
public class RpcRequest {
    @Tag(1)
    private String clazz;
    @Tag(2)
    private String method;
    @Tag(3)
    private Object[] params; //参数

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "clazz:"+clazz+
                ";method:"+method+
                ";params:"+Arrays.toString(params);
    }
}
