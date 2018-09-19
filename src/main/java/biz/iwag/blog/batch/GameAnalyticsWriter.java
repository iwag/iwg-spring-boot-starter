package biz.iwag.blog.batch;

import biz.iwag.blog.web.rest.VersionResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class GameAnalyticsWriter implements ItemWriter<UserDeviceInfo> {

    private final Logger log = LoggerFactory.getLogger(VersionResource.class);

    @Override
    public void write(List<? extends UserDeviceInfo> items) throws Exception {
        for (UserDeviceInfo udi : items) {
            log.info("write " + udi.toString());
        }
    }
}
