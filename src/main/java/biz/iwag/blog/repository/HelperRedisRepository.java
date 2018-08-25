package biz.iwag.blog.repository;


import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class HelperRedisRepository {

    public String get(String key) {
        return "";
    }

    public List<String> getList(String key) {return Arrays.asList("");}

    public Map<String,String> getMap(String s) {
        return new HashMap<>();
    }
}
