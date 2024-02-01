package com.fs.work.fastjson.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/19 15:27
 */
@RestController
@RequestMapping("/fastjson")
public class FastjsonController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("/test/redis")
    public Students testFastJsonRedis() {
        Students students = new Students(1, "FS001", 28, "smile", null);

        redisTemplate.opsForValue().set("admin",students);

        return students;
    }
}

@Data
@AllArgsConstructor
class Students {
    private Integer id;

    private String number;

    private Integer age;

    private String name;

    private List<Integer> grades;
}