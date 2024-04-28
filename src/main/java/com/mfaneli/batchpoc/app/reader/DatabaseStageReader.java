package com.mfaneli.batchpoc.app.reader;

import com.mfaneli.batchpoc.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DatabaseStageReader {

    public final DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<User> readerUser() {
        return new JdbcCursorItemReader<>(){{
            setDataSource(dataSource);
            setSql("SELECT id, name, email, phone FROM public.users");
            setRowMapper((resultSet, i) -> {
                var user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                return user;
            });
        }};
    }

}
