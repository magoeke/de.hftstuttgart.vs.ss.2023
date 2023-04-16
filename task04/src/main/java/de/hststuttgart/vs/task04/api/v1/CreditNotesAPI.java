package de.hststuttgart.vs.task04.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hststuttgart.vs.task04.api.v1.mapper.CreditNoteMapper;
import de.hststuttgart.vs.task04.bm.InvoiceController;
import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteNotFound;

@RestController
@RequestMapping("/credit-notes")
public class CreditNotesAPI {

    private final InvoiceController invoiceController;

    public CreditNotesAPI(final InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @GetMapping("/{creditNoteId}")
    public ResponseEntity getCreditNote(@PathVariable final String creditNoteId) {
        try {
            final var creditNote = invoiceController.getCreditNote(creditNoteId);
            final var mappedCreditNote = CreditNoteMapper.map(creditNote);
            return ResponseEntity.ok(mappedCreditNote);
        } catch (CreditNoteNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

}
