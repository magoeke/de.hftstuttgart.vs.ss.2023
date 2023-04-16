package de.hststuttgart.vs.task04.api.v1.models;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

public class InvoiceDO extends RepresentationModel<InvoiceDO> {

    private String invoiceId;
    private String orderId;
    private BigDecimal totalNetAmount;
    private BigDecimal totalTaxAmount;
    private BigDecimal totalGrossAmount;
    private Customer customer;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(final String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalNetAmount() {
        return totalNetAmount;
    }

    public void setTotalNetAmount(final BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(final BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getTotalGrossAmount() {
        return totalGrossAmount;
    }

    public void setTotalGrossAmount(final BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

}
