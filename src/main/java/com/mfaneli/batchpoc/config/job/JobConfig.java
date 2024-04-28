package com.mfaneli.batchpoc.config.job;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@AllArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importUserJob(
            Step readKafkaStep,
            Step generateFileStep,
            Step deleteStageStep
    ) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(readKafkaStep)
                .next(generateFileStep)
                .next(deleteStageStep)
                .end()
                .build();
    }
}
