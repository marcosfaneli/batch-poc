package com.mfaneli.batchpoc.app.reader;

import com.mfaneli.batchpoc.app.reader.dto.LineContact;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class KafkaUserReader {

    @Bean
    public FlatFileItemReader<LineContact> readerKafka() {
        return new FlatFileItemReaderBuilder<LineContact>()
                .name("userItemReader")
                .resource(new FileSystemResource("usuarios.csv"))
                .delimited()
                .names("id", "name", "contact")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private BeanWrapperFieldSetMapper<LineContact> fieldSetMapper() {
        BeanWrapperFieldSetMapper<LineContact> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LineContact.class);
        return fieldSetMapper;
    }
}
