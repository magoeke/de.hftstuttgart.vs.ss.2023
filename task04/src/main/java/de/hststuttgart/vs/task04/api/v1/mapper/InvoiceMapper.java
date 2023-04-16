package de.hststuttgart.vs.task04.api.v1.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import de.hststuttgart.vs.task04.api.v1.InvoiceAPI;
import de.hststuttgart.vs.task04.api.v1.models.InvoiceDO;
import de.hststuttgart.vs.task04.bm.model.Invoice;

public class InvoiceMapper {

    public static InvoiceDO map(final Invoice invoice) {
        final var invoiceDO = new InvoiceDO();

        invoiceDO.setInvoiceId(invoice.getInvoiceId());
        invoiceDO.setOrderId(invoice.getOrderId());
        invoiceDO.setTotalNetAmount(invoice.getTotalNetAmount());
        invoiceDO.setTotalTaxAmount(invoice.getTotalTaxAmount());
        invoiceDO.setTotalGrossAmount(invoice.getTotalGrossAmount());
        invoiceDO.setCustomer(invoice.getCustomer());

        invoiceDO
                .add(WebMvcLinkBuilder
                        .linkTo(methodOn(InvoiceAPI.class).getInvoice(invoice.getInvoiceId()))
                        .withSelfRel())
                .addIf(
                        invoice.isCreditNotePossible(),
                        () -> linkTo(methodOn(InvoiceAPI.class).createCreditNote(
                                invoice.getInvoiceId(),
                                null)).withRel("create:full-credit-note"))
                .addIf(
                        !invoice.getCreditNotes().isEmpty(),
                        () -> linkTo(methodOn(InvoiceAPI.class).getCreditNotes(invoice.getInvoiceId()))
                                .withRel("show:credit-notes"));

        return invoiceDO;
    }

}
