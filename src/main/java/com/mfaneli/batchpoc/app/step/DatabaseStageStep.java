package com.mfaneli.batchpoc.app.step;

import com.mfaneli.batchpoc.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseStageStep {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step generateFileStep(
            JdbcCursorItemReader<? extends User> readerUser,
            FlatFileItemWriter<User> writerFile
    ) {
        return stepBuilderFactory.get("generate-file-step")
                .<User, User>chunk(10)
                .reader(readerUser)
                .writer(writerFile)
                .build();
    }
}
