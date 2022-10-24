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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private PaymentsReader reader;
    @Autowired
    private PaymentsProcessor processor;

    private PaymentsWriter writer;

    @Autowired
    private PaymentsStepListener listener;

    @Bean
    public Job jobNx(JobBuilderFactory jobBuilderFactory){

        Step step = stepBuilderFactory.get("ETL-file-load")
                .<PaymentCsv, Payment>chunk(5)
                .reader(paymentFileReader())
                .processor(processor)
                .writer(paymentItemWriter())
                //.listener(listener)
                .build();


        Job job = jobBuilderFactory.get("ETL-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

        return job;

    }

    @Bean
    @StepScope
    public FlatFileItemReader<PaymentCsv> paymentFileReader() {
        return new FlatFileItemReaderBuilder<PaymentCsv>()
                .name("customerFileReader")
                .resource(new ClassPathResource("20221024_payments.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[] {"documentNumber","paymentDate","interest_amount","capital_amount","loan_id"})
                .targetType(PaymentCsv.class)
                .build();
    }


    @Bean
    public ItemWriter<Payment> paymentItemWriter() {
        return new ItemWriter<Payment>() {
            @Override
            public void write(List<? extends Payment> items) throws Exception {
                paymentRepository.saveAll(items);
            }
        };
    }
}
