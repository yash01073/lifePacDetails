package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.PYDDetails;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PaymentDetails;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

@ContextConfiguration(classes = {PDCOPaymentDelegatorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PDCOPaymentDelegatorServiceImplTest {

    @Mock
    private WebClient.Builder builderMock;
    @Autowired
    private PDCOPaymentDelegatorServiceImpl pDCOPaymentDelegatorServiceImpl;

    @Value("${pdcoPaymentService.url}")
    private String pdcoPaymentServiceUrl;
    /**
     * Method under test:
     * {@link PDCOPaymentDelegatorServiceImpl#getPacInfoRecords(PacInfoRequest)}
     */
    //@Test
    void testGetPacInfoRecords() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualPacInfoRecords = pDCOPaymentDelegatorServiceImpl.getPacInfoRecords(request);

        // Assert
        assertEquals(1, actualPacInfoRecords.size());
        PacInfoResult getResult = actualPacInfoRecords.get(0);
        BankDetails bankDetails = getResult.getBankDetails();
        //assertEquals("", bankDetails.getBankAccountType());
        //assertEquals("", bankDetails.getNameOnAccount());
        PaymentDetails paymentDetails = getResult.getPaymentDetails();
        assertEquals("0.00", paymentDetails.getLoanAmount());
        assertEquals("003", getResult.getCode());
        //assertEquals("0143", bankDetails.getBankAccountNumber());
        assertEquals("10000012271719602254", getResult.getUUID());
        assertEquals("106", getResult.getPLC());
        PYDDetails pydDetails = getResult.getPydDetails();
        assertEquals("2024-09-26", pydDetails.getPydDate());
        assertEquals("117.00", paymentDetails.getPremiumAmount());
        assertEquals("2V", getResult.getAdminSystemId());
        assertEquals("117.00", paymentDetails.getPaymentAmount());
        //assertEquals("648949589", bankDetails.getBankRoutingNumber());
        assertEquals("81864653", getResult.getPolicyNumber());
        assertEquals("Day 1 - NBD has PAC but no Bank info", getResult.getMessage());
        assertEquals("N", pydDetails.getBackdating());
        assertEquals("Payor", getResult.getRole());
        assertEquals("Quarterly", paymentDetails.getModeOfFrequency());
        assertEquals("TP", getResult.getSourceSystem());
        //assertEquals("Watertown", bankDetails.getBankName());
        //assertEquals("Watertown, account ending in 0143", bankDetails.getDisplay());
        assertNull(getResult.getId());
        assertNull(getResult.getApplicationId());
        assertNull(getResult.getError());
        assertNull(getResult.getErrorDetails());
        assertNull(getResult.getTokenId());
        assertNull(getResult.getTransactionId());
        assertNull(getResult.getWorkItemId());
        assertNull(paymentDetails.getImmediateLoanDraft());
        assertNull(paymentDetails.getImmediatePremiumDraft());
        //assertNull(paymentDetails.getPremiumDueDate());
        assertNull(getResult.getCreatedTime());
        assertEquals(26, paymentDetails.getDraftDueDate());
    }

    /**
     * Method under test:
     * {@link PDCOPaymentDelegatorServiceImpl#getPacInfoRecords(PacInfoRequest)}
     */
    //@Test
    void testGetPacInfoRecords2() {
        // Arrange
        PacInfoRequest request = mock(PacInfoRequest.class);
        doNothing().when(request).setAdminSystem(Mockito.<String>any());
        doNothing().when(request).setBusiness(Mockito.<String>any());
        doNothing().when(request).setCallType(Mockito.<String>any());
        doNothing().when(request).setPlc(Mockito.<String>any());
        doNothing().when(request).setPolicyNumber(Mockito.<String>any());
        doNothing().when(request).setTransactionId(Mockito.<String>any());
        doNothing().when(request).setUUID(Mockito.<String>any());
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualPacInfoRecords = pDCOPaymentDelegatorServiceImpl.getPacInfoRecords(request);

        // Assert
        verify(request).setAdminSystem(eq("Admin System"));
        verify(request).setBusiness(eq("Business"));
        verify(request).setCallType(eq("Call Type"));
        verify(request).setPlc(eq("Plc"));
        verify(request).setPolicyNumber(eq("42"));
        verify(request).setTransactionId(eq("42"));
        verify(request).setUUID(eq("01234567-89AB-CDEF-FEDC-BA9876543210"));
        assertEquals(1, actualPacInfoRecords.size());
        PacInfoResult getResult = actualPacInfoRecords.get(0);
        BankDetails bankDetails = getResult.getBankDetails();
        //assertEquals("", bankDetails.getBankAccountType());
        //assertEquals("", bankDetails.getNameOnAccount());
        PaymentDetails paymentDetails = getResult.getPaymentDetails();
        assertEquals("0.00", paymentDetails.getLoanAmount());
        assertEquals("003", getResult.getCode());
        //assertEquals("0143", bankDetails.getBankAccountNumber());
        assertEquals("10000012271719602254", getResult.getUUID());
        assertEquals("106", getResult.getPLC());
        PYDDetails pydDetails = getResult.getPydDetails();
        assertEquals("2024-09-26", pydDetails.getPydDate());
        assertEquals("117.00", paymentDetails.getPremiumAmount());
        assertEquals("2V", getResult.getAdminSystemId());
        assertEquals("117.00", paymentDetails.getPaymentAmount());
        //assertEquals("648949589", bankDetails.getBankRoutingNumber());
        assertEquals("81864653", getResult.getPolicyNumber());
        assertEquals("Day 1 - NBD has PAC but no Bank info", getResult.getMessage());
        assertEquals("N", pydDetails.getBackdating());
        assertEquals("Payor", getResult.getRole());
        assertEquals("Quarterly", paymentDetails.getModeOfFrequency());
        assertEquals("TP", getResult.getSourceSystem());
        //assertEquals("Watertown", bankDetails.getBankName());
        //assertEquals("Watertown, account ending in 0143", bankDetails.getDisplay());
        assertNull(getResult.getId());
        assertNull(getResult.getApplicationId());
        assertNull(getResult.getError());
        assertNull(getResult.getErrorDetails());
        assertNull(getResult.getTokenId());
        assertNull(getResult.getTransactionId());
        assertNull(getResult.getWorkItemId());
        assertNull(paymentDetails.getImmediateLoanDraft());
        assertNull(paymentDetails.getImmediatePremiumDraft());
        //assertNull(paymentDetails.getPremiumDueDate());
        assertNull(getResult.getCreatedTime());
        assertEquals(26, paymentDetails.getDraftDueDate());
    }
}
