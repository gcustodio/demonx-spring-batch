package com.naranjax.springbatchdemo.paymentStep;

import com.naranjax.springbatchdemo.model.PaymentCsv;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

public class PaymentsReader implements ItemReader<PaymentCsv> {

    @Override
    public PaymentCsv read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        FlatFileItemReader<PaymentCsv> flatFileItemReader = new FlatFileItemReader<>();
//        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/20221024_payments.csv"));
        flatFileItemReader.setResource(new ClassPathResource("src/main/resources/20221024_payments.csv"));
        flatFileItemReader.setName("CSV-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader.read();
    }


    @Bean
    public LineMapper<PaymentCsv> lineMapper() {   // 19:10

        DefaultLineMapper<PaymentCsv> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("documentNumber","paymentDate","interest_amount","capital_amount","loan_id");

        BeanWrapperFieldSetMapper<PaymentCsv> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(PaymentCsv.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }
}
