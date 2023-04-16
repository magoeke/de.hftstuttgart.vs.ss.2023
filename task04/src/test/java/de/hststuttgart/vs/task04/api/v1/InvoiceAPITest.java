package de.hststuttgart.vs.task04.api.v1;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import de.hststuttgart.vs.task04.api.v1.models.Customer;
import de.hststuttgart.vs.task04.bm.InvoiceController;
import de.hststuttgart.vs.task04.bm.model.Invoice;
import de.hststuttgart.vs.task04.bm.model.PagedInvoices;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(InvoiceAPI.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InvoiceAPITest {

    private MockMvc mockMvc;

    @MockBean
    private InvoiceController invoiceController;

    private RestDocumentationResultHandler documentationHandler;

    @BeforeEach
    void setUp(final WebApplicationContext webApplicationContext,
            final RestDocumentationContextProvider restDocumentationContextProvider) {
        documentationHandler =
                document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentationContextProvider).uris()
                        .withScheme("https")
                        .withPort(443))
                .alwaysDo(documentationHandler)
                .build();
    }

    @Test
    void should_return_invoices() throws Exception {
        // We mock the Object so that we can test it easier
        final var pagedInvoices = new PagedInvoices();
        pagedInvoices.setInvoices(List.of());
        pagedInvoices.setNext(false);
        pagedInvoices.setPrevious(false);

        Mockito.doReturn(pagedInvoices).when(invoiceController).getInvoices(Mockito.anyInt(), Mockito.anyInt());

        this.mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(documentationHandler.document(
                        links(
                                halLinks(),
                                linkWithRel("self").description("Link to this resource")
                        ),
                        responseFields(
                                fieldWithPath("invoices").description("Array that contains all invoices"),
                                subsectionWithPath("_links").description("Link to resources")
                        )
                ));
    }

    // TODO 04: test /invoices which should return a list with a next and previous link
    @Test
    void should_return_invoices_with_links() throws Exception {
        // We mock the Object so that we can test it easier
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

        final var pagedInvoices = new PagedInvoices();
        pagedInvoices.setInvoices(List.of(invoice));
        pagedInvoices.setNext(true);
        pagedInvoices.setPrevious(true);

        Mockito.doReturn(pagedInvoices).when(invoiceController).getInvoices(Mockito.anyInt(), Mockito.anyInt());

        this.mockMvc.perform(get("/invoices")
                        .queryParam("offset", "2")
                        .queryParam("limit", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(documentationHandler.document(
                        links(
                                halLinks(),
                                linkWithRel("self").description("Link to this resource"),
                                linkWithRel("next").description("Link to next page"),
                                linkWithRel("previous").description("Link to previous page")
                        ),
                        queryParameters(
                                parameterWithName("offset").description("Indicates the page. Starts with 1").optional(),
                                parameterWithName("limit").description("Limits the amount of invoices per page").optional()
                        ),
                        responseFields(
                                fieldWithPath("invoices").description("Array that contains all invoices"),
                                subsectionWithPath("_links").description("Link to resources")
                        )
                ));
    }

    // TODO 05 add a test for /invoices/[invoiceId}
    // The endpoint should return an invoice that can be refunded (credit note creation is possible)

    // TODO 06 add a test for /invoices/[invoiceId}
    // The endpoint should return an invoice that can't be refunded (credit note already exists)

    // TODO 07 add a test for /invoices/[invoiceId}
    // The endpoint should return no invoce (not found)


}
