package com.yss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.zeromq.ZMQ;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName ProcessHandler.java
 * @Description TODO
 * @createTime 2019年05月17日 09:33:00
 */
@Slf4j
public class ProcessHandler {

    private RpcRequest request;
    private ZMQ.Socket responder;
    private IUserService service;
    public ProcessHandler(IUserService services, RpcRequest request, ZMQ.Socket responder) {
        this.request = request;
        this.responder = responder;
        this.service = services;
    }
    /*
     *
     * @Description 处理逻辑
     * @Return void
     * @Date 2019/5/17 10:12
     * @Author wanglei
     */
    public void run() {
        Result result= null;
        try {
            result = (Result) invoke(service,request);
        } catch (Exception e) {
            e.printStackTrace();
            result =   new Result(500,false,"error");
        }
        //序列化发送

        byte[] ts =SerializationUtil.serialize(result);

        responder.send(ts, 0);



    }

    public Object invoke(IUserService service,RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParams();
        Class<?>[] types = new Class[args.length];
        for (int i =0 ;i<args.length;i++){
            types[i] =args[i].getClass();
        }
        Class clazz = Class.forName(request.getClazz());

        Method method = clazz.getMethod(request.getMethod(),types);
        return method.invoke(service,args);
    }
}
