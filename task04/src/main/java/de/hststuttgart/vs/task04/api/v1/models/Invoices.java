package de.hststuttgart.vs.task04.api.v1.models;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class Invoices extends RepresentationModel<Invoices> {

    private List<InvoiceDO> invoices;

    public List<InvoiceDO> getInvoices() {
        return invoices;
    }

    public void setInvoices(final List<InvoiceDO> invoices) {
        this.invoices = invoices;
    }

}
