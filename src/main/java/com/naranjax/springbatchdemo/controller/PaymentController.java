package com.naranjax.springbatchdemo.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.batch.runtime.BatchStatus;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class PaymentController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job jobNx;

    @GetMapping("/payment")
    public BatchStatus payment() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {


        Map<String, JobParameter> params = new HashMap<>();

        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(params);

        JobExecution jobExecution = jobLauncher.run(jobNx, parameters);

        return jobExecution.getStatus().getBatchStatus();


    }


    @GetMapping("/test")
    public String getMappingTest()  {


        return "TEST";


    }


}
