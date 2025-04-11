package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.exception.ApiException.InternalServerException;
import com.jh.de.pacdetails.model.request.PacInfoRequest;

import java.net.URI;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.util.UriBuilder;

@ContextConfiguration(classes = {PDCOPaymentDelegatorServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PDCOPaymentDelegatorServiceImplDiffblueTest {
    @MockitoBean
    private Builder builder;

    @Autowired
    private PDCOPaymentDelegatorServiceImpl pDCOPaymentDelegatorServiceImpl;

    /**
     * Test {@link PDCOPaymentDelegatorServiceImpl#getPacInfoRecords(PacInfoRequest)}.
     * <p>
     * Method under test: {@link PDCOPaymentDelegatorServiceImpl#getPacInfoRecords(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getPacInfoRecords(PacInfoRequest)")
    @Tag("MaintainedByDiffblue")
    void testGetPacInfoRecords() {
        // Arrange
        RequestHeadersUriSpec requestHeadersUriSpec = mock(RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), Mockito.<Function<UriBuilder, URI>>any()))
                .thenThrow(new InternalServerException("An error occurred"));
        WebClient webClient = mock(WebClient.class);
        Mockito.<RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(builder.build()).thenReturn(webClient);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertThrows(InternalServerException.class, () -> pDCOPaymentDelegatorServiceImpl.getPacInfoRecords(request));
        verify(webClient).get();
        verify(builder).build();
        verify(requestHeadersUriSpec).uri(eq("${pdcoPaymentService.url}"), isA(Function.class));
    }
}
