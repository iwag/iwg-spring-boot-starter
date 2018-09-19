package biz.iwag.blog.batch;

import biz.iwag.blog.config.BatchConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BatchConfiguration.class)
public class GameAnalyticsBatchTests {
	//  https://github.com/spring-projects/spring-batch/blob/master/spring-batch-samples/src/test/java/org/springframework/batch/sample/JsonSupportIntegrationTests.java

    @Test
    public void testJob() throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());

        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}
