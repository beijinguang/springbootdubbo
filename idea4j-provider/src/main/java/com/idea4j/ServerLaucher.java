package com.idea4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by markee on 2017/1/3.
 */
@SpringBootApplication
//@ImportAutoConfiguration(DubboConfiguration.class)
@ImportResource({"classpath:dubboContext.xml"})
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
