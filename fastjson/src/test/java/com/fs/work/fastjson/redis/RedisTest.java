package com.fs.work.fastjson.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/2/16 11:58
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("admin","smile");

        String value = redisTemplate.opsForValue().get("admin").toString();

        assert value.equals("smile");
    }
}
