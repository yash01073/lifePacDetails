package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.DividendRecord;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.PaymentDetails;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPasOneResponse;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransformSvcImpl.class})
@ExtendWith(SpringExtension.class)
class TransformSvcImplTest {
    @Autowired
    private TransformSvcImpl transformSvcImpl;


    /*@Test

    void testGetPacInfoResult()


        // Arrange
        TPolicyPacInfo billing = new TPolicyPacInfo();
        billing.setCreatedTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        billing.setMaxLoanAvilable("Max Loan Avilable");
        billing.setModalLoanAmount("10");
        billing.setModalPremAmount("10");
        billing.setOutstandingLoanAmount("10");
        billing.setPacDraftAmt("Pac Draft Amt");
        billing.setPacDraftDate("2020-03-01");
        billing.setPacFrequency("Pac Frequency");
        billing.setPaymentMethod("Payment Method");
        billing.setPlc("Plc");
        billing.setPolicyNumber("42");
        billing.setSystemId("42");
        billing.setUpdatedTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        // Act
        transformSvcImpl.getPacInfoResult(billing, "42");
    }*/


    @Test
    void testGetPacInfoResult2() {
        LifeRecurringPaymentTransaction ptr = new LifeRecurringPaymentTransaction();
        ptr.setAdminSystemId("42");
        ptr.setApplicationId("42");
        ptr.setBankAccountNumber("42");
        ptr.setBankAccountType("3");
        ptr.setBankName("Bank Name");
        ptr.setBankRoutingNumber("42");
        ptr.setCallType("Call Type");
        Date CreatedTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ptr.setCreatedTime(CreatedTime);
        ptr.setDraftDueDate(1);
        ptr.setError("An error occurred");
        ptr.setErrorDetails("An error occurred");
        ptr.setId(1L);
        ptr.setImmediateLoanDraft("Immediate Loan Draft");
        ptr.setImmediatePremiumDraft("Immediate Premium Draft");
        ptr.setLoanAmount("10");
        ptr.setModeOfFrequency("Mode Of Frequency");
        ptr.setNameOnAccount("3");
        ptr.setPLC("PLC");
        ptr.setPaymentAmount("10");
        ptr.setPolicyNumber("42");
        ptr.setPremiumAmount("10");
        ptr.setPremiumDueDate("2020-03-01");
        ptr.setRole("Role");
        ptr.setSourceSystem("Source System");
        ptr.setTokenId("42");
        ptr.setTransactionId("42");
        ptr.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        ptr.setWorkItemId("42");


        PacInfoResult actualPacInfoResult = transformSvcImpl.getPacInfoResult(ptr);

        // Assert
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualPacInfoResult.getUUID());
        PaymentDetails paymentDetails = actualPacInfoResult.getPaymentDetails();
        assertEquals("10", paymentDetails.getLoanAmount());
        assertEquals("10", paymentDetails.getPaymentAmount());
        assertEquals("10", paymentDetails.getPremiumAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createdTime = actualPacInfoResult.getCreatedTime();
        //assertEquals("1970-01-01", simpleDateFormat.format(createdTime));
        assertEquals("2020-03-01", paymentDetails.getPremiumDueDate());
        BankDetails bankDetails = actualPacInfoResult.getBankDetails();
        assertEquals("3", bankDetails.getBankAccountType());
        assertEquals("3", bankDetails.getNameOnAccount());
        assertEquals("42", bankDetails.getBankAccountNumber());
        assertEquals("42", bankDetails.getBankRoutingNumber());
        assertEquals("42", actualPacInfoResult.getAdminSystemId());
        assertEquals("42", actualPacInfoResult.getApplicationId());
        assertEquals("42", actualPacInfoResult.getPolicyNumber());
        assertEquals("42", actualPacInfoResult.getTokenId());
        assertEquals("42", actualPacInfoResult.getTransactionId());
        assertEquals("An error occurred", actualPacInfoResult.getError());
        assertEquals("An error occurred", actualPacInfoResult.getErrorDetails());
        assertEquals("Bank Name", bankDetails.getBankName());
        assertEquals("Bank account ending with 42", bankDetails.getDisplay());
        assertEquals("Immediate Loan Draft", paymentDetails.getImmediateLoanDraft());
        assertEquals("Immediate Premium Draft", paymentDetails.getImmediatePremiumDraft());
        assertEquals("Mode Of Frequency", paymentDetails.getModeOfFrequency());
        assertEquals("PLC", actualPacInfoResult.getPLC());
        assertEquals("Role", actualPacInfoResult.getRole());
        assertEquals("Source System", actualPacInfoResult.getSourceSystem());
        assertNull(actualPacInfoResult.getPydDetails());
        assertNull(actualPacInfoResult.getCode());
        assertNull(actualPacInfoResult.getMessage());
        assertNull(actualPacInfoResult.getWorkItemId());
        assertEquals(1, paymentDetails.getDraftDueDate());
        assertEquals(1L, actualPacInfoResult.getId().longValue());
        assertSame(CreatedTime, createdTime);
    }

    @Test
    void testGetPacInfoResult3() {
        // Arrange
        LifeRecurringPaymentTransaction ptr = mock(LifeRecurringPaymentTransaction.class);
        when(ptr.getDraftDueDate()).thenReturn(1);
        when(ptr.getId()).thenReturn(1L);
        when(ptr.getAdminSystemId()).thenReturn("42");
        when(ptr.getApplicationId()).thenReturn("42");
        when(ptr.getBankAccountNumber()).thenReturn("42");
        when(ptr.getBankAccountType()).thenReturn("3");
        when(ptr.getBankName()).thenReturn("Bank Name");
        when(ptr.getBankRoutingNumber()).thenReturn("42");
        when(ptr.getError()).thenReturn("An error occurred");
        when(ptr.getErrorDetails()).thenReturn("An error occurred");
        when(ptr.getImmediateLoanDraft()).thenReturn("Immediate Loan Draft");
        when(ptr.getImmediatePremiumDraft()).thenReturn("Immediate Premium Draft");
        when(ptr.getLoanAmount()).thenReturn("10");
        when(ptr.getModeOfFrequency()).thenReturn("Mode Of Frequency");
        when(ptr.getNameOnAccount()).thenReturn("3");
        when(ptr.getPLC()).thenReturn("Plc");
        when(ptr.getPaymentAmount()).thenReturn("10");
        when(ptr.getPolicyNumber()).thenReturn("42");
        when(ptr.getPremiumAmount()).thenReturn("10");
        when(ptr.getPremiumDueDate()).thenReturn("2020-03-01");
        when(ptr.getRole()).thenReturn("Role");
        when(ptr.getSourceSystem()).thenReturn("Source System");
        when(ptr.getTokenId()).thenReturn("42");
        when(ptr.getTransactionId()).thenReturn("42");
        when(ptr.getUUID()).thenReturn("01234567-89AB-CDEF-FEDC-BA9876543210");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(ptr.getCreatedTime()).thenReturn(fromResult);
        doNothing().when(ptr).setAdminSystemId(Mockito.<String>any());
        doNothing().when(ptr).setApplicationId(Mockito.<String>any());
        doNothing().when(ptr).setBankAccountNumber(Mockito.<String>any());
        doNothing().when(ptr).setBankAccountType(Mockito.<String>any());
        doNothing().when(ptr).setBankName(Mockito.<String>any());
        doNothing().when(ptr).setBankRoutingNumber(Mockito.<String>any());
        doNothing().when(ptr).setCallType(Mockito.<String>any());
        doNothing().when(ptr).setCreatedTime(Mockito.<Date>any());
        doNothing().when(ptr).setDraftDueDate(anyInt());
        doNothing().when(ptr).setError(Mockito.<String>any());
        doNothing().when(ptr).setErrorDetails(Mockito.<String>any());
        doNothing().when(ptr).setId(Mockito.<Long>any());
        doNothing().when(ptr).setImmediateLoanDraft(Mockito.<String>any());
        doNothing().when(ptr).setImmediatePremiumDraft(Mockito.<String>any());
        doNothing().when(ptr).setLoanAmount(Mockito.<String>any());
        doNothing().when(ptr).setModeOfFrequency(Mockito.<String>any());
        doNothing().when(ptr).setNameOnAccount(Mockito.<String>any());
        doNothing().when(ptr).setPLC(Mockito.<String>any());
        doNothing().when(ptr).setPaymentAmount(Mockito.<String>any());
        doNothing().when(ptr).setPolicyNumber(Mockito.<String>any());
        doNothing().when(ptr).setPremiumAmount(Mockito.<String>any());
        doNothing().when(ptr).setPremiumDueDate(Mockito.<String>any());
        doNothing().when(ptr).setRole(Mockito.<String>any());
        doNothing().when(ptr).setSourceSystem(Mockito.<String>any());
        doNothing().when(ptr).setTokenId(Mockito.<String>any());
        doNothing().when(ptr).setTransactionId(Mockito.<String>any());
        doNothing().when(ptr).setUUID(Mockito.<String>any());
        doNothing().when(ptr).setWorkItemId(Mockito.<String>any());
        ptr.setAdminSystemId("42");
        ptr.setApplicationId("42");
        ptr.setBankAccountNumber("42");
        ptr.setBankAccountType("3");
        ptr.setBankName("Bank Name");
        ptr.setBankRoutingNumber("42");
        ptr.setCallType("Call Type");
        ptr.setCreatedTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        ptr.setDraftDueDate(1);
        ptr.setError("An error occurred");
        ptr.setErrorDetails("An error occurred");
        ptr.setId(1L);
        ptr.setImmediateLoanDraft("Immediate Loan Draft");
        ptr.setImmediatePremiumDraft("Immediate Premium Draft");
        ptr.setLoanAmount("10");
        ptr.setModeOfFrequency("Mode Of Frequency");
        ptr.setNameOnAccount("3");
        ptr.setPLC("PLC");
        ptr.setPaymentAmount("10");
        ptr.setPolicyNumber("42");
        ptr.setPremiumAmount("10");
        ptr.setPremiumDueDate("2020-03-01");
        ptr.setRole("Role");
        ptr.setSourceSystem("Source System");
        ptr.setTokenId("42");
        ptr.setTransactionId("42");
        ptr.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        ptr.setWorkItemId("42");


        PacInfoResult actualPacInfoResult = transformSvcImpl.getPacInfoResult(ptr);

        // Assert
        verify(ptr).getAdminSystemId();
        verify(ptr).getApplicationId();
        verify(ptr).getBankAccountNumber();
        verify(ptr).getBankAccountType();
        verify(ptr).getBankName();
        verify(ptr).getBankRoutingNumber();
        verify(ptr).getCreatedTime();
        verify(ptr).getDraftDueDate();
        verify(ptr, atLeast(1)).getError();
        verify(ptr).getErrorDetails();
        verify(ptr).getId();
        verify(ptr).getImmediateLoanDraft();
        verify(ptr).getImmediatePremiumDraft();
        verify(ptr).getLoanAmount();
        verify(ptr).getModeOfFrequency();
        verify(ptr).getNameOnAccount();
        verify(ptr).getPLC();
        verify(ptr).getPaymentAmount();
        verify(ptr).getPolicyNumber();
        verify(ptr).getPremiumAmount();
        verify(ptr).getPremiumDueDate();
        verify(ptr).getRole();
        verify(ptr).getSourceSystem();
        verify(ptr).getTokenId();
        verify(ptr, atLeast(1)).getTransactionId();
        verify(ptr).getUUID();
        verify(ptr).setAdminSystemId(eq("42"));
        verify(ptr).setApplicationId(eq("42"));
        verify(ptr).setBankAccountNumber(eq("42"));
        verify(ptr).setBankAccountType(eq("3"));
        verify(ptr).setBankName(eq("Bank Name"));
        verify(ptr).setBankRoutingNumber(eq("42"));
        verify(ptr).setCallType(eq("Call Type"));
        verify(ptr).setCreatedTime(isA(Date.class));
        verify(ptr).setDraftDueDate(eq(1));
        verify(ptr).setError(eq("An error occurred"));
        verify(ptr).setErrorDetails(eq("An error occurred"));
        verify(ptr).setId(eq(1L));
        verify(ptr).setImmediateLoanDraft(eq("Immediate Loan Draft"));
        verify(ptr).setImmediatePremiumDraft(eq("Immediate Premium Draft"));
        verify(ptr).setLoanAmount(eq("10"));
        verify(ptr).setModeOfFrequency(eq("Mode Of Frequency"));
        verify(ptr).setNameOnAccount(eq("3"));
        verify(ptr).setPLC(eq("PLC"));
        verify(ptr).setPaymentAmount(eq("10"));
        verify(ptr).setPolicyNumber(eq("42"));
        verify(ptr).setPremiumAmount(eq("10"));
        verify(ptr).setPremiumDueDate(eq("2020-03-01"));
        verify(ptr).setRole(eq("Role"));
        verify(ptr).setSourceSystem(eq("Source System"));
        verify(ptr).setTokenId(eq("42"));
        verify(ptr).setTransactionId(eq("42"));
        verify(ptr).setUUID(eq("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(ptr).setWorkItemId(eq("42"));
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualPacInfoResult.getUUID());
        PaymentDetails paymentDetails = actualPacInfoResult.getPaymentDetails();
        assertEquals("10", paymentDetails.getLoanAmount());
        assertEquals("10", paymentDetails.getPaymentAmount());
        assertEquals("10", paymentDetails.getPremiumAmount());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createdTime = actualPacInfoResult.getCreatedTime();
        //assertEquals("1970-01-01", simpleDateFormat.format(createdTime));
        assertEquals("2020-03-01", paymentDetails.getPremiumDueDate());
        BankDetails bankDetails = actualPacInfoResult.getBankDetails();
        assertEquals("3", bankDetails.getBankAccountType());
        assertEquals("3", bankDetails.getNameOnAccount());
        assertEquals("42", bankDetails.getBankAccountNumber());
        assertEquals("42", bankDetails.getBankRoutingNumber());
        assertEquals("42", actualPacInfoResult.getAdminSystemId());
        assertEquals("42", actualPacInfoResult.getApplicationId());
        assertEquals("42", actualPacInfoResult.getPolicyNumber());
        assertEquals("42", actualPacInfoResult.getTokenId());
        assertEquals("42", actualPacInfoResult.getTransactionId());
        assertEquals("An error occurred", actualPacInfoResult.getError());
        assertEquals("An error occurred", actualPacInfoResult.getErrorDetails());
        assertEquals("Bank Name", bankDetails.getBankName());
        assertEquals("Bank account ending with 42", bankDetails.getDisplay());
        assertEquals("Immediate Loan Draft", paymentDetails.getImmediateLoanDraft());
        assertEquals("Immediate Premium Draft", paymentDetails.getImmediatePremiumDraft());
        assertEquals("Mode Of Frequency", paymentDetails.getModeOfFrequency());
        assertEquals("Plc", actualPacInfoResult.getPLC());
        assertEquals("Role", actualPacInfoResult.getRole());
        assertEquals("Source System", actualPacInfoResult.getSourceSystem());
        assertNull(actualPacInfoResult.getPydDetails());
        assertNull(actualPacInfoResult.getCode());
        assertNull(actualPacInfoResult.getMessage());
        assertNull(actualPacInfoResult.getWorkItemId());
        assertEquals(1, paymentDetails.getDraftDueDate());
        assertEquals(1L, actualPacInfoResult.getId().longValue());
        assertSame(fromResult, createdTime);
    }

    @Test
    void testGetDividendResponse() {
        // Arrange and Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(new ArrayList<>());

        // Assert
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertTrue(actualDividendResponse.getResults().isEmpty());
    }

    @Test
    void testGetDividendResponse2() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("billing");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("billing");
        tPolicyPacInfoDividend.setPlc("billing");
        tPolicyPacInfoDividend.setPolicyActiveStatus("billing");
        tPolicyPacInfoDividend.setPolicyCategory("billing");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        Date PolicyUpdTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setPolicyUpdTs(PolicyUpdTs);
        tPolicyPacInfoDividend.setPrdcoverageid("billing");
        tPolicyPacInfoDividend.setSystemId("42");
        Date UpdateTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setUpdateTs(UpdateTs);

        ArrayList<TPolicyPacInfoDividend> records = new ArrayList<>();
        records.add(tPolicyPacInfoDividend);

        // Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(records);

        // Assert
        List<DividendRecord> results = actualDividendResponse.getResults();
        assertEquals(1, results.size());
        DividendRecord getResult = results.get(0);
        assertEquals("10", getResult.getDividendAmounts());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs = getResult.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat.format(policyUpdTs));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs = getResult.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat2.format(updateTs));
        assertEquals("42", getResult.getDividendAcctId());
        assertEquals("42", getResult.getPolicyNumber());
        assertEquals("42", getResult.getSystemId());
        assertEquals("billing", getResult.getCoverageId());
        assertEquals("billing", getResult.getDividendTypes());
        assertEquals("billing", getResult.getPlc());
        assertEquals("billing", getResult.getPolicyActiveStatus());
        assertEquals("billing", getResult.getPolicyCategory());
        assertEquals("billing", getResult.getPrdCoverageId());
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertSame(PolicyUpdTs, policyUpdTs);
        assertSame(UpdateTs, updateTs);
    }

    @Test
    void testGetDividendResponse3() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("billing");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("billing");
        tPolicyPacInfoDividend.setPlc("billing");
        tPolicyPacInfoDividend.setPolicyActiveStatus("billing");
        tPolicyPacInfoDividend.setPolicyCategory("billing");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        Date PolicyUpdTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setPolicyUpdTs(PolicyUpdTs);
        tPolicyPacInfoDividend.setPrdcoverageid("billing");
        tPolicyPacInfoDividend.setSystemId("42");
        Date UpdateTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setUpdateTs(UpdateTs);

        TPolicyPacInfoDividend tPolicyPacInfoDividend2 = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend2.setCoverageid("XX");
        tPolicyPacInfoDividend2.setDividendAcctId("billing");
        tPolicyPacInfoDividend2.setDividendAmounts("billing");
        tPolicyPacInfoDividend2.setDividendTypes("XX");
        tPolicyPacInfoDividend2.setPlc("XX");
        tPolicyPacInfoDividend2.setPolicyActiveStatus("XX");
        tPolicyPacInfoDividend2.setPolicyCategory("XX");
        tPolicyPacInfoDividend2.setPolicyNumber("billing");
        Date PolicyUpdTs2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend2.setPolicyUpdTs(PolicyUpdTs2);
        tPolicyPacInfoDividend2.setPrdcoverageid("XX");
        tPolicyPacInfoDividend2.setSystemId("billing");
        Date UpdateTs2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend2.setUpdateTs(UpdateTs2);

        ArrayList<TPolicyPacInfoDividend> records = new ArrayList<>();
        records.add(tPolicyPacInfoDividend2);
        records.add(tPolicyPacInfoDividend);

        // Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(records);

        // Assert
        List<DividendRecord> results = actualDividendResponse.getResults();
        assertEquals(2, results.size());
        DividendRecord getResult = results.get(0);
        assertEquals("", getResult.getCoverageId());
        assertEquals("", getResult.getPrdCoverageId());
        DividendRecord getResult2 = results.get(1);
        assertEquals("10", getResult2.getDividendAmounts());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs = getResult.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat.format(policyUpdTs));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs2 = getResult2.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat2.format(policyUpdTs2));
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs = getResult.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat3.format(updateTs));
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs2 = getResult2.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat4.format(updateTs2));
        assertEquals("42", getResult2.getDividendAcctId());
        assertEquals("42", getResult2.getPolicyNumber());
        assertEquals("42", getResult2.getSystemId());
        assertEquals("XX", getResult.getDividendTypes());
        assertEquals("XX", getResult.getPlc());
        assertEquals("XX", getResult.getPolicyActiveStatus());
        assertEquals("XX", getResult.getPolicyCategory());
        assertEquals("billing", getResult2.getCoverageId());
        assertEquals("billing", getResult.getDividendAcctId());
        assertEquals("billing", getResult.getDividendAmounts());
        assertEquals("billing", getResult2.getDividendTypes());
        assertEquals("billing", getResult2.getPlc());
        assertEquals("billing", getResult2.getPolicyActiveStatus());
        assertEquals("billing", getResult2.getPolicyCategory());
        assertEquals("billing", getResult.getPolicyNumber());
        assertEquals("billing", getResult2.getPrdCoverageId());
        assertEquals("billing", getResult.getSystemId());
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertSame(PolicyUpdTs2, policyUpdTs);
        assertSame(PolicyUpdTs, policyUpdTs2);
        assertSame(UpdateTs2, updateTs);
        assertSame(UpdateTs, updateTs2);
    }


    @Test
    void testGetDividendResponse4() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("billing");
        tPolicyPacInfoDividend.setDividendAcctId("XX");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("billing");
        tPolicyPacInfoDividend.setPlc("billing");
        tPolicyPacInfoDividend.setPolicyActiveStatus("billing");
        tPolicyPacInfoDividend.setPolicyCategory("billing");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        Date PolicyUpdTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setPolicyUpdTs(PolicyUpdTs);
        tPolicyPacInfoDividend.setPrdcoverageid("billing");
        tPolicyPacInfoDividend.setSystemId("42");
        Date UpdateTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setUpdateTs(UpdateTs);

        ArrayList<TPolicyPacInfoDividend> records = new ArrayList<>();
        records.add(tPolicyPacInfoDividend);

        // Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(records);

        // Assert
        List<DividendRecord> results = actualDividendResponse.getResults();
        assertEquals(1, results.size());
        DividendRecord getResult = results.get(0);
        assertEquals("", getResult.getDividendAcctId());
        assertEquals("10", getResult.getDividendAmounts());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs = getResult.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat.format(policyUpdTs));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs = getResult.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat2.format(updateTs));
        assertEquals("42", getResult.getPolicyNumber());
        assertEquals("42", getResult.getSystemId());
        assertEquals("billing", getResult.getCoverageId());
        assertEquals("billing", getResult.getDividendTypes());
        assertEquals("billing", getResult.getPlc());
        assertEquals("billing", getResult.getPolicyActiveStatus());
        assertEquals("billing", getResult.getPolicyCategory());
        assertEquals("billing", getResult.getPrdCoverageId());
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertSame(PolicyUpdTs, policyUpdTs);
        assertSame(UpdateTs, updateTs);
    }

    @Test
    void testGetDividendResponse5() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = new TPolicyPacInfoDividend();
        tPolicyPacInfoDividend.setCoverageid("billing");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("");
        tPolicyPacInfoDividend.setDividendTypes("billing");
        tPolicyPacInfoDividend.setPlc("billing");
        tPolicyPacInfoDividend.setPolicyActiveStatus("billing");
        tPolicyPacInfoDividend.setPolicyCategory("billing");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        Date PolicyUpdTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setPolicyUpdTs(PolicyUpdTs);
        tPolicyPacInfoDividend.setPrdcoverageid("billing");
        tPolicyPacInfoDividend.setSystemId("42");
        Date UpdateTs = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        tPolicyPacInfoDividend.setUpdateTs(UpdateTs);

        ArrayList<TPolicyPacInfoDividend> records = new ArrayList<>();
        records.add(tPolicyPacInfoDividend);

        // Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(records);

        // Assert
        List<DividendRecord> results = actualDividendResponse.getResults();
        assertEquals(1, results.size());
        DividendRecord getResult = results.get(0);
        assertEquals("", getResult.getDividendAmounts());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs = getResult.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat.format(policyUpdTs));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs = getResult.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat2.format(updateTs));
        assertEquals("42", getResult.getDividendAcctId());
        assertEquals("42", getResult.getPolicyNumber());
        assertEquals("42", getResult.getSystemId());
        assertEquals("billing", getResult.getCoverageId());
        assertEquals("billing", getResult.getDividendTypes());
        assertEquals("billing", getResult.getPlc());
        assertEquals("billing", getResult.getPolicyActiveStatus());
        assertEquals("billing", getResult.getPolicyCategory());
        assertEquals("billing", getResult.getPrdCoverageId());
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertSame(PolicyUpdTs, policyUpdTs);
        assertSame(UpdateTs, updateTs);
    }

    @Test
    void testGetDividendResponse6() {
        // Arrange
        TPolicyPacInfoDividend tPolicyPacInfoDividend = mock(TPolicyPacInfoDividend.class);
        when(tPolicyPacInfoDividend.getCoverageid()).thenReturn("Coverageid");
        when(tPolicyPacInfoDividend.getDividendAcctId()).thenReturn("42");
        when(tPolicyPacInfoDividend.getDividendAmounts()).thenReturn("10");
        when(tPolicyPacInfoDividend.getDividendTypes()).thenReturn("Dividend Types");
        when(tPolicyPacInfoDividend.getPlc()).thenReturn("Plc");
        when(tPolicyPacInfoDividend.getPolicyActiveStatus()).thenReturn("Policy Active Status");
        when(tPolicyPacInfoDividend.getPolicyCategory()).thenReturn("Policy Category");
        when(tPolicyPacInfoDividend.getPolicyNumber()).thenReturn("42");
        when(tPolicyPacInfoDividend.getPrdcoverageid()).thenReturn("Prdcoverageid");
        when(tPolicyPacInfoDividend.getSystemId()).thenReturn("42");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(tPolicyPacInfoDividend.getPolicyUpdTs()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(tPolicyPacInfoDividend.getUpdateTs()).thenReturn(fromResult2);
        doNothing().when(tPolicyPacInfoDividend).setCoverageid(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setDividendAcctId(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setDividendAmounts(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setDividendTypes(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setPlc(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setPolicyActiveStatus(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setPolicyCategory(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setPolicyNumber(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setPolicyUpdTs(Mockito.<Date>any());
        doNothing().when(tPolicyPacInfoDividend).setPrdcoverageid(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setSystemId(Mockito.<String>any());
        doNothing().when(tPolicyPacInfoDividend).setUpdateTs(Mockito.<Date>any());
        tPolicyPacInfoDividend.setCoverageid("billing");
        tPolicyPacInfoDividend.setDividendAcctId("42");
        tPolicyPacInfoDividend.setDividendAmounts("10");
        tPolicyPacInfoDividend.setDividendTypes("billing");
        tPolicyPacInfoDividend.setPlc("billing");
        tPolicyPacInfoDividend.setPolicyActiveStatus("billing");
        tPolicyPacInfoDividend.setPolicyCategory("billing");
        tPolicyPacInfoDividend.setPolicyNumber("42");
        tPolicyPacInfoDividend
                .setPolicyUpdTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        tPolicyPacInfoDividend.setPrdcoverageid("billing");
        tPolicyPacInfoDividend.setSystemId("42");
        tPolicyPacInfoDividend
                .setUpdateTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<TPolicyPacInfoDividend> records = new ArrayList<>();
        records.add(tPolicyPacInfoDividend);

        // Act
        DividendResponse actualDividendResponse = transformSvcImpl.getDividendResponse(records);

        // Assert
        verify(tPolicyPacInfoDividend, atLeast(1)).getCoverageid();
        verify(tPolicyPacInfoDividend, atLeast(1)).getDividendAcctId();
        verify(tPolicyPacInfoDividend, atLeast(1)).getDividendAmounts();
        verify(tPolicyPacInfoDividend).getDividendTypes();
        verify(tPolicyPacInfoDividend).getPlc();
        verify(tPolicyPacInfoDividend).getPolicyActiveStatus();
        verify(tPolicyPacInfoDividend).getPolicyCategory();
        verify(tPolicyPacInfoDividend).getPolicyNumber();
        verify(tPolicyPacInfoDividend).getPolicyUpdTs();
        verify(tPolicyPacInfoDividend, atLeast(1)).getPrdcoverageid();
        verify(tPolicyPacInfoDividend).getSystemId();
        verify(tPolicyPacInfoDividend).getUpdateTs();
        verify(tPolicyPacInfoDividend).setCoverageid(eq("billing"));
        verify(tPolicyPacInfoDividend).setDividendAcctId(eq("42"));
        verify(tPolicyPacInfoDividend).setDividendAmounts(eq("10"));
        verify(tPolicyPacInfoDividend).setDividendTypes(eq("billing"));
        verify(tPolicyPacInfoDividend).setPlc(eq("billing"));
        verify(tPolicyPacInfoDividend).setPolicyActiveStatus(eq("billing"));
        verify(tPolicyPacInfoDividend).setPolicyCategory(eq("billing"));
        verify(tPolicyPacInfoDividend).setPolicyNumber(eq("42"));
        verify(tPolicyPacInfoDividend).setPolicyUpdTs(isA(Date.class));
        verify(tPolicyPacInfoDividend).setPrdcoverageid(eq("billing"));
        verify(tPolicyPacInfoDividend).setSystemId(eq("42"));
        verify(tPolicyPacInfoDividend).setUpdateTs(isA(Date.class));
        List<DividendRecord> results = actualDividendResponse.getResults();
        assertEquals(1, results.size());
        DividendRecord getResult = results.get(0);
        assertEquals("10", getResult.getDividendAmounts());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date policyUpdTs = getResult.getPolicyUpdTs();
        //assertEquals("1970-01-01", simpleDateFormat.format(policyUpdTs));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date updateTs = getResult.getUpdateTs();
        //assertEquals("1970-01-01", simpleDateFormat2.format(updateTs));
        assertEquals("42", getResult.getDividendAcctId());
        assertEquals("42", getResult.getPolicyNumber());
        assertEquals("42", getResult.getSystemId());
        assertEquals("Coverageid", getResult.getCoverageId());
        assertEquals("Dividend Types", getResult.getDividendTypes());
        assertEquals("Plc", getResult.getPlc());
        assertEquals("Policy Active Status", getResult.getPolicyActiveStatus());
        assertEquals("Policy Category", getResult.getPolicyCategory());
        assertEquals("Prdcoverageid", getResult.getPrdCoverageId());
        assertEquals("billing", actualDividendResponse.getCode());
        assertNull(actualDividendResponse.getErrorMessage());
        assertTrue(actualDividendResponse.isSuccessful());
        assertSame(fromResult, policyUpdTs);
        assertSame(fromResult2, updateTs);
    }


    @Test
    void testGetAWDRequest() {
        // Arrange
        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act and Assert
        assertThrows(ApiException.BadRequestException.class,
                () -> transformSvcImpl.getAWDRequest(pacSuspendRequest, "V Pas One Success"));
    }

    @Test
    void testGetPacSuspendResponse() {
        // Arrange
        PacSuspendRequest request = new PacSuspendRequest();
        request.setAdminSystemId("42");
        request.setAppId("42");
        request.setModeOfFrequency("Mode Of Frequency");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setSourceSystem("Source System");

        // Act
        PacSuspendResponse actualPacSuspendResponse = transformSvcImpl.getPacSuspendResponse("42", request);

        // Assert
        assertEquals("42", actualPacSuspendResponse.getPolicyNumber());
        assertEquals("42", actualPacSuspendResponse.getWorkItemId());
        assertEquals("Plc", actualPacSuspendResponse.getPlc());
        assertEquals("workitem", actualPacSuspendResponse.getCode());
        assertNull(actualPacSuspendResponse.getVPasOneResponse());
        assertNull(actualPacSuspendResponse.getConfirmId());
        assertTrue(actualPacSuspendResponse.isSuccessful());
    }

    @Test
    void testGetPacSuspendResponse2() {
        // Arrange
        PacSuspendRequest request = mock(PacSuspendRequest.class);
        when(request.getPlc()).thenReturn("Plc");
        when(request.getPolicyNumber()).thenReturn("42");
        doNothing().when(request).setAdminSystemId(Mockito.<String>any());
        doNothing().when(request).setAppId(Mockito.<String>any());
        doNothing().when(request).setModeOfFrequency(Mockito.<String>any());
        doNothing().when(request).setPlc(Mockito.<String>any());
        doNothing().when(request).setPolicyNumber(Mockito.<String>any());
        doNothing().when(request).setSourceSystem(Mockito.<String>any());
        request.setAdminSystemId("42");
        request.setAppId("42");
        request.setModeOfFrequency("Mode Of Frequency");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setSourceSystem("Source System");

        // Act
        PacSuspendResponse actualPacSuspendResponse = transformSvcImpl.getPacSuspendResponse("42", request);

        // Assert
        verify(request).getPlc();
        verify(request).getPolicyNumber();
        verify(request).setAdminSystemId(eq("42"));
        verify(request).setAppId(eq("42"));
        verify(request).setModeOfFrequency(eq("Mode Of Frequency"));
        verify(request).setPlc(eq("Plc"));
        verify(request).setPolicyNumber(eq("42"));
        verify(request).setSourceSystem(eq("Source System"));
        assertEquals("42", actualPacSuspendResponse.getPolicyNumber());
        assertEquals("42", actualPacSuspendResponse.getWorkItemId());
        assertEquals("Plc", actualPacSuspendResponse.getPlc());
        assertEquals("workitem", actualPacSuspendResponse.getCode());
        assertNull(actualPacSuspendResponse.getVPasOneResponse());
        assertNull(actualPacSuspendResponse.getConfirmId());
        assertTrue(actualPacSuspendResponse.isSuccessful());
    }


    @Test
    void testGetPacSuspendResponse3() {
        // Arrange
        PacSuspendRequest request = new PacSuspendRequest();
        request.setAdminSystemId("42");
        request.setAppId("42");
        request.setModeOfFrequency("Mode Of Frequency");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setSourceSystem("Source System");

        RetVal retVal = new RetVal();
        retVal.setMessage("Not all who wander are lost");
        retVal.setSuccessful("Successful");

        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        vPasOneResponse.setChangeEffDate("2020-03-01");
        vPasOneResponse.setErrorStatus("An error occurred");
        vPasOneResponse.setRetVal(retVal);

        // Act
        PacSuspendResponse actualPacSuspendResponse = transformSvcImpl.getPacSuspendResponse("42", request,
                vPasOneResponse);

        // Assert
        assertEquals("42", actualPacSuspendResponse.getPolicyNumber());
        assertEquals("42", actualPacSuspendResponse.getWorkItemId());
        assertEquals("Plc", actualPacSuspendResponse.getPlc());
        assertEquals("workitem", actualPacSuspendResponse.getCode());
        assertNull(actualPacSuspendResponse.getConfirmId());
        assertTrue(actualPacSuspendResponse.isSuccessful());
        assertSame(vPasOneResponse, actualPacSuspendResponse.getVPasOneResponse());
    }

    @Test
    void testGetPacSuspendResponse4() {
        // Arrange
        PacSuspendRequest request = mock(PacSuspendRequest.class);
        when(request.getPlc()).thenReturn("Plc");
        when(request.getPolicyNumber()).thenReturn("42");
        doNothing().when(request).setAdminSystemId(Mockito.<String>any());
        doNothing().when(request).setAppId(Mockito.<String>any());
        doNothing().when(request).setModeOfFrequency(Mockito.<String>any());
        doNothing().when(request).setPlc(Mockito.<String>any());
        doNothing().when(request).setPolicyNumber(Mockito.<String>any());
        doNothing().when(request).setSourceSystem(Mockito.<String>any());
        request.setAdminSystemId("42");
        request.setAppId("42");
        request.setModeOfFrequency("Mode Of Frequency");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setSourceSystem("Source System");

        RetVal retVal = new RetVal();
        retVal.setMessage("Not all who wander are lost");
        retVal.setSuccessful("Successful");

        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        vPasOneResponse.setChangeEffDate("2020-03-01");
        vPasOneResponse.setErrorStatus("An error occurred");
        vPasOneResponse.setRetVal(retVal);

        // Act
        PacSuspendResponse actualPacSuspendResponse = transformSvcImpl.getPacSuspendResponse("42", request,
                vPasOneResponse);

        // Assert
        verify(request).getPlc();
        verify(request).getPolicyNumber();
        verify(request).setAdminSystemId(eq("42"));
        verify(request).setAppId(eq("42"));
        verify(request).setModeOfFrequency(eq("Mode Of Frequency"));
        verify(request).setPlc(eq("Plc"));
        verify(request).setPolicyNumber(eq("42"));
        verify(request).setSourceSystem(eq("Source System"));
        assertEquals("42", actualPacSuspendResponse.getPolicyNumber());
        assertEquals("42", actualPacSuspendResponse.getWorkItemId());
        assertEquals("Plc", actualPacSuspendResponse.getPlc());
        assertEquals("workitem", actualPacSuspendResponse.getCode());
        assertNull(actualPacSuspendResponse.getConfirmId());
        assertTrue(actualPacSuspendResponse.isSuccessful());
        assertSame(vPasOneResponse, actualPacSuspendResponse.getVPasOneResponse());
    }
}
