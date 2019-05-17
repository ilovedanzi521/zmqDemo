package com.yss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ZmqDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ZmqDemoApplication.class, args);
		ZmqRpcProxyServer zmqRpcProxyServer = context.getBean(ZmqRpcProxyServer.class);
		IUserService userService = (IUserService) context.getBean("userService");
		zmqRpcProxyServer.publisher(userService,1234);
	}

}
