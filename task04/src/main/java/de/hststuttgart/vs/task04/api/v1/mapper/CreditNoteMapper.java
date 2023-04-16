package de.hststuttgart.vs.task04.api.v1.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import de.hststuttgart.vs.task04.api.v1.CreditNotesAPI;
import de.hststuttgart.vs.task04.api.v1.InvoiceAPI;
import de.hststuttgart.vs.task04.api.v1.models.CreditNoteDO;
import de.hststuttgart.vs.task04.bm.model.CreditNote;


public class CreditNoteMapper {

    public static CreditNoteDO map(final CreditNote creditNote) {
        final var creditNoteDO = new CreditNoteDO();

        creditNoteDO.setCreditNoteId(creditNote.getCreditNoteId());
        creditNoteDO.setInvoiceId(creditNote.getInvoiceId());
        creditNoteDO.setOrderId(creditNote.getOrderId());
        creditNoteDO.setTotalNetAmount(creditNote.getTotalNetAmount());
        creditNoteDO.setTotalTaxAmount(creditNote.getTotalTaxAmount());
        creditNoteDO.setTotalGrossAmount(creditNote.getTotalGrossAmount());
        creditNoteDO.setCustomer(creditNote.getCustomer());
        creditNoteDO.setReason(creditNote.getReason());

        // TODO 02: Add a self rel and one that links to the original invoice


        return creditNoteDO;
    }

}
