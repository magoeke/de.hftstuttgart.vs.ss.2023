package de.hststuttgart.vs.task04.bm.model;

import java.util.List;

public class PagedInvoices {

    private boolean previous;
    private boolean next;

    private List<Invoice> invoices;

    public boolean hasPrevious() {
        return previous;
    }

    public void setPrevious(final boolean previous) {
        this.previous = previous;
    }

    public boolean hasNext() {
        return next;
    }

    public void setNext(final boolean next) {
        this.next = next;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(final List<Invoice> invoices) {
        this.invoices = invoices;
    }

}
