package com.naranjax.springbatchdemo.paymentStep;

import com.naranjax.springbatchdemo.model.Payment;
import com.naranjax.springbatchdemo.repository.PaymentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class PaymentsWriter implements ItemWriter<Payment> {

    @Autowired
    private PaymentRepository paymentRepository;

   /* public PaymentsWriter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
*/
    @Override
    public void write(List<? extends Payment> payments) throws Exception {


        paymentRepository.saveAll(payments);
    }
}



