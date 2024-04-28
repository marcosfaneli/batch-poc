package com.mfaneli.batchpoc.app.writer;

import com.mfaneli.batchpoc.domain.User;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class CsvWriter {

    @Bean
    public FlatFileItemWriter<User> writerFile() {
        return new FlatFileItemWriterBuilder<User>()
                .name("userCsvWriter")
                .resource(new FileSystemResource("usuarios_teste.csv"))
                .lineAggregator(lineAggregator())
                .build();
    }

    private LineAggregator<User> lineAggregator() {
        return new DelimitedLineAggregator<>() {{
            setDelimiter(",");
            setFieldExtractor(fieldExtractor());
        }};
    }

    private BeanWrapperFieldExtractor<User> fieldExtractor() {
        return new BeanWrapperFieldExtractor<>() {{
            setNames(new String[]{"id", "name", "phone", "email"});
        }};
    }
}
