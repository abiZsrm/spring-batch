package com.customerBatch;

import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Properties;

@EnableBatchProcessing
@SpringBootApplication
@ComponentScan(basePackages = { "com.customerBatch.config", "com.customerBatch.validator"})
public class CustomerBatch implements CommandLineRunner {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CustomerBatch.class);
        Properties properties = new Properties();
        properties.put("spring.batch.job.enabled", false);
        application.setDefaultProperties(properties);

        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        String filePath = "inputs/customer_update_13.csv";

        URI sampleUri = this.getClass().getClassLoader().getResource(filePath).toURI();
        String stringPath = Paths.get(sampleUri).toString();

        JobParameters jobParameters = new JobParametersBuilder().addString("customerUpdateFile", stringPath).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);
    }
}
