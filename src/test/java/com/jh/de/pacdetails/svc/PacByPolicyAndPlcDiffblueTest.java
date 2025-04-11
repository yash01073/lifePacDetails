package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.PYDDetails;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PaymentDetails;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoRepository;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class PacByPolicyAndPlcDiffblueTest {
    @Mock
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;

    @Mock
    private PDCOPaymentDelegatorService pDCOPaymentDelegatorService;

    @InjectMocks
    private PacByPolicyAndPlc pacByPolicyAndPlc;

    @Mock
    private RequestHeaderBean requestHeaderBean;

    @Mock
    private TPolicyPacInfoRepository tPolicyPacInfoRepository;

    @Mock
    private TransformSvc transformSvc;

    @Mock
    private ValidateSvc validateSvc;

    @Mock
    private VirtualDataSvc virtualDataSvc;

    /**
     * Test {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given empty string.</li>
     *   <li>When {@link PacInfoRequest} (default constructor) Plc is empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given empty string; when PacInfoRequest (default constructor) Plc is empty string")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenEmptyString_whenPacInfoRequestPlcIsEmptyString() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        request.setPolicyNumber("not empty");
        request.setPlc("");

        // Act and Assert
        assertFalse(pacByPolicyAndPlc.isApplicable(request));
    }

    /**
     * Test {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link PacInfoRequest} (default constructor) Plc is {@code null}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given 'null'; when PacInfoRequest (default constructor) Plc is 'null'; then return 'false'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenNull_whenPacInfoRequestPlcIsNull_thenReturnFalse() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        request.setPolicyNumber("not empty");
        request.setPlc(null);

        // Act and Assert
        assertFalse(pacByPolicyAndPlc.isApplicable(request));
    }

    /**
     * Test {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@code null}.</li>
     *   <li>When {@link PacInfoRequest} (default constructor) PolicyNumber is {@code null}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given 'null'; when PacInfoRequest (default constructor) PolicyNumber is 'null'; then return 'false'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenNull_whenPacInfoRequestPolicyNumberIsNull_thenReturnFalse() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        request.setPolicyNumber(null);
        request.setPlc("not empty");

        // Act and Assert
        assertFalse(pacByPolicyAndPlc.isApplicable(request));
    }

    /**
     * Test {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@code Plc}.</li>
     *   <li>When {@link PacInfoRequest} (default constructor) Plc is {@code Plc}.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given 'Plc'; when PacInfoRequest (default constructor) Plc is 'Plc'; then return 'true'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenPlc_whenPacInfoRequestPlcIsPlc_thenReturnTrue() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertTrue(pacByPolicyAndPlc.isApplicable(request));
    }

    /**
     * Test {@link PacByPolicyAndPlc#getResponse(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@link TPolicyPacInfoRepository}.</li>
     *   <li>Then return first is {@link PacInfoResult} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#getResponse(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getResponse(PacInfoRequest); given TPolicyPacInfoRepository; then return first is PacInfoResult (default constructor)")
    @Tag("MaintainedByDiffblue")
    void testGetResponse_givenTPolicyPacInfoRepository_thenReturnFirstIsPacInfoResult() {
        // Arrange
        LifeRecurringPaymentTransaction lifeRecurringPaymentTransaction = new LifeRecurringPaymentTransaction();
        lifeRecurringPaymentTransaction.setAdminSystemId("42");
        lifeRecurringPaymentTransaction.setApplicationId("42");
        lifeRecurringPaymentTransaction.setBankAccountNumber("42");
        lifeRecurringPaymentTransaction.setBankAccountType("3");
        lifeRecurringPaymentTransaction.setBankName("Bank Name");
        lifeRecurringPaymentTransaction.setBankRoutingNumber("42");
        lifeRecurringPaymentTransaction.setCallType("Call Type");
        lifeRecurringPaymentTransaction
                .setCreatedTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        lifeRecurringPaymentTransaction.setDraftDueDate(1);
        lifeRecurringPaymentTransaction.setError("An error occurred");
        lifeRecurringPaymentTransaction.setErrorDetails("An error occurred");
        lifeRecurringPaymentTransaction.setId(1L);
        lifeRecurringPaymentTransaction.setImmediateLoanDraft("Immediate Loan Draft");
        lifeRecurringPaymentTransaction.setImmediatePremiumDraft("Immediate Premium Draft");
        lifeRecurringPaymentTransaction.setLoanAmount("10");
        lifeRecurringPaymentTransaction.setModeOfFrequency("Mode Of Frequency");
        lifeRecurringPaymentTransaction.setNameOnAccount("3");
        lifeRecurringPaymentTransaction.setPLC("PLC");
        lifeRecurringPaymentTransaction.setPaymentAmount("10");
        lifeRecurringPaymentTransaction.setPolicyNumber("42");
        lifeRecurringPaymentTransaction.setPremiumAmount("10");
        lifeRecurringPaymentTransaction.setPremiumDueDate("2020-03-01");
        lifeRecurringPaymentTransaction.setRole("Role");
        lifeRecurringPaymentTransaction.setSourceSystem("Source System");
        lifeRecurringPaymentTransaction.setTokenId("42");
        lifeRecurringPaymentTransaction.setTransactionId("42");
        lifeRecurringPaymentTransaction.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        lifeRecurringPaymentTransaction.setWorkItemId("42");
        when(lifeRecurringPaymentTransactionRepository.findTransactionByPolicyNumberAndPlc(Mockito.<String>any(),
                Mockito.<String>any())).thenReturn(lifeRecurringPaymentTransaction);

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
        when(transformSvc.getPacInfoResult(Mockito.<LifeRecurringPaymentTransaction>any())).thenReturn(pacInfoResult);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualResponse = pacByPolicyAndPlc.getResponse(request);

        // Assert
        verify(lifeRecurringPaymentTransactionRepository).findTransactionByPolicyNumberAndPlc(eq("42"), eq("Plc"));
        verify(transformSvc).getPacInfoResult(isA(LifeRecurringPaymentTransaction.class));
        assertEquals(1, actualResponse.size());
        assertSame(pacInfoResult, actualResponse.get(0));
    }

    /**
     * Test {@link PacByPolicyAndPlc#getResponse(PacInfoRequest)}.
     * <ul>
     *   <li>Then return first BankDetails Display is {@code Bank account ending with 42}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#getResponse(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getResponse(PacInfoRequest); then return first BankDetails Display is 'Bank account ending with 42'")
    @Tag("MaintainedByDiffblue")
    void testGetResponse_thenReturnFirstBankDetailsDisplayIsBankAccountEndingWith42() {
        // Arrange
        LifeRecurringPaymentTransaction lifeRecurringPaymentTransaction = new LifeRecurringPaymentTransaction();
        lifeRecurringPaymentTransaction.setAdminSystemId("42");
        lifeRecurringPaymentTransaction.setApplicationId("42");
        lifeRecurringPaymentTransaction.setBankAccountNumber("42");
        lifeRecurringPaymentTransaction.setBankAccountType("3");
        lifeRecurringPaymentTransaction.setBankName("Bank Name");
        lifeRecurringPaymentTransaction.setBankRoutingNumber("42");
        lifeRecurringPaymentTransaction.setCallType("Call Type");
        lifeRecurringPaymentTransaction
                .setCreatedTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        lifeRecurringPaymentTransaction.setDraftDueDate(1);
        lifeRecurringPaymentTransaction.setError("An error occurred");
        lifeRecurringPaymentTransaction.setErrorDetails("An error occurred");
        lifeRecurringPaymentTransaction.setId(1L);
        lifeRecurringPaymentTransaction.setImmediateLoanDraft("Immediate Loan Draft");
        lifeRecurringPaymentTransaction.setImmediatePremiumDraft("Immediate Premium Draft");
        lifeRecurringPaymentTransaction.setLoanAmount("10");
        lifeRecurringPaymentTransaction.setModeOfFrequency("Mode Of Frequency");
        lifeRecurringPaymentTransaction.setNameOnAccount("3");
        lifeRecurringPaymentTransaction.setPLC("PLC");
        lifeRecurringPaymentTransaction.setPaymentAmount("10");
        lifeRecurringPaymentTransaction.setPolicyNumber("42");
        lifeRecurringPaymentTransaction.setPremiumAmount("10");
        lifeRecurringPaymentTransaction.setPremiumDueDate("2020-03-01");
        lifeRecurringPaymentTransaction.setRole("Role");
        lifeRecurringPaymentTransaction.setSourceSystem("Source System");
        lifeRecurringPaymentTransaction.setTokenId("42");
        lifeRecurringPaymentTransaction.setTransactionId("42");
        lifeRecurringPaymentTransaction.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        lifeRecurringPaymentTransaction.setWorkItemId("42");
        LifeRecurringPaymentTransactionRepository ptrRepo = mock(LifeRecurringPaymentTransactionRepository.class);
        when(ptrRepo.findTransactionByPolicyNumberAndPlc(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(lifeRecurringPaymentTransaction);

        PacByPolicyAndPlc pacByPolicyAndPlc = new PacByPolicyAndPlc();
        pacByPolicyAndPlc.setTransformSvc(new TransformSvcImpl());
        pacByPolicyAndPlc.setPtrRepo(ptrRepo);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualResponse = pacByPolicyAndPlc.getResponse(request);

        // Assert
        verify(ptrRepo).findTransactionByPolicyNumberAndPlc(eq("42"), eq("Plc"));
        assertEquals(1, actualResponse.size());
        PacInfoResult getResult = actualResponse.get(0);
        assertEquals("Bank account ending with 42", getResult.getBankDetails().getDisplay());
        assertNull(getResult.getPydDetails());
        assertNull(getResult.getCode());
        assertNull(getResult.getMessage());
        assertNull(getResult.getWorkItemId());
    }

    /**
     * Test {@link PacByPolicyAndPlc#getErrorMessage(PacInfoRequest)}.
     * <p>
     * Method under test: {@link PacByPolicyAndPlc#getErrorMessage(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getErrorMessage(PacInfoRequest)")
    @Tag("MaintainedByDiffblue")
    void testGetErrorMessage() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertEquals("Latest PAC info does not exist for  policy: 42 and PLC: Plc",
                pacByPolicyAndPlc.getErrorMessage(request));
    }
}
