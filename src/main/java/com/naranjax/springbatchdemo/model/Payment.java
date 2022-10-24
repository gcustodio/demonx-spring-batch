package com.naranjax.springbatchdemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {

    @Id
    private long id;
    private String documentNumber;
    private long loanId;
    private Double interestAmount;
    private Double capitalAmount;
    private Double totalAmount;
    private Date paymentDate;


    public Payment() {
    }

    public Payment(String documentNumber, long loanId, Double interestAmount, Double capitalAmount, Double totalAmount, Date paymentDate) {
        this.documentNumber = documentNumber;
        this.loanId = loanId;
        this.interestAmount = interestAmount;
        this.capitalAmount = capitalAmount;
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Double getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(Double capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
