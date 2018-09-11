package biz.iwag.blog.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;


@Repository
public class HelperRedisRepository {
    private static String PREFIX = "db0:";

    @Resource(name="redisTemplate0")
    private ListOperations<String, String> listOps;

    @Resource(name = "redisTemplate0")
    private HashOperations<String, String, String> mapOps;

    @Resource(name = "redisTemplate0")
    private ValueOperations<String, String> valueOps;

    public Optional<String> get(String key) {
        return Optional.ofNullable(valueOps.get(PREFIX + key));
    }

    public List<String> getList(String key) {
        return listOps.range(PREFIX + key, 0, -1);
    }

    public void addValueList(String key, String value) {
        listOps.leftPush(PREFIX + key, value);
    }

    public Map<String, String> getMap(String s) {
        return new HashMap<>();
    }

    public Optional<String> getValue(String key, String s) {
        return Optional.ofNullable(mapOps.get(PREFIX + key, s));
    }
}
