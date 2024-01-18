package com.smile.work.gateway.service;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/18 20:35
 */
public interface PermissionService {
    /**
     * 忽略路由
     * @param url 路由地址
     * @author smile
     * @date 2024/1/18 20:35
     * @return java.lang.Boolean
    **/
    Boolean ignoreUrl(String url);
}
