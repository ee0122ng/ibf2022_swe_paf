package iss.ibf2022.pafday08workshop.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    // @Value("${spring.redis.database}")
    // private Integer database;

    @Value("${spring.redis.username}")
    private String username;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    @Qualifier("my-redis")
    public RedisTemplate<String, Object> createRedisTemplate() {
        
        // configure redis
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        // setup redis environment
        System.out.println(">>>host: " + host);
        System.out.println(">>>port: " + port);
        // System.out.println(">>>host: " + database);
        System.out.println(">>>host: " + username);
        System.out.println(">>>host: " + password);
        config.setHostName(host);
        config.setPort(port);
        // config.setDatabase(database);

        if (null != password && null != username) {
            config.setUsername(username);
            config.setPassword(password);
        }

        // configure jedis client and connection factory
        final JedisClientConfiguration jedisClient = JedisClientConfiguration.builder().build();
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
        jedisFac.afterPropertiesSet();

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisFac);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        RedisSerializer<Object> serializerObj = new JdkSerializationRedisSerializer(getClass().getClassLoader());
        redisTemplate.setValueSerializer(serializerObj);
        redisTemplate.setHashValueSerializer(serializerObj);
        // redisTemplate.setHashValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }
    
}
