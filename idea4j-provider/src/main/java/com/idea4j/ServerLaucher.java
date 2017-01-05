package com.idea4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by markee on 2017/1/3.
 */
//@SpringBootApplication
//@ImportAutoConfiguration(DubboConfiguration.class)
@ImportResource({"classpath:dubboContext.xml"})
@ComponentScan("com.idea4j")
@MapperScan("com.idea4j.dao")
public class ServerLaucher {
    public static void main(String[] args) {
        SpringApplication.run(ServerLaucher.class, args);
        new Thread(){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }.start();
    }



}
