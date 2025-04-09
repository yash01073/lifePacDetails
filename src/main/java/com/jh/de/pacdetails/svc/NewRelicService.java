package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.exception.ApiError;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.newrelic.api.agent.NewRelic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("NewRelicService")
public class NewRelicService {
    public static final String POLICY_NUMBER = "PolicyNumber";
    public void populateInputRequest(PacInfoRequest pacInfoRequest) {
        NewRelic.addCustomParameter(POLICY_NUMBER, pacInfoRequest.getPolicyNumber());
        NewRelic.addCustomParameter("PLC", pacInfoRequest.getPlc());
        NewRelic.addCustomParameter("Transaction ID", pacInfoRequest.getTransactionId());
        NewRelic.addCustomParameter("UUID", pacInfoRequest.getUUID());
        NewRelic.addCustomParameter("Call-Type", pacInfoRequest.getCallType());
        NewRelic.addCustomParameter("Business", pacInfoRequest.getBusiness());
    }

    public void populateResponse(PacTransactionInfoResponse response){
        NewRelic.addCustomParameter("SourceOfData", response.getCode());
        NewRelic.addCustomParameter("AdminSystem", response.getResults().get(0).getAdminSystemId());
        NewRelic.addCustomParameter("ResponsePLC", response.getResults().get(0).getPLC());
        if(response.getResults().get(0).getPaymentDetails() != null) {
            NewRelic.addCustomParameter("PremiumAmount", response.getResults().get(0).getPaymentDetails().getPremiumAmount());
            NewRelic.addCustomParameter("LoanAmount", response.getResults().get(0).getPaymentDetails().getLoanAmount());
            NewRelic.addCustomParameter("PaymentAmount", response.getResults().get(0).getPaymentDetails().getPaymentAmount());
            NewRelic.addCustomParameter("ModeOfFrequency", response.getResults().get(0).getPaymentDetails().getModeOfFrequency());
            NewRelic.addCustomParameter("DraftDueDate", response.getResults().get(0).getPaymentDetails().getDraftDueDate());
            NewRelic.addCustomParameter("ImmediatePremiumDraft", response.getResults().get(0).getPaymentDetails().getImmediatePremiumDraft());
        }
    }

    public void populateErrorDetails(String errorMessage) {
        NewRelic.addCustomParameter("Error", "Yes");
        NewRelic.addCustomParameter("Error Details",errorMessage);
    }

    public void populateErrorDetails(ApiError apiError) {
        NewRelic.addCustomParameter("Error", "Yes");
        NewRelic.addCustomParameter("Status",String.valueOf(apiError.getStatus()));
        NewRelic.addCustomParameter("Error message",apiError.getMessage());
        NewRelic.addCustomParameter("Error Details",String.valueOf(apiError.getErrors()));
    }

    public void populateInputRequest(DividendRequest dividendRequest) {
        NewRelic.addCustomParameter(POLICY_NUMBER, dividendRequest.getPolicyNumber());
        NewRelic.addCustomParameter("PLC", dividendRequest.getPlc());
    }

    public void populateResponse(DividendResponse response) {
        NewRelic.addCustomParameter("SourceOfData", response.getCode());
        NewRelic.addCustomParameter("SystemId", response.getResults().get(0).getSystemId());
        NewRelic.addCustomParameter("DividendTypes", response.getResults().get(0).getDividendTypes());
        NewRelic.addCustomParameter("DividendAcctId", response.getResults().get(0).getDividendAcctId());
        NewRelic.addCustomParameter("CoverageId", response.getResults().get(0).getCoverageId());
        NewRelic.addCustomParameter("PrdCoverageId", response.getResults().get(0).getPrdCoverageId());
        NewRelic.addCustomParameter("DividendAmounts", response.getResults().get(0).getDividendAmounts());
        NewRelic.addCustomParameter("PolicyActiveStatus", response.getResults().get(0).getPolicyActiveStatus());
        NewRelic.addCustomParameter("PolicyCategory", response.getResults().get(0).getPolicyCategory());
    }

    public void populateInputRequest(PacSuspendRequest pacSuspendRequest) {
        log.info("Pac Suspend request: "+pacSuspendRequest);
        NewRelic.addCustomParameter(POLICY_NUMBER, pacSuspendRequest.getPolicyNumber());
        NewRelic.addCustomParameter("PolicyAdminKey", pacSuspendRequest.getPolicyAdminKey());
        NewRelic.addCustomParameter("PLC", pacSuspendRequest.getPlc());
        NewRelic.addCustomParameter("ModeOfFrequency", pacSuspendRequest.getModeOfFrequency());
        NewRelic.addCustomParameter("AdminSystemId", pacSuspendRequest.getAdminSystemId());
        NewRelic.addCustomParameter("ApplicationId", pacSuspendRequest.getAppId());
        NewRelic.addCustomParameter("SourceSystem",pacSuspendRequest.getSourceSystem());
    }

    public void populateVPasOneResponse(VPasOneResponse vPasOneResponse){
        NewRelic.addCustomParameter("VPasOneSuccessful", vPasOneResponse.getRetVal().getSuccessful());
        NewRelic.addCustomParameter("VPasOneResponseMessage", vPasOneResponse.getRetVal().getMessage());
        NewRelic.addCustomParameter("VPasOneStatusCode",vPasOneResponse.getErrorStatus());
    }

    public void populatePacSuspendResponse(PacSuspendResponse pacSuspendResponse){
        log.info("Pac Suspend Response: "+pacSuspendResponse);
        NewRelic.addCustomParameter("ResponseCode", pacSuspendResponse.getCode());
        NewRelic.addCustomParameter("Successful", pacSuspendResponse.isSuccessful());
        NewRelic.addCustomParameter("WorkItemId", pacSuspendResponse.getWorkItemId());
    }

    public void populateVPasOneTokenErrorResponse(VPASOneTokenResponse vpasOneTokenResponse){
        NewRelic.addCustomParameter("VPasOneTokenError", vpasOneTokenResponse.isError());
        NewRelic.addCustomParameter("VPasOneTokenErrorMessage", vpasOneTokenResponse.getErrorMessage());
        NewRelic.addCustomParameter("VPasOneTokenErrorCode", vpasOneTokenResponse.getErrorCode());
    }
}
