##### 依赖下载
2.0.23版本之后为了兼容Spring 5.x / 6.x，将不同版本独立开不同的依赖包。
* 如使用2.0.23之前的版本
  ```xml
    <dependency>
        <groupId>com.alibaba.fastjson2</groupId>
        <artifactId>fastjson2-extension</artifactId>
        <version>2.0.x</version>
    </dependency>
  ```
* 如使用2.0.23之后的版本
```xml
    <dependency>
        <groupId>com.alibaba.fastjson2</groupId>
        <artifactId>fastjson2-extension-spring5</artifactId>
        <version>2.0.43</version>
    </dependency>

        or

    <dependency>
      <groupId>com.alibaba.fastjson2</groupId>
      <artifactId>fastjson2-extension-spring6</artifactId>
      <version>2.0.43</version>
    </dependency>
```

##### 参数配置
参数 | 类型 | 描述
---- | ---- | ----
charset | Charset | 指定的字符集，默认UTF-8
dateFormat | String | 指定的日期格式，默认yyyy-MM-dd HH:mm:ss
writerFilters | Filter[] | 配置序列化过滤器
writerFeatures | JSONWriter.Feature[] | 配置序列化的指定行为，更多配置请见：[Features](features_cn.md)
readerFilters | Filter[] | 配置反序列化过滤器
readerFeatures | JSONReader.Feature[] | 配置反序列化的指定行为，更多配置请见：[Features](features_cn.md)
jsonb | boolean | 是否采用JSONB进行序列化和反序列化，默认false
symbolTable | JSONB.SymbolTable | JSONB序列化和反序列化的符号表，只有使用JSONB时生效

##### JSONReader.Feature介绍
| JSONReader.Feature              | 介绍                                                                                                  |
|---------------------------------|-----------------------------------------------------------------------------------------------------|
| FieldBased                      | 基于字段反序列化，如果不配置，会默认基于public的field和getter方法序列化。配置后，会基于非static的field（包括private）做反序列化。在fieldbase配置下会更安全 |
| IgnoreNoneSerializable          | 反序列化忽略非Serializable类型的字段                                                                            |
| SupportArrayToBean              | 支持数据映射的方式                                                                                           |
| InitStringFieldAsEmpty          | 初始化String字段为空字符串""                                                                                  |
| SupportAutoType                 | 支持自动类型，要读取带"@type"类型信息的JSON数据，需要显式打开SupportAutoType                                                 |
| SupportSmartMatch               | 默认下是camel case精确匹配，打开这个后，能够智能识别camel/upper/pascal/snake/Kebab五中case                                 |
| UseNativeObject                 | 默认是使用JSONObject和JSONArray，配置后会使用LinkedHashMap和ArrayList                                             |
| SupportClassForName             | 支持类型为Class的字段，使用Class.forName。为了安全这个是默认关闭的                                                          |
| IgnoreSetNullValue              | 忽略输入为null的字段                                                                                        |
| UseDefaultConstructorAsPossible | 尽可能使用缺省构造函数，在fieldBase打开这个选项没打开的时候，会可能用Unsafe.allocateInstance来实现                                   | 
| UseBigDecimalForFloats          | 默认配置会使用BigDecimal来parse小数，打开后会使用Float                                                               |
| UseBigDecimalForDoubles         | 默认配置会使用BigDecimal来parse小数，打开后会使用Double                                                              |
| ErrorOnEnumNotMatch             | 默认Enum的name不匹配时会忽略，打开后不匹配会抛异常                                                                       |
| TrimString                      | 对读取到的字符串值做trim处理                                                                                    |
| ErrorOnNotSupportAutoType       | 遇到AutoType报错（缺省是忽略）                                                                                 |
| DuplicateKeyValueAsArray        | 重复Key的Value不是替换而是组合成数组                                                                              |
| AllowUnQuotedFieldNames         | 支持不带双引号的字段名                                                                                         |
| NonStringKeyAsString            | 非String类型的Key当做String处理                                                                             |
| Base64StringAsByteArray         | 将byte[]序列化为Base64格式的字符串                                                                             |
##### JSONWriter.Feature介绍
| JSONWriter.Feature                | 介绍                                                                                    |
|-----------------------------------|---------------------------------------------------------------------------------------|
| FieldBased                        | 基于字段序列化，如果不配置，会默认基于public的field和getter方法序列化。配置后，会基于非static的field（包括private）做序列化。      |
| IgnoreNoneSerializable            | 序列化忽略非Serializable类型的字段                                                               |
| BeanToArray                       | 将对象序列为[101,"XX"]这样的数组格式，这样的格式会更小                                                      |
| WriteNulls                        | 序列化输出空值字段                                                                             |
| BrowserCompatible                 | 在大范围超过JavaScript支持的整数，输出为字符串格式                                                        |
| NullAsDefaultValue                | 将空置输出为缺省值，Number类型的null都输出为0，String类型的null输出为""，数组和Collection类型的输出为[]                 |
| WriteBooleanAsNumber              | 将true输出为1，false输出为0                                                                   |
| WriteNonStringValueAsString       | 将非String类型的值输出为String，不包括对象和数据类型                                                      |
| WriteClassName                    | 序列化时输出类型信息                                                                            |
| NotWriteRootClassName             | 打开WriteClassName的同时，不输出根对象的类型信息                                                       |
| NotWriteHashMapArrayListClassName | 打开WriteClassName的同时，不输出类型为HashMap/ArrayList类型对象的类型信息，反序列结合UseNativeObject使用，能节省序列化结果的大小 |
| NotWriteDefaultValue              | 当字段的值为缺省值时，不输出，这个能节省序列化后结果的大小                                                         |
| WriteEnumsUsingName               | 序列化enum使用name                                                                         |
| WriteEnumUsingToString            | 序列化enum使用toString方法                                                                   |
| IgnoreErrorGetter                 | 忽略setter方法的错误                                                                         |
| PrettyFormat                      | 格式化输出                                                                                 |
| ReferenceDetection                | 打开引用检测，这个缺省是关闭的，和fastjson 1.x不一致                                                      |
| WriteNameAsSymbol                 | 将字段名按照symbol输出，这个仅在JSONB下起作用                                                          |
| WriteBigDecimalAsPlain            | 序列化BigDecimal使用toPlainString，避免科学计数法                                                  |
| UseSingleQuotes                   | 使用单引号                                                                                 |
| MapSortField                      | 对Map中的KeyValue按照Key做排序后再输出。在有些验签的场景需要使用这个Feature                                      |
| WriteNullListAsEmpty              | 将List类型字段的空值序列化输出为空数组"[]"                                                             |
| WriteNullStringAsEmpty            | 将String类型字段的空值序列化输出为空字符串""                                                            |
| WriteNullNumberAsZero             | 将Number类型字段的空值序列化输出为0                                                                 |
| WriteNullBooleanAsFalse           | 将Boolean类型字段的空值序列化输出为false                                                            |
| NotWriteEmptyArray                | 数组类型字段当length为0时不输出                                                                   |
| WriteNonStringKeyAsString         | 将Map中的非String类型的Key当做String类型输出                                                       |
| ErrorOnNoneSerializable           | 序列化非Serializable对象时报错                                                                 |
| WritePairAsJavaBean               | 将 Apache Common 包中的Pair对象当做JavaBean序列化                                                |
| BrowserSecure                     | 浏览器安全，将会'<' '>' '(' ')'字符做转义输出                                                        |
| WriteLongAsString                 | 将Long序列化为String                                                                       |
| WriteEnumUsingOrdinal             | 序列化Enum使用Ordinal，缺省是name                                                              | 
| WriteThrowableClassName           | 序列化Throwable时带上类型信息                                                                   |
| LargeObject                       | 这个是一个保护措施，是为了防止序列化有循环引用对象消耗过大资源的保护措施。                                                 |
| UnquoteFieldName                  | 不带引号输出Key                                                                             |
| NotWriteSetClassName              | 当打开WriteClassName时又不想输出Set的类型信息，使用这个Feature                                           |
| NotWriteNumberClassName           | 当打开WriteClassName时又不想输出Number的类型信息，比如L/S/B/F/D这种后缀，使用这个Feature                        |
##### 创建配置文件
Fastjson2Configuration
* WebMvcConfigurerAdapter 在Spring5.0中，已经被标记为过时，这里采用WebMvcConfigurer [参考文档](https://blog.csdn.net/qq_38164123/article/details/80392904)
* 创建配置文件 [参考文档](https://github.com/alibaba/fastjson2/blob/main/docs/spring_support_cn.md)
```java
package com.fs.work.fastjson.config;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/19 15:32
 */
@Configuration
public class Fastjson2Configuration implements WebMvcConfigurer {

  /**
   * 设置写入Feature
   * @author smile
   * @date 2024/1/19 16:14
   * @return com.alibaba.fastjson2.JSONWriter.Feature[]
   **/
  public static JSONWriter.Feature[] fastjsonFeature() {
    return new JSONWriter.Feature[] {
            JSONWriter.Feature.WriteNullListAsEmpty,
            //json格式化
            JSONWriter.Feature.PrettyFormat,
            //输出map中value为null的数据
            JSONWriter.Feature.WriteMapNullValue,
            //输出boolean 为 false
            JSONWriter.Feature.WriteNullBooleanAsFalse,
            //输出list 为 []
            JSONWriter.Feature.WriteNullListAsEmpty,
            //输出number 为 0
            JSONWriter.Feature.WriteNullNumberAsZero,
            //输出字符串 为 ""
            JSONWriter.Feature.WriteNullStringAsEmpty,
            //对map进行排序
            JSONWriter.Feature.MapSortField
    };
  }

  /**
   * 配置@RestController @ResponseBody @RequestBody注解的JSON序列化和反序列化
   * @param converters 转换器
   * @author smile
   * @date 2024/1/19 16:20
   **/
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //配置fastjson
    FastJsonConfig config = new FastJsonConfig();

    config.setDateFormat("yyyy-MM-dd HH:mm:ss");
    config.setCharset(StandardCharsets.UTF_8);
    config.setWriterFeatures(
            Fastjson2Configuration.fastjsonFeature()
    );

    //添加fastjson转换器
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    List<MediaType> supportedMediaTypes = new ArrayList<>();

    //添加支持的媒体类型
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
    supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
    supportedMediaTypes.add(MediaType.APPLICATION_PDF);
    supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
    supportedMediaTypes.add(MediaType.APPLICATION_XML);
    supportedMediaTypes.add(MediaType.IMAGE_GIF);
    supportedMediaTypes.add(MediaType.IMAGE_JPEG);
    supportedMediaTypes.add(MediaType.IMAGE_PNG);
    supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
    supportedMediaTypes.add(MediaType.TEXT_HTML);
    supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
    supportedMediaTypes.add(MediaType.TEXT_PLAIN);
    supportedMediaTypes.add(MediaType.TEXT_XML);

    converter.setSupportedMediaTypes(supportedMediaTypes);

    //将convert添加到converters
    converter.setFastJsonConfig(config);
    converters.add(0,converter);
  }
}
```
##### Spring Data Redis 中集成 Fastjson2
##### 引入依赖
```xml
  <!--redis-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
```
##### 创建RedisConfiguration
* 查看Jackson2JsonRedisSerializer的源码知道这个类是实现了RedisSerializer<>的，所以我们也要实现
* 创建FastJson2RedisSerializer [参考](https://blog.csdn.net/qq_43520746/article/details/133695597)
```java
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
```
* 创建RedisConfiguration
```java
package com.fs.work.fastjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/19 16:27
 */
@Configuration
public class RedisConfiguration {
  @Bean
  public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();

    template.setConnectionFactory(redisConnectionFactory);

    // 设置 key 的序列化方式为 String 类型
    template.setKeySerializer(new StringRedisSerializer());

    // 设置 value 的序列化方式为 Fastjson
    FastJson2RedisSerializer<Object> fastJson2RedisSerializer = new FastJson2RedisSerializer<>(Object.class);
    template.setValueSerializer(fastJson2RedisSerializer);

    // Hash 结构下 field 和 value 的序列化方式为 Fastjson
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(fastJson2RedisSerializer);

    template.afterPropertiesSet();
    return template;
  }
}
```

* 配置redis多数据库
```java
package com.fs.work.fastjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @author: smile
 * @title:
 * @projectName: sso
 * @description: TODO
 * @date: 2024/1/19 16:27
 */
@Configuration
public class RedisConfiguration {
    /**
    * 默认库
    **/
    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return createRedisTemplate(lettuceConnectionFactory, 0);
    }

    /**
     * 1号库
     **/
    @Bean(name = "firstTemplate")
    public RedisTemplate<String, Object> firstTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return createRedisTemplate(lettuceConnectionFactory, 1);
    }

    /**
     * 2号库
     **/
    @Bean(name = "secondTemplate")
    public RedisTemplate<String, Object> secondTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return createRedisTemplate(lettuceConnectionFactory, 2);
    }

    /**
     * 3号库
     **/
    @Bean(name = "thirdTemplate")
    public RedisTemplate<String, Object> thirdTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return createRedisTemplate(lettuceConnectionFactory, 3);
    }

    /***
     * 创建redis工具模板
     * @param lettuceConnectionFactory lettuceConnectionFactory
     * @param database 数据库
     * @author smile
     * @date 2024/2/16 14:29
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
    **/
    private RedisTemplate<String, Object> createRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory, int database) {
        // 需要创建一个新的连接工厂，因为在调用PropertiesSet后它是不可变的
        LettuceConnectionFactory newConnectionFactory = createConnectionFactory(lettuceConnectionFactory, database);

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(newConnectionFactory);

        // 设置 key 的序列化方式为 String 类型
        template.setKeySerializer(new StringRedisSerializer());

        // 设置 value 的序列化方式为 Fastjson
        FastJson2RedisSerializer<Object> fastJson2RedisSerializer = new FastJson2RedisSerializer<>(Object.class);
        template.setValueSerializer(fastJson2RedisSerializer);

        // Hash 结构下 field 和 value 的序列化方式为 Fastjson
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(fastJson2RedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 创建连接工厂
     * @param lettuceConnectionFactory lettuceConnectionFactory
     * @param database 数据库
     * @author smile
     * @date 2024/2/16 14:31
     * @return org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
    **/
    private LettuceConnectionFactory createConnectionFactory(LettuceConnectionFactory lettuceConnectionFactory, int database) {
        lettuceConnectionFactory.afterPropertiesSet();

        LettuceConnectionFactory newConnectionFactory = new LettuceConnectionFactory(lettuceConnectionFactory.getStandaloneConfiguration());
        newConnectionFactory.setDatabase(database);
        newConnectionFactory.setShareNativeConnection(lettuceConnectionFactory.getShareNativeConnection());
        newConnectionFactory.setValidateConnection(lettuceConnectionFactory.getValidateConnection());
        newConnectionFactory.afterPropertiesSet();

        return newConnectionFactory;
    }
}

```

##### 参考文档：
https://github.com/alibaba/fastjson2/blob/main/docs/spring_support_cn.md