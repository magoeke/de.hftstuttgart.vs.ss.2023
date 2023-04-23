package de.hststuttgart.vs.task04.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hststuttgart.vs.task04.api.v1.mapper.CreditNoteMapper;
import de.hststuttgart.vs.task04.api.v1.mapper.InvoiceMapper;
import de.hststuttgart.vs.task04.api.v1.models.CreditNoteDO;
import de.hststuttgart.vs.task04.api.v1.models.FullCreditNoteRequest;
import de.hststuttgart.vs.task04.api.v1.models.InvoiceDO;
import de.hststuttgart.vs.task04.api.v1.models.Invoices;
import de.hststuttgart.vs.task04.bm.InvoiceController;
import de.hststuttgart.vs.task04.bm.exceptions.CreditNoteAlreadyExists;
import de.hststuttgart.vs.task04.bm.exceptions.InvalidOffset;
import de.hststuttgart.vs.task04.bm.exceptions.InvoiceNotFound;
import de.hststuttgart.vs.task04.bm.model.PagedInvoices;

@RestController
@RequestMapping("/invoices")
public class InvoiceAPI {

    private final InvoiceController invoiceController;

    public InvoiceAPI(final InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @GetMapping
    public ResponseEntity<Invoices> getInvoices(
            @RequestParam(defaultValue = "1") final int offset,
            @RequestParam(defaultValue = "5") final int limit
    ) {
        try {
            final PagedInvoices pagedInvoices = invoiceController.getInvoices(offset, limit);

            final var mappedInvoices =
                    pagedInvoices.getInvoices().stream().map(InvoiceMapper::map).collect(Collectors.toList());

            final var invoices = new Invoices();
            invoices.setInvoices(mappedInvoices);

            invoices
                    .add(linkTo(methodOn(InvoiceAPI.class).getInvoices(offset, limit)).withSelfRel())
                    .addIf(pagedInvoices.hasNext(), () -> linkTo(methodOn(InvoiceAPI.class).getInvoices(offset + 1, limit)).withRel("next"))
                    .addIf(pagedInvoices.hasPrevious(), () -> linkTo(methodOn(InvoiceAPI.class).getInvoices(offset - 1, limit)).withRel("previous"));

            return ResponseEntity.ok(invoices);
        } catch (final InvalidOffset e) {
            System.out.println("Offset was invalid");
            return ResponseEntity.notFound().build();
        }


    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<InvoiceDO> getInvoice(@PathVariable("invoiceId") final String invoiceId) {
        try {
            final var invoice = invoiceController.getInvoice(invoiceId);
            final var mappedInvoice = InvoiceMapper.map(invoice);
            return ResponseEntity.ok(mappedInvoice);
        } catch (final InvoiceNotFound e) {
            System.out.println("Invoice " + invoiceId + " not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{invoiceId}/full-credit-notes")
    public ResponseEntity<CreditNoteDO> createCreditNote(
            @PathVariable("invoiceId") final String invoiceId,
            @RequestBody final FullCreditNoteRequest fullCreditNoteRequest) {
        try {
            final var creditNote = invoiceController.createCreditNotes(invoiceId, fullCreditNoteRequest.getReason());
            final var mappedCreditNote = CreditNoteMapper.map(creditNote);
            return ResponseEntity.ok(mappedCreditNote);
        } catch (final InvoiceNotFound e) {
            System.out.println("Invoice " + invoiceId + " not found");
            return ResponseEntity.notFound().build();
        } catch (final CreditNoteAlreadyExists e) {
            System.out.println("Credit Note already exists");
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/{invoiceId}/credit-notes")
    public ResponseEntity<List<CreditNoteDO>> getCreditNotes(@PathVariable("invoiceId") final String invoiceId) {
        try {
            final var creditNotes = invoiceController.getCreditNotes(invoiceId);
            final var mappedCreditNotes = creditNotes.stream().map(CreditNoteMapper::map).toList();
            return ResponseEntity.ok(mappedCreditNotes);
        } catch (final InvoiceNotFound e) {
            System.out.println("Invoice " + invoiceId + " not found");
            return ResponseEntity.notFound().build();
        }
    }

}
