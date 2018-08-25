package biz.iwag.blog.repository;


import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.GeneralSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class GameDataRedisRepository {

    public String get(String key) {
        return "";
    }

}
