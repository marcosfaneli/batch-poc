package com.mfaneli.batchpoc.app.step;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@AllArgsConstructor
public class DeleteDatabaseStep {

    public final DataSource dataSource;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step deleteStageStep() {
        return stepBuilderFactory.get("delete-users-step")
                .tasklet(((stepContribution, chunkContext) -> {
                    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                    jdbcTemplate.execute("DELETE FROM public.users");
                    return null;
                }))
                .build();
    }
}
