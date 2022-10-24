package com.naranjax.springbatchdemo.model;

import java.util.Date;

public class PaymentCsv {

    private String documentNumber;
    private long loanId;
    private String interestAmount;
    private String capitalAmount;
    private String paymentDate;

    public PaymentCsv() {}

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public String getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(String interestAmount) {
        this.interestAmount = interestAmount;
    }

    public String getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(String capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
