package com.naranjax.springbatchdemo.paymentStep;

import com.naranjax.springbatchdemo.model.Payment;
import com.naranjax.springbatchdemo.model.PaymentCsv;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class PaymentsProcessor implements ItemProcessor<PaymentCsv,Payment> {

    @Override
    public Payment process(PaymentCsv paymentCsv) throws Exception {
        Payment payment = new Payment();

        payment.setDocumentNumber(paymentCsv.getDocumentNumber());
        payment.setLoanId(paymentCsv.getLoanId());
        payment.setInterestAmount(Double.parseDouble(paymentCsv.getInterestAmount()));
        payment.setCapitalAmount(Double.parseDouble(paymentCsv.getCapitalAmount()));
        // calculo el monto total
        payment.setTotalAmount(Double.parseDouble(paymentCsv.getCapitalAmount()) + Double.parseDouble(paymentCsv.getInterestAmount()));
        payment.setPaymentDate(Date.valueOf(paymentCsv.getPaymentDate()));

        return payment;
    }
}
