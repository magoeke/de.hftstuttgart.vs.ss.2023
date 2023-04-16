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

        // TODO 03: Add self rel and one rel to either create a credit note or return a list of credit notes

        return invoiceDO;
    }

}
