package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.exception.ApiError;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.DividendRecord;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.model.response.PYDDetails;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.response.VPasOneResponse;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.newrelic.api.agent.NewRelic;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {NewRelicService.class})
@ExtendWith(SpringExtension.class)
class NewRelicServiceDiffblueTest {
    @Autowired
    private NewRelicService newRelicService;

    /**
     * Test {@link NewRelicService#populateInputRequest(DividendRequest)} with {@code dividendRequest}.
     * <p>
     * Method under test: {@link NewRelicService#populateInputRequest(DividendRequest)}
     */
    @Test
    @DisplayName("Test populateInputRequest(DividendRequest) with 'dividendRequest'")
    @Tag("MaintainedByDiffblue")
    void testPopulateInputRequestWithDividendRequest() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        DividendRequest dividendRequest = new DividendRequest();
        dividendRequest.setDividendAccountType("3");
        dividendRequest.setDividendAccountTypes(new ArrayList<>());
        dividendRequest.setDividendType("Dividend Type");
        dividendRequest.setDividendTypes(new ArrayList<>());
        dividendRequest.setPlc("Plc");
        dividendRequest.setPolicyNumber("42");

        // Act
        newRelicService.populateInputRequest(dividendRequest);
    }

    /**
     * Test {@link NewRelicService#populateInputRequest(PacInfoRequest)} with {@code pacInfoRequest}.
     * <p>
     * Method under test: {@link NewRelicService#populateInputRequest(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test populateInputRequest(PacInfoRequest) with 'pacInfoRequest'")
    @Tag("MaintainedByDiffblue")
    void testPopulateInputRequestWithPacInfoRequest() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        PacInfoRequest pacInfoRequest = new PacInfoRequest();
        pacInfoRequest.setAdminSystem("Admin System");
        pacInfoRequest.setBusiness("Business");
        pacInfoRequest.setCallType("Call Type");
        pacInfoRequest.setPlc("Plc");
        pacInfoRequest.setPolicyNumber("42");
        pacInfoRequest.setTransactionId("42");
        pacInfoRequest.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        newRelicService.populateInputRequest(pacInfoRequest);
    }

    /**
     * Test {@link NewRelicService#populateInputRequest(PacSuspendRequest)} with {@code pacSuspendRequest}.
     * <p>
     * Method under test: {@link NewRelicService#populateInputRequest(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test populateInputRequest(PacSuspendRequest) with 'pacSuspendRequest'")
    @Tag("MaintainedByDiffblue")
    void testPopulateInputRequestWithPacSuspendRequest() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act
        newRelicService.populateInputRequest(pacSuspendRequest);
    }

    /**
     * Test {@link NewRelicService#populateVPasOneResponse(VPasOneResponse)}.
     * <p>
     * Method under test: {@link NewRelicService#populateVPasOneResponse(VPasOneResponse)}
     */
    @Test
    @DisplayName("Test populateVPasOneResponse(VPasOneResponse)")
    @Tag("MaintainedByDiffblue")
    void testPopulateVPasOneResponse() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        RetVal retVal = new RetVal();
        retVal.setMessage("Not all who wander are lost");
        retVal.setSuccessful("Successful");

        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        vPasOneResponse.setChangeEffDate("2020-03-01");
        vPasOneResponse.setErrorStatus("An error occurred");
        vPasOneResponse.setRetVal(retVal);

        // Act
        newRelicService.populateVPasOneResponse(vPasOneResponse);
    }

    /**
     * Test {@link NewRelicService#populatePacSuspendResponse(PacSuspendResponse)}.
     * <p>
     * Method under test: {@link NewRelicService#populatePacSuspendResponse(PacSuspendResponse)}
     */
    @Test
    @DisplayName("Test populatePacSuspendResponse(PacSuspendResponse)")
    @Tag("MaintainedByDiffblue")
    void testPopulatePacSuspendResponse() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        RetVal retVal = new RetVal();
        retVal.setMessage("Not all who wander are lost");
        retVal.setSuccessful("Successful");

        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        vPasOneResponse.setChangeEffDate("2020-03-01");
        vPasOneResponse.setErrorStatus("An error occurred");
        vPasOneResponse.setRetVal(retVal);

        PacSuspendResponse pacSuspendResponse = new PacSuspendResponse();
        pacSuspendResponse.setCode("Code");
        pacSuspendResponse.setConfirmId("42");
        pacSuspendResponse.setPlc("Plc");
        pacSuspendResponse.setPolicyNumber("42");
        pacSuspendResponse.setSuccessful(true);
        pacSuspendResponse.setVPasOneResponse(vPasOneResponse);
        pacSuspendResponse.setWorkItemId("42");

        // Act
        newRelicService.populatePacSuspendResponse(pacSuspendResponse);
    }

    /**
     * Test {@link NewRelicService#populateVPasOneTokenErrorResponse(VPASOneTokenResponse)}.
     * <p>
     * Method under test: {@link NewRelicService#populateVPasOneTokenErrorResponse(VPASOneTokenResponse)}
     */
    @Test
    @DisplayName("Test populateVPasOneTokenErrorResponse(VPASOneTokenResponse)")
    @Tag("MaintainedByDiffblue")
    void testPopulateVPasOneTokenErrorResponse() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        newRelicService
                .populateVPasOneTokenErrorResponse(new VPASOneTokenResponse(true, "An error occurred", "An error occurred"));
    }





    /**
     * Test {@link NewRelicService#populateResponse(DividendResponse)} with {@code DividendResponse}.
     * <ul>
     *   <li>Given {@link DividendRecord} (default constructor) CoverageId is {@code 42}.</li>
     * </ul>
     * <p>
     * Method under test: {@link NewRelicService#populateResponse(DividendResponse)}
     */
    @Test
    @DisplayName("Test populateResponse(DividendResponse) with 'DividendResponse'; given DividendRecord (default constructor) CoverageId is '42'")
    @Tag("MaintainedByDiffblue")
    void testPopulateResponseWithDividendResponse_givenDividendRecordCoverageIdIs42() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        DividendRecord dividendRecord = new DividendRecord();
        dividendRecord.setCoverageId("42");
        dividendRecord.setDividendAcctId("42");
        dividendRecord.setDividendAmounts("10");
        dividendRecord.setDividendTypes("Dividend Types");
        dividendRecord.setPlc("Plc");
        dividendRecord.setPolicyActiveStatus("Policy Active Status");
        dividendRecord.setPolicyCategory("Policy Category");
        dividendRecord.setPolicyNumber("42");
        dividendRecord
                .setPolicyUpdTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        dividendRecord.setPrdCoverageId("42");
        dividendRecord.setSystemId("42");
        dividendRecord.setUpdateTs(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));

        ArrayList<DividendRecord> results = new ArrayList<>();
        results.add(dividendRecord);

        DividendResponse response = new DividendResponse();
        response.setCode("Code");
        response.setErrorMessage("An error occurred");
        response.setSuccessful(true);
        response.setResults(results);

        // Act
        newRelicService.populateResponse(response);
    }

    /**
     * Test {@link NewRelicService#populateResponse(PacTransactionInfoResponse)} with {@code PacTransactionInfoResponse}.
     * <p>
     * Method under test: {@link NewRelicService#populateResponse(PacTransactionInfoResponse)}
     */
    @Test
    @DisplayName("Test populateResponse(PacTransactionInfoResponse) with 'PacTransactionInfoResponse'")
    @Tag("MaintainedByDiffblue")
    void testPopulateResponseWithPacTransactionInfoResponse() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBankAccountNumber("42");
        bankDetails.setBankAccountType("3");
        bankDetails.setBankName("Bank Name");
        bankDetails.setBankRoutingNumber("42");
        bankDetails.setDisplay("Display");
        bankDetails.setNameOnAccount("3");

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
        pacInfoResult.setPolicyNumber("42");
        pacInfoResult.setPydDetails(pydDetails);
        pacInfoResult.setRole("Role");
        pacInfoResult.setSourceSystem("Source System");
        pacInfoResult.setTokenId("42");
        pacInfoResult.setTransactionId("42");
        pacInfoResult.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");
        pacInfoResult.setWorkItemId("42");
        pacInfoResult.setPaymentDetails(null);

        ArrayList<PacInfoResult> results = new ArrayList<>();
        results.add(pacInfoResult);

        PacTransactionInfoResponse response = new PacTransactionInfoResponse();
        response.setCode("Code");
        response.setErrorMessage("An error occurred");
        response.setSuccessful(true);
        response.setResults(results);

        // Act
        newRelicService.populateResponse(response);
    }



    /**
     * Test {@link NewRelicService#populateErrorDetails(ApiError)} with {@code apiError}.
     * <p>
     * Method under test: {@link NewRelicService#populateErrorDetails(ApiError)}
     */
    @Test
    @DisplayName("Test populateErrorDetails(ApiError) with 'apiError'")
    @Tag("MaintainedByDiffblue")
    void testPopulateErrorDetailsWithApiError() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        LocalDateTime timestamp = LocalDate.of(1970, 1, 1).atStartOfDay();

        // Act
        newRelicService
                .populateErrorDetails(new ApiError(timestamp, HttpStatus.OK, "Not all who wander are lost", new ArrayList<>()));
    }





    @Test
    @DisplayName("populateErrorDetails: -> NewRelicAddCustomParameter")
    public void testPopulateErrorDetails_NewRelicAddCustomParameter() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(NewRelic.class);
            NewRelicService newRelicService = new NewRelicService();

            newRelicService.populateErrorDetails(((String) null));
            assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> newRelicService.populateErrorDetails((String) null));
        } finally {
            mockedStatic.close();
        }
    }

    @Test
    @DisplayName("populateVPasOneTokenErrorResponse: VPASOneTokenResponseIsError -> NewRelicAddCustomParameter")
    public void testPopulateVPasOneTokenErrorResponse_NewRelicAddCustomParameter() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(NewRelic.class);
            NewRelicService newRelicService = new NewRelicService();
            VPASOneTokenResponse vpasOneTokenResponseMock = mock(VPASOneTokenResponse.class);
            (when(vpasOneTokenResponseMock.isError())).thenReturn(false);
            (when(vpasOneTokenResponseMock.getErrorMessage())).thenReturn(((String) null));
            (when(vpasOneTokenResponseMock.getErrorCode())).thenReturn(((String) null));

            newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponseMock);
            assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponseMock));
        } finally {
            mockedStatic.close();
        }
    }


}
