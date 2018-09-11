package biz.iwag.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:ApplicationContext.xml"})
public class XmlConfiguration {
}
