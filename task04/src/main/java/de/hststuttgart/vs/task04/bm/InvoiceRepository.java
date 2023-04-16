package de.hststuttgart.vs.task04.bm;

import de.hststuttgart.vs.task04.bm.exceptions.InvalidOffset;
import de.hststuttgart.vs.task04.bm.exceptions.InvoiceNotFound;
import de.hststuttgart.vs.task04.bm.model.Invoice;
import de.hststuttgart.vs.task04.bm.model.PagedInvoices;

public interface InvoiceRepository {

    public PagedInvoices getInvoices(final int offset, final int limit) throws InvalidOffset;

    public Invoice getInvoice(final String invoiceId) throws InvoiceNotFound;


}
