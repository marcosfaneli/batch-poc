package com.mfaneli.batchpoc;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchPocApplication.class, args);
    }

}
