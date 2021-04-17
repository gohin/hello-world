package com.kure.test.zookeeper;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

public class RedisTest {

    private final static String lock = "lock";

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/buyMaoTai")
    public String buy(){
        // 设置成功了才返回 true
        String clientId = UUID.randomUUID().toString();

        // 这两行代码非原子 线程不安全
//        final Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lock, "lock"); // 加锁
//        stringRedisTemplate.expire(lock, 10, TimeUnit.SECONDS); // 设置过期时间

//        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lock, clientId, 10, TimeUnit.SECONDS);
//
//        if(!result) {
//            return "error";
//        }
        final RLock rLock = redisson.getLock(lock);
        try {
            rLock.lock();
            int stock = Integer.valueOf(stringRedisTemplate.opsForValue().get("stock")); // 获取库存
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("stock", String.valueOf(stock - 1));
                System.out.println("抢购成功， 库存剩余："+ (stock - 1));
            } else {
                System.out.println("库存不足");
//                new ConcurrentHashMap<>()
//                new HashMap<>()
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            rLock.unlock();
//            if (clientId.equals(stringRedisTemplate.opsForValue().get(lock))) {
//                stringRedisTemplate.delete(lock); // 释放锁
//            }
        }
        return "success";
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("192.168.2.129:6397").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
