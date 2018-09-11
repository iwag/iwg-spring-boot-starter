package biz.iwag.blog.config;

import io.github.jhipster.config.JHipsterDefaults;
import io.github.jhipster.config.JHipsterProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Blog.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Caches caches = new ApplicationProperties.Caches();

    public static class Caches {

        private String cache_base_url_path = JHipsterDefaults.Swagger.title;

        public String getCache_base_url_path() {
            return cache_base_url_path;
        }

        public void setCache_base_url_path(String cache_base_url_path) {
            this.cache_base_url_path = cache_base_url_path;
        }
    }

    public Caches getCaches() {
        return caches;
    }
}
