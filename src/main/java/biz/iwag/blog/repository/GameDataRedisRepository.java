package biz.iwag.blog.repository;


import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class GameDataRedisRepository {

    @Resource(name="redisTemplate0")
    private ValueOperations<String, String> listOps;

    public String get(String key) {
        return listOps.get(key);
    }

}
