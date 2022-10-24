package com.naranjax.springbatchdemo.config;

import com.naranjax.springbatchdemo.model.Payment;
import com.naranjax.springbatchdemo.model.PaymentCsv;
import com.naranjax.springbatchdemo.paymentStep.*;
import com.naranjax.springbatchdemo.repository.PaymentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public PaymentsReader reader(){
        return new PaymentsReader();
    }

    /*
    @Bean
    public PaymentsWriter writer() {
        return new PaymentsWriter();
    }

     */

    @Autowired private PaymentsWriter writer;

    @Bean
    public PaymentsProcessor processor() {
        return new PaymentsProcessor();
    }

    /*
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

     */
/*
    @Autowired
    private PaymentsStepListener listener;
*/
    @Bean
    public Job jobNx(JobBuilderFactory jobBuilderFactory){

        return jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }

    @Bean
    public Step step(){
        return stepBuilderFactory.get("ETL-file-load")
                .<PaymentCsv, Payment>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                //.listener(listener)
                .build();
    }

    /*
    @Bean
    @StepScope
    public FlatFileItemReader<PaymentCsv> paymentFileReader() {
        return new FlatFileItemReaderBuilder<PaymentCsv>()
                .name("customerFileReader")
                .resource(new ClassPathResource("20221024_payments.csv"))
                .linesToSkip(1)
                .delimited()
                .names("documentNumber","paymentDate","interest_amount","capital_amount","loan_id")
                .targetType(PaymentCsv.class)
                .build();
    }


    @Bean
    public ItemWriter<? super Payment> paymentItemWriter() {
        return new ItemWriter<Payment>() {
            @Override
            public void write(List<? extends Payment> items) throws Exception {
                paymentRepository.saveAll(items);
            }
        };
    }

     */
}
