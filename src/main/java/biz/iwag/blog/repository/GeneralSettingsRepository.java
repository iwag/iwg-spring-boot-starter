package biz.iwag.blog.repository;

import biz.iwag.blog.config.Constants;
import biz.iwag.blog.domain.GeneralSettings;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


/**
 * Spring Data  repository for the GeneralSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public class GeneralSettingsRepository {
    // TODO SERVECE!!
    private static Map<String, Object> settings;
    static {
        settings = new HashMap<>();
        settings.put(Constants.LAST_API_VERSION, 34);
    }

    public String get(String key) {

        return settings.get(key).toString();
    }
}
