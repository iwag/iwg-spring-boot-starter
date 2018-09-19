package biz.iwag.blog.config;

import biz.iwag.blog.batch.GameAnalyticsWriter;
import biz.iwag.blog.batch.JobCompletionNotificationListener;
import biz.iwag.blog.batch.UserDeviceInfo;
import biz.iwag.blog.batch.UserDeviceInfoReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public GameAnalyticsWriter writer() {
        return new GameAnalyticsWriter();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(new JobCompletionNotificationListener())
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(ItemWriter<UserDeviceInfo> writer) {
        return stepBuilderFactory.get("step1")
            .<UserDeviceInfo, UserDeviceInfo> chunk(10)
            .reader(new UserDeviceInfoReader())
            .writer(writer)
            .build();
    }
}
