package de.hststuttgart.vs.task04.bm.model;

import java.math.BigDecimal;
import java.util.List;

import de.hststuttgart.vs.task04.api.v1.models.Customer;

public class Invoice {

    private String invoiceId;
    private String orderId;
    private BigDecimal totalNetAmount;
    private BigDecimal totalTaxAmount;
    private BigDecimal totalGrossAmount;
    private Customer customer;
    private boolean isCreditNotePossible;
    private List<CreditNote> creditNotes;

    public List<CreditNote> getCreditNotes() {
        return creditNotes;
    }

    public void setCreditNotes(final List<CreditNote> creditNotes) {
        this.creditNotes = creditNotes;
    }

    public boolean isCreditNotePossible() {
        return isCreditNotePossible;
    }

    public void setCreditNotePossible(final boolean creditNotePossible) {
        isCreditNotePossible = creditNotePossible;
    }

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

    public Invoice invoiceId(final String invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    public Invoice orderId(final String orderId) {
        this.orderId = orderId;
        return this;
    }


    public Invoice totalNetAmount(final BigDecimal totalNetAmount) {
        this.totalNetAmount = totalNetAmount;
        return this;
    }


    public Invoice totalTaxAmount(final BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
        return this;
    }


    public Invoice totalGrossAmount(final BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
        return this;
    }

    public Invoice customer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public Invoice creditNotePossible(final boolean creditNotePossible) {
        this.isCreditNotePossible = creditNotePossible;
        return this;
    }

    public Invoice creditNotes(final List<CreditNote> creditNotes) {
        this.creditNotes = creditNotes;
        return this;
    }
}
