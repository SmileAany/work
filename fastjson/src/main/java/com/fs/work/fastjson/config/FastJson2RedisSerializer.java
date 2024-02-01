package com.fs.work.fastjson.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/2/1 12:03
 */
@Slf4j
public class FastJson2RedisSerializer<T> implements RedisSerializer<T> {
    private final Class<T> clazz;

    static final Filter AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(
            "com.***.***"
    );

    public FastJson2RedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (Objects.isNull(t)) {
            return new byte[0];
        }

        try {
            return JSON.toJSONBytes(t, JSONWriter.Feature.WriteClassName);
        }catch (Exception exception) {
            log.error("fastjson2 序列化异常：{}", exception.getMessage());

            throw new SerializationException("序列化异常: " + exception.getMessage(), exception);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (ArrayUtils.isEmpty(bytes)) {
            return null;
        }

        try {
            return JSON.parseObject(bytes, clazz, AUTO_TYPE_FILTER);
        } catch (Exception exception) {
            log.error("fastjson2 反序列化异常：{}", exception.getMessage());

            throw new SerializationException("反序列化异常: " + exception.getMessage(), exception);
        }
    }
}
