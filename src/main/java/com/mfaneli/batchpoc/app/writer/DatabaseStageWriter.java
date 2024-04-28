package com.mfaneli.batchpoc.app.writer;

import com.mfaneli.batchpoc.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DatabaseStageWriter {

    public final DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<User> writerDatabaseStage() {
        String sql = """
        INSERT INTO users (id, name, email, phone)
        VALUES (:id, :name, :email, :phone)
        on conflict (id) do update
        set name = COALESCE(excluded.name, users.name), email = COALESCE(excluded.email, users.email), phone = COALESCE(excluded.phone, users.phone)
        """;
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }
}
