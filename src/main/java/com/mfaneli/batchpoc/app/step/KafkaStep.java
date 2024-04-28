package com.mfaneli.batchpoc.app.step;

import com.mfaneli.batchpoc.app.reader.dto.LineContact;
import com.mfaneli.batchpoc.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaStep {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step readKafkaStep(
            FlatFileItemReader<LineContact> readerKafka,
            ItemProcessor<LineContact, User> userProcessor,
            JdbcBatchItemWriter<User> writer
    ) {
        return stepBuilderFactory.get("read-kafka-step")
                .<LineContact, User>chunk(10)
                .reader(readerKafka)
                .processor(userProcessor)
                .writer(writer)
                .build();
    }
}
