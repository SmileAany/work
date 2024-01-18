package com.smile.work.gateway.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: smile
 * @title: auth属性
 * @projectName: gateway
 * @description: auth属性
 * @date: 2023/12/28 20:21
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties("path")
public class AuthoriseProperty {
    /**
    * token basic
    **/
    private String basic;

    /**
    * query参数标识
    **/
    private String queryToken;

    /**
    * authorization
    **/
    private String authorization;

    /**
    * 忽略路由
    **/
    private List<String> ignore;
}
