package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.exception.ApiException.BadResponseException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.AWDResponse;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.model.response.Detail;
import com.jh.de.pacdetails.validate.BillingPacInfoResponseValidate;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import com.jh.de.pacdetails.validate.PacInfoRequestValidate;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import com.jh.de.pacdetails.validate.PdcoRequestValidate;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

@ContextConfiguration(classes = {ValidateSvcImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ValidateSvcImplDiffblueTest {
    @MockitoBean
    private BillingPacInfoResponseValidate billingPacInfoResponseValidate;

    @MockitoBean
    private DividendRequestValidate dividendRequestValidate;

    @Autowired
    private List<PacInfoRequestValidate> list;

    @Autowired
    private List<BillingPacInfoResponseValidate> list2;

    @Autowired
    private List<DividendRequestValidate> list3;

    @Autowired
    private List<PacSuspendRequestValidate> list4;

    @Autowired
    private List<PdcoRequestValidate> list5;

    @MockitoBean
    private PacInfoRequestValidate pacInfoRequestValidate;

    @MockitoBean
    private PacSuspendRequestValidate pacSuspendRequestValidate;

    @MockitoBean
    private PdcoRequestValidate pdcoRequestValidate;

    @Autowired
    private ValidateSvcImpl validateSvcImpl;

    /**
     * Test {@link ValidateSvcImpl#validateRequest(DividendRequest)} with {@code dividendRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(DividendRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(DividendRequest) with 'dividendRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithDividendRequest() {
        // Arrange
        doNothing().when(dividendRequestValidate).validate(Mockito.<DividendRequest>any());

        DividendRequest dividendRequest = new DividendRequest();
        dividendRequest.setDividendAccountType("3");
        dividendRequest.setDividendAccountTypes(new ArrayList<>());
        dividendRequest.setDividendType("Dividend Type");
        dividendRequest.setDividendTypes(new ArrayList<>());
        dividendRequest.setPlc("Plc");
        dividendRequest.setPolicyNumber("42");

        // Act
        validateSvcImpl.validateRequest(dividendRequest);

        // Assert
        verify(dividendRequestValidate).validate(isA(DividendRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(DividendRequest)} with {@code dividendRequest}.
     * <ul>
     *   <li>Then throw {@link BadResponseException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(DividendRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(DividendRequest) with 'dividendRequest'; then throw BadResponseException")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithDividendRequest_thenThrowBadResponseException() {
        // Arrange
        doThrow(new BadResponseException("An error occurred")).when(dividendRequestValidate)
                .validate(Mockito.<DividendRequest>any());

        DividendRequest dividendRequest = new DividendRequest();
        dividendRequest.setDividendAccountType("3");
        dividendRequest.setDividendAccountTypes(new ArrayList<>());
        dividendRequest.setDividendType("Dividend Type");
        dividendRequest.setDividendTypes(new ArrayList<>());
        dividendRequest.setPlc("Plc");
        dividendRequest.setPolicyNumber("42");

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateRequest(dividendRequest));
        verify(dividendRequestValidate).validate(isA(DividendRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacInfoRequest)} with {@code pacInfoRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacInfoRequest) with 'pacInfoRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacInfoRequest() {
        // Arrange
        doNothing().when(pacInfoRequestValidate).validate(Mockito.<PacInfoRequest>any());

        PacInfoRequest pacInfoRequest = new PacInfoRequest();
        pacInfoRequest.setAdminSystem("Admin System");
        pacInfoRequest.setBusiness("Business");
        pacInfoRequest.setCallType("Call Type");
        pacInfoRequest.setPlc("Plc");
        pacInfoRequest.setPolicyNumber("42");
        pacInfoRequest.setTransactionId("42");
        pacInfoRequest.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        validateSvcImpl.validateRequest(pacInfoRequest);

        // Assert
        verify(pacInfoRequestValidate).validate(isA(PacInfoRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacInfoRequest)} with {@code pacInfoRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacInfoRequest) with 'pacInfoRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacInfoRequest2() {
        // Arrange
        doThrow(new BadResponseException("An error occurred")).when(pdcoRequestValidate)
                .validate(Mockito.<PacInfoRequest>any());

        PacInfoRequest pacInfoRequest = new PacInfoRequest();
        pacInfoRequest.setAdminSystem("Admin System");
        pacInfoRequest.setBusiness("Business");
        pacInfoRequest.setCallType("PDCO");
        pacInfoRequest.setPlc("Plc");
        pacInfoRequest.setPolicyNumber("42");
        pacInfoRequest.setTransactionId("42");
        pacInfoRequest.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateRequest(pacInfoRequest));
        verify(pdcoRequestValidate).validate(isA(PacInfoRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacInfoRequest)} with {@code pacInfoRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacInfoRequest) with 'pacInfoRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacInfoRequest3() {
        // Arrange
        doNothing().when(pdcoRequestValidate).validate(Mockito.<PacInfoRequest>any());

        PacInfoRequest pacInfoRequest = new PacInfoRequest();
        pacInfoRequest.setAdminSystem("Admin System");
        pacInfoRequest.setBusiness("Business");
        pacInfoRequest.setCallType("PDCO");
        pacInfoRequest.setPlc("Plc");
        pacInfoRequest.setPolicyNumber("42");
        pacInfoRequest.setTransactionId("42");
        pacInfoRequest.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        validateSvcImpl.validateRequest(pacInfoRequest);

        // Assert
        verify(pdcoRequestValidate).validate(isA(PacInfoRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacInfoRequest)} with {@code pacInfoRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacInfoRequest) with 'pacInfoRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacInfoRequest4() {
        // Arrange
        PacInfoRequestValidate pacInfoRequestValidate = mock(PacInfoRequestValidate.class);
        doThrow(new BadResponseException("An error occurred")).when(pacInfoRequestValidate)
                .validate(Mockito.<PacInfoRequest>any());

        ArrayList<PacInfoRequestValidate> pacInfoRequestValidateList = new ArrayList<>();
        pacInfoRequestValidateList.add(pacInfoRequestValidate);
        pacInfoRequestValidateList.add(mock(PacInfoRequestValidate.class));

        ArrayList<PdcoRequestValidate> pdcoRequestValidateList = new ArrayList<>();
        pdcoRequestValidateList.add(mock(PdcoRequestValidate.class));
        ArrayList<BillingPacInfoResponseValidate> billingPacInfoResponseValidateList = new ArrayList<>();
        ArrayList<DividendRequestValidate> dividendRequestValidateList = new ArrayList<>();
        ValidateSvcImpl validateSvcImpl = new ValidateSvcImpl(pacInfoRequestValidateList,
                billingPacInfoResponseValidateList, dividendRequestValidateList, new ArrayList<>(), pdcoRequestValidateList);

        PacInfoRequest pacInfoRequest = new PacInfoRequest();
        pacInfoRequest.setAdminSystem("Admin System");
        pacInfoRequest.setBusiness("Business");
        pacInfoRequest.setCallType("Call Type");
        pacInfoRequest.setPlc("Plc");
        pacInfoRequest.setPolicyNumber("42");
        pacInfoRequest.setTransactionId("42");
        pacInfoRequest.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateRequest(pacInfoRequest));
        verify(pacInfoRequestValidate).validate(isA(PacInfoRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacSuspendRequest)} with {@code pacSuspendRequest}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacSuspendRequest) with 'pacSuspendRequest'")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacSuspendRequest() {
        // Arrange
        doNothing().when(pacSuspendRequestValidate).validate(Mockito.<PacSuspendRequest>any());

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act
        validateSvcImpl.validateRequest(pacSuspendRequest);

        // Assert
        verify(pacSuspendRequestValidate).validate(isA(PacSuspendRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateRequest(PacSuspendRequest)} with {@code pacSuspendRequest}.
     * <ul>
     *   <li>Then throw {@link BadResponseException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateRequest(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test validateRequest(PacSuspendRequest) with 'pacSuspendRequest'; then throw BadResponseException")
    @Tag("MaintainedByDiffblue")
    void testValidateRequestWithPacSuspendRequest_thenThrowBadResponseException() {
        // Arrange
        doThrow(new BadResponseException("An error occurred")).when(pacSuspendRequestValidate)
                .validate(Mockito.<PacSuspendRequest>any());

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateRequest(pacSuspendRequest));
        verify(pacSuspendRequestValidate).validate(isA(PacSuspendRequest.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>Given {@link AWDResponse} (default constructor) Instance is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; given AWDResponse (default constructor) Instance is 'null'")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_givenAWDResponseInstanceIsNull() {
        // Arrange
        AWDResponse awdResponse = new AWDResponse();
        awdResponse.setInstance(null);

        ArrayList<AWDResponse> list6 = new ArrayList<>();
        list6.add(awdResponse);

        Detail details = new Detail();
        details.setList(list6);

        AwdCreateWorkItemResponse awdResponse2 = new AwdCreateWorkItemResponse();
        awdResponse2.setCode("Code");
        awdResponse2.setMessage("Not all who wander are lost");
        awdResponse2.setDetails(details);

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(awdResponse2));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>Given {@link Detail} (default constructor) List is {@link ArrayList#ArrayList()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; given Detail (default constructor) List is ArrayList()")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_givenDetailListIsArrayList() {
        // Arrange
        Detail details = new Detail();
        details.setList(new ArrayList<>());

        AwdCreateWorkItemResponse awdResponse = new AwdCreateWorkItemResponse();
        awdResponse.setCode("Code");
        awdResponse.setDetails(details);
        awdResponse.setMessage("Not all who wander are lost");

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(awdResponse));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>Given {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; given 'null'")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_givenNull() {
        // Arrange
        AwdCreateWorkItemResponse awdResponse = new AwdCreateWorkItemResponse();
        awdResponse.setCode("Code");
        awdResponse.setMessage("Not all who wander are lost");
        awdResponse.setDetails(null);

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(awdResponse));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>Then calls {@link AwdCreateWorkItemResponse#getDetails()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; then calls getDetails()")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_thenCallsGetDetails() {
        // Arrange
        AwdCreateWorkItemResponse awdResponse = mock(AwdCreateWorkItemResponse.class);
        when(awdResponse.getDetails()).thenThrow(new BadResponseException("An error occurred"));

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(awdResponse));
        verify(awdResponse).getDetails();
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>Then calls {@link Detail#getList()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; then calls getList()")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_thenCallsGetList() {
        // Arrange
        Detail details = mock(Detail.class);
        when(details.getList()).thenThrow(new BadResponseException("An error occurred"));

        AwdCreateWorkItemResponse awdResponse = new AwdCreateWorkItemResponse();
        awdResponse.setCode("Code");
        awdResponse.setMessage("Not all who wander are lost");
        awdResponse.setDetails(details);

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(awdResponse));
        verify(details).getList();
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)} with {@code awdResponse}.
     * <ul>
     *   <li>When {@code null}.</li>
     *   <li>Then throw {@link BadResponseException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(AwdCreateWorkItemResponse)}
     */
    @Test
    @DisplayName("Test validateResponse(AwdCreateWorkItemResponse) with 'awdResponse'; when 'null'; then throw BadResponseException")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithAwdResponse_whenNull_thenThrowBadResponseException() {
        // Arrange, Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse((AwdCreateWorkItemResponse) null));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)} with {@code billing}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)}
     */
    @Test
    @DisplayName("Test validateResponse(TPolicyPacInfo) with 'billing'")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithBilling() {
        // Arrange
        when(billingPacInfoResponseValidate.validate(Mockito.<TPolicyPacInfo>any()))
                .thenThrow(new BadResponseException("An error occurred"));

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

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(billing));
        verify(billingPacInfoResponseValidate).validate(isA(TPolicyPacInfo.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)} with {@code billing}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)}
     */
    @Test
    @DisplayName("Test validateResponse(TPolicyPacInfo) with 'billing'")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithBilling2() {
        // Arrange
        when(billingPacInfoResponseValidate.validate(Mockito.<TPolicyPacInfo>any())).thenReturn("2020-03-01");

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

        // Act and Assert
        assertThrows(BadResponseException.class, () -> validateSvcImpl.validateResponse(billing));
        verify(billingPacInfoResponseValidate).validate(isA(TPolicyPacInfo.class));
    }

    /**
     * Test {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)} with {@code billing}.
     * <p>
     * Method under test: {@link ValidateSvcImpl#validateResponse(TPolicyPacInfo)}
     */
    @Test
    @DisplayName("Test validateResponse(TPolicyPacInfo) with 'billing'")
    @Tag("MaintainedByDiffblue")
    void testValidateResponseWithBilling3() {
        // Arrange
        when(billingPacInfoResponseValidate.validate(Mockito.<TPolicyPacInfo>any())).thenReturn("");

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
        validateSvcImpl.validateResponse(billing);

        // Assert
        verify(billingPacInfoResponseValidate).validate(isA(TPolicyPacInfo.class));
    }
}
