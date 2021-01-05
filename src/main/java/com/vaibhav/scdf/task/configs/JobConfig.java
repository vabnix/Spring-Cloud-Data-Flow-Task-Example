package com.vaibhav.scdf.task.configs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JobConfig {
    private static Log logger = LogFactory.getLog(JobConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(stepBuilderFactory.get("jobStep1")
                        .tasklet(new Tasklet() {

                            @Override
                            public RepeatStatus execute(StepContribution contribution,
                                                        ChunkContext chunkContext) throws Exception {

                                logger.info("Job was run successfully");
                                return RepeatStatus.FINISHED;
                            }
                        }).build()).build();
    }
}
