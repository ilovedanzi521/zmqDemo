package com.yss;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wanglei
 * @version 1.0.0
 * @ClassName ZmqRpcProxyServer.java
 * @Description TODO
 * @createTime 2019年05月17日 09:06:00
 */
@Service(value = "zmqRpcProxyServer")
public class ZmqRpcProxyServer {
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    /*
     *
     * @Description 发布对外服务
     * @param services
     * @param port
     * @Return void
     * @Date 2019/5/17 9:08
     * @Author wanglei
     */
    public void publisher( IUserService services, int port){
        ZMQ.Context context = ZMQ.context(1);
        // Socket to talk to clients
        ZMQ.Socket responder = context.socket(SocketType.REP);
//        ZMQ.Socket responder = context.socket(ZMQ.REP);
        responder.bind("tcp://*:"+port);
        while (!Thread.currentThread().isInterrupted()) {
            // Wait for next request from the client
            byte[] request = responder.recv(0);
//            System.out.println("Received Hello");
            // Do some 'work'
            //反序列化获取请求
            RpcRequest rpcRequest = SerializationUtil.deserialize(RpcRequest.class,request);
            //传入请求和响应
//            executorService.execute(new ProcessHandler(services,rpcRequest,responder));
            new ProcessHandler(services,rpcRequest,responder).run();
            // Send reply back to client
//            String reply = "World";
//            responder.send(reply.getBytes(), 0);
        }
        responder.close();
        context.term();

    }
}
