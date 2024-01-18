package com.smile.work.gateway.service.impl;

import com.smile.work.gateway.property.AuthoriseProperty;
import com.smile.work.gateway.service.PermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;

import java.util.Objects;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/18 20:36
 */
public class PermissionServiceImpl implements PermissionService {
    /**
    * authorise 属性
    **/
    @Autowired
    private AuthoriseProperty authoriseProperty;

    /**
    * 路由匹配器
    **/
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 忽略路由
     *
     * @param url 路由地址
     * @return java.lang.Boolean
     * @author smile
     * @date 2024/1/18 20:35
     **/
    @Override
    public Boolean ignoreUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }

        return Objects.nonNull(authoriseProperty)
                && CollectionUtils.isNotEmpty(authoriseProperty.getIgnore())
                && authoriseProperty.getIgnore().stream().anyMatch(u -> ANT_PATH_MATCHER.match(u, url));

    }
}
