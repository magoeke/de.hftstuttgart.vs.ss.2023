package de.hststuttgart.vs.task04.bm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.hststuttgart.vs.task04.api.v1.models.Customer;
import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteAlreadyExists;
import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteNotFound;
import de.hststuttgart.vs.task04.bm.exceptions.InvalidOffset;
import de.hststuttgart.vs.task04.bm.exceptions.InvoiceNotFound;
import de.hststuttgart.vs.task04.bm.model.CreditNote;
import de.hststuttgart.vs.task04.bm.model.Invoice;
import de.hststuttgart.vs.task04.bm.model.PagedInvoices;

@Service
public class InvoiceController implements InvoiceRepository, CreditNoteRepository {

    private final List<Invoice> invoices;
    private final List<CreditNote> creditNotes;

    public InvoiceController() {
        // fake some invoices
        final var johnDoe = new Customer().firstname("John").lastname("Doe");

        final var invoice = new Invoice()
                .invoiceId("2023-04-16-0001")
                .orderId("c082bcbf-550a-4b10-baeb-4047957f70a2")
                .totalNetAmount(BigDecimal.valueOf(9.34))
                .totalTaxAmount(BigDecimal.valueOf(0.65))
                .totalGrossAmount(BigDecimal.valueOf(9.99))
                .customer(johnDoe)
                .creditNotePossible(true)
                .creditNotes(new ArrayList<>());

        invoices = new ArrayList<>();
        invoices.add(invoice);

        creditNotes = new ArrayList<>();
    }

    @Override
    public PagedInvoices getInvoices(final int offset, final int limit) throws InvalidOffset {
        if (offset < 1 || ((offset - 1) * limit) > invoices.size()) {
            throw new InvalidOffset();
        }

        final var invoicePage = invoices
                .stream()
                .skip((offset - 1) * limit)
                .limit(limit)
                .collect(Collectors.toList());

        final var hasNext = invoices.size() > (offset * limit);
        final var hasPrevious = offset > 1;

        final var pagedInvoices = new PagedInvoices();
        pagedInvoices.setPrevious(hasPrevious);
        pagedInvoices.setNext(hasNext);
        pagedInvoices.setInvoices(invoicePage);

        return pagedInvoices;
    }

    @Override
    public Invoice getInvoice(final String invoiceId) throws InvoiceNotFound {
        return invoices
                .stream()
                .filter(inv -> inv.getInvoiceId().equals(invoiceId))
                .findFirst()
                .orElseThrow(InvoiceNotFound::new);
    }

    @Override
    public CreditNote createCreditNotes(final String invoiceId, final String reason)
            throws InvoiceNotFound, CreditNoteAlreadyExists {
        final var invoice = getInvoice(invoiceId);

        if (!invoice.isCreditNotePossible()) {
            throw new CreditNoteAlreadyExists();
        }

        final var creditNote = new CreditNote();

        creditNote.setCreditNoteId("CREDITNOTE-" + invoiceId);
        creditNote.setInvoiceId(invoiceId);
        creditNote.setOrderId(invoice.getOrderId());
        creditNote.setTotalNetAmount(invoice.getTotalNetAmount());
        creditNote.setTotalTaxAmount(invoice.getTotalTaxAmount());
        creditNote.setTotalGrossAmount(invoice.getTotalGrossAmount());
        creditNote.setCustomer(invoice.getCustomer());
        creditNote.setReason(reason);

        creditNotes.add(creditNote);
        invoice.setCreditNotePossible(false);
        invoice.setCreditNotes(List.of(creditNote));

        return creditNote;
    }

    @Override
    public List<CreditNote> getCreditNotes(final String invoiceId) throws InvoiceNotFound {
        final var invoice = getInvoice(invoiceId);
        return invoice.getCreditNotes();
    }

    @Override
    public CreditNote getCreditNote(final String creditNoteId) throws CreditNoteNotFound {
        return creditNotes
                .stream()
                .filter(creditNote -> creditNote.getCreditNoteId().equals(creditNoteId))
                .findFirst()
                .orElseThrow(CreditNoteNotFound::new);
    }

}
