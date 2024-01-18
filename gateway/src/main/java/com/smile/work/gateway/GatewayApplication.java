package com.smile.work.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/18 20:26
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

    /**
     * 设置系统默认时区
     **/
    @PostConstruct
    void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
