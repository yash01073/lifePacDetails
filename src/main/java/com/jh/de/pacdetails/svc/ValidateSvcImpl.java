package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.validate.BillingPacInfoResponseValidate;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import com.jh.de.pacdetails.validate.PdcoRequestValidate;
import com.jh.de.pacdetails.validate.PacInfoRequestValidate;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.jh.de.pacdetails.constants.JHConstants.PDCO;

@Service("ValidateSvc")
public class ValidateSvcImpl implements ValidateSvc {

    private List<PacInfoRequestValidate> pacInfoRequestValidateList;

    private List<BillingPacInfoResponseValidate> billingPacInfoResponseValidateList;

    private List<DividendRequestValidate> dividendRequestValidateList;

    private List<PacSuspendRequestValidate> pacSuspendRequestValidateList;

    private List<PdcoRequestValidate> pdcoRequestValidateList;

    @Autowired
    public ValidateSvcImpl(List<PacInfoRequestValidate> pacInfoRequestValidateList,
                           List<BillingPacInfoResponseValidate> billingPacInfoResponseValidateList,
                           List<DividendRequestValidate> dividendRequestValidateList,
                           List<PacSuspendRequestValidate> pacSuspendRequestValidateList,
                           List<PdcoRequestValidate> pdcoRequestValidateList) {
        this.pacInfoRequestValidateList = pacInfoRequestValidateList;
        this.billingPacInfoResponseValidateList = billingPacInfoResponseValidateList;
        this.dividendRequestValidateList = dividendRequestValidateList;
        this.pacSuspendRequestValidateList = pacSuspendRequestValidateList;
        this.pdcoRequestValidateList = pdcoRequestValidateList;
    }

    @Override
    public void validateRequest(PacInfoRequest pacInfoRequest) {
        if(PDCO.equalsIgnoreCase(pacInfoRequest.getCallType())) {
            for(PdcoRequestValidate v: pdcoRequestValidateList) {
                v.validate(pacInfoRequest);
            }
        } else {
            for(PacInfoRequestValidate v: pacInfoRequestValidateList) {
                v.validate(pacInfoRequest);
            }
        }
    }

    @Override
    public void validateRequest(DividendRequest dividendRequest) {
        for(DividendRequestValidate v: dividendRequestValidateList) {
            v.validate(dividendRequest);
        }
    }

    @Override
    public void validateRequest(PacSuspendRequest pacSuspendRequest) {
        for(PacSuspendRequestValidate v: pacSuspendRequestValidateList) {
            v.validate(pacSuspendRequest);
        }
    }

    @Override
    public void validateResponse(TPolicyPacInfo billing) {
        List<String> errors = new ArrayList<>();
        for(BillingPacInfoResponseValidate v: billingPacInfoResponseValidateList) {
            String errorMessage = v.validate(billing);
            if(StringUtils.isNotBlank(errorMessage)) {
                errors.add(errorMessage);
            }
        }
        if(CollectionUtils.isNotEmpty(errors)) {
            throw new ApiException.BadResponseException("Missing PAC information from Billing", errors);
        }
    }

    @Override
    public void validateResponse(AwdCreateWorkItemResponse awdResponse) {
        if(awdResponse == null
                || awdResponse.getDetails() == null
                || awdResponse.getDetails().getList().isEmpty()
                || awdResponse.getDetails().getList().get(0).getInstance() == null) {
            throw new ApiException.BadResponseException("AWD response not found.");
        }
    }

    /*@Override
    public void validateResponse(VPasOneResponse vPasOneResponse) {
        if(!("YES".equalsIgnoreCase(vPasOneResponse.getRetVal().getSuccessful())
                && "API CALL COMPLETED".equalsIgnoreCase(vPasOneResponse.getRetVal().getMessage()))){
            List<String> errors = new ArrayList<>();
            errors.add(vPasOneResponse.getRetVal().getMessage());
            throw new ApiException.BadVPasOneResponseException("VPasOne API Error", errors);
        }
    }*/

}
