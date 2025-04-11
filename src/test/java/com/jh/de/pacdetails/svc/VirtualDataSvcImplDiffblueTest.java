package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.PYDDetails;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PaymentDetails;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;

@ExtendWith(MockitoExtension.class)
class VirtualDataSvcImplDiffblueTest {
    @Mock
    private Map<String, TPolicyPacInfoDividend> map;

    @Mock
    private Map<String, PacInfoResult> map2;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Resource resource;

    @InjectMocks
    private VirtualDataSvcImpl virtualDataSvcImpl;

    /**
     * Test {@link VirtualDataSvcImpl#getPacInfoRecords(PacInfoRequest)}.
     * <p>
     * Method under test: {@link VirtualDataSvcImpl#getPacInfoRecords(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getPacInfoRecords(PacInfoRequest)")
    @Tag("MaintainedByDiffblue")
    void testGetPacInfoRecords() {
        // Arrange
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBankAccountNumber("42");
        bankDetails.setBankAccountType("3");
        bankDetails.setBankName("Bank Name");
        bankDetails.setBankRoutingNumber("42");
        bankDetails.setDisplay("Display");
        bankDetails.setNameOnAccount("3");

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setDraftDueDate(1);
        paymentDetails.setImmediateLoanDraft("Immediate Loan Draft");
        paymentDetails.setImmediatePremiumDraft("Immediate Premium Draft");
        paymentDetails.setLoanAmount("10");
        paymentDetails.setModeOfFrequency("Mode Of Frequency");
        paymentDetails.setPaymentAmount("10");
        paymentDetails.setPremiumAmount("10");
        paymentDetails.setPremiumDueDate("2020-03-01");

        PYDDetails pydDetails = new PYDDetails();
        pydDetails.setBackdating("Backdating");
        pydDetails.setPydDate("2020-03-01");

        PacInfoResult pacInfoResult = new PacInfoResult();
        pacInfoResult.setAdminSystemId("42");
        pacInfoResult.setApplicationId("42");
        pacInfoResult.setBankDetails(bankDetails);
        pacInfoResult.setCode("Code");
        pacInfoResult.setCreatedTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        pacInfoResult.setError("An error occurred");
        pacInfoResult.setErrorDetails("An error occurred");
        pacInfoResult.setId(1L);
        pacInfoResult.setMessage("Not all who wander are lost");
        pacInfoResult.setPLC("PLC");
        pacInfoResult.setPaymentDetails(paymentDetails);
        pacInfoResult.setPolicyNumber("42");
        pacInfoResult.setPydDetails(pydDetails);
        pacInfoResult.setRole("Role");
        pacInfoResult.setSourceSystem("Source System");
        pacInfoResult.setTokenId("42");
        pacInfoResult.setTransactionId("42");
        pacInfoResult.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        pacInfoResult.setWorkItemId("42");
        when(map2.get(Mockito.<Object>any())).thenReturn(pacInfoResult);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualPacInfoRecords = virtualDataSvcImpl.getPacInfoRecords(request);

        // Assert
        verify(map2).get(isA(Object.class));
        assertEquals(1, actualPacInfoRecords.size());
        assertSame(pacInfoResult, actualPacInfoRecords.get(0));
    }

    /**
     * Test {@link VirtualDataSvcImpl#getDividendRecords(DividendRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>Then calls {@link DividendRequest#getDividendAccountType()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link VirtualDataSvcImpl#getDividendRecords(DividendRequest)}
     */
    @Test
    @DisplayName("Test getDividendRecords(DividendRequest); given empty string; then calls getDividendAccountType()")
    @Tag("MaintainedByDiffblue")
    void testGetDividendRecords_givenEmptyString_thenCallsGetDividendAccountType() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("Coverageid");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("Dividend Types");
        tPolicyPacInfoDividend.setPlc("Plc");
        tPolicyPacInfoDividend.setPolicyActiveStatus("Policy Active Status");
        tPolicyPacInfoDividend.setPolicyCategory("Policy Category");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        tPolicyPacInfoDividend
                .setPolicyUpdTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        tPolicyPacInfoDividend.setPrdcoverageid("Prdcoverageid");
        tPolicyPacInfoDividend.setSystemId("42");
        tPolicyPacInfoDividend
                .setUpdateTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(map.get(Mockito.<Object>any())).thenReturn(tPolicyPacInfoDividend);
        DividendRequest request = mock(DividendRequest.class);
        when(request.getDividendAccountType()).thenReturn("");
        when(request.getPlc()).thenReturn("Plc");
        when(request.getPolicyNumber()).thenReturn("42");
        doNothing().when(request).setDividendAccountType(Mockito.<String>any());
        doNothing().when(request).setDividendAccountTypes(Mockito.<List<String>>any());
        doNothing().when(request).setDividendType(Mockito.<String>any());
        doNothing().when(request).setDividendTypes(Mockito.<List<String>>any());
        doNothing().when(request).setPlc(Mockito.<String>any());
        doNothing().when(request).setPolicyNumber(Mockito.<String>any());
        request.setDividendAccountType("3");
        request.setDividendAccountTypes(new ArrayList<>());
        request.setDividendType("Dividend Type");
        request.setDividendTypes(new ArrayList<>());
        request.setPlc("Plc");
        request.setPolicyNumber("42");

        // Act
        List<TPolicyPacInfoDividend> actualDividendRecords = virtualDataSvcImpl.getDividendRecords(request);

        // Assert
        verify(request).getDividendAccountType();
        verify(request).getPlc();
        verify(request).getPolicyNumber();
        verify(request).setDividendAccountType(eq("3"));
        verify(request).setDividendAccountTypes(isA(List.class));
        verify(request).setDividendType(eq("Dividend Type"));
        verify(request).setDividendTypes(isA(List.class));
        verify(request).setPlc(eq("Plc"));
        verify(request).setPolicyNumber(eq("42"));
        verify(map).get(isA(Object.class));
        assertEquals(1, actualDividendRecords.size());
        assertSame(tPolicyPacInfoDividend, actualDividendRecords.get(0));
    }

    /**
     * Test {@link VirtualDataSvcImpl#getDividendRecords(DividendRequest)}.
     * <ul>
     *   <li>When {@link DividendRequest} (default constructor) DividendAccountType is {@code 3}.</li>
     * </ul>
     * <p>
     * Method under test: {@link VirtualDataSvcImpl#getDividendRecords(DividendRequest)}
     */
    @Test
    @DisplayName("Test getDividendRecords(DividendRequest); when DividendRequest (default constructor) DividendAccountType is '3'")
    @Tag("MaintainedByDiffblue")
    void testGetDividendRecords_whenDividendRequestDividendAccountTypeIs3() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("Coverageid");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("Dividend Types");
        tPolicyPacInfoDividend.setPlc("Plc");
        tPolicyPacInfoDividend.setPolicyActiveStatus("Policy Active Status");
        tPolicyPacInfoDividend.setPolicyCategory("Policy Category");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        tPolicyPacInfoDividend
                .setPolicyUpdTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        tPolicyPacInfoDividend.setPrdcoverageid("Prdcoverageid");
        tPolicyPacInfoDividend.setSystemId("42");
        tPolicyPacInfoDividend
                .setUpdateTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(map.get(Mockito.<Object>any())).thenReturn(tPolicyPacInfoDividend);

        DividendRequest request = new DividendRequest();
        request.setDividendAccountType("3");
        request.setDividendAccountTypes(new ArrayList<>());
        request.setDividendType("Dividend Type");
        request.setDividendTypes(new ArrayList<>());
        request.setPlc("Plc");
        request.setPolicyNumber("42");

        // Act
        List<TPolicyPacInfoDividend> actualDividendRecords = virtualDataSvcImpl.getDividendRecords(request);

        // Assert
        verify(map).get(isA(Object.class));
        assertEquals(1, actualDividendRecords.size());
        assertSame(tPolicyPacInfoDividend, actualDividendRecords.get(0));
    }


}
