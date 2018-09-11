package biz.iwag.blog.repository;


import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.GeneralSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Repository
public class GameDataRedisRepository {

    @Resource(name="redisTemplate0")
    private ValueOperations<String, String> listOps;

    public String get(String key) {
        return listOps.get(key);
    }

}
