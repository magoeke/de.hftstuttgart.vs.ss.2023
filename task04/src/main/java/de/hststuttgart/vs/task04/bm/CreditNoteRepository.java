package de.hststuttgart.vs.task04.bm;

import java.util.List;

import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteAlreadyExists;
import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteNotFound;
import de.hststuttgart.vs.task04.bm.exceptions.InvoiceNotFound;
import de.hststuttgart.vs.task04.bm.model.CreditNote;

public interface CreditNoteRepository {

    public CreditNote getCreditNote(final String creditNoteId) throws CreditNoteNotFound;

    public List<CreditNote> getCreditNotes(final String invoiceId) throws InvoiceNotFound;

    public CreditNote createCreditNotes(final String invoiceId, final String reason) throws InvoiceNotFound,
            CreditNoteAlreadyExists;

}
