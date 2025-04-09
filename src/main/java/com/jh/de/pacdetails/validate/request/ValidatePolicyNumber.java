package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import com.jh.de.pacdetails.validate.PacInfoRequestValidate;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import com.jh.de.pacdetails.validate.PdcoRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidatePolicyNumber extends BaseValidation implements PacInfoRequestValidate,
        DividendRequestValidate, PacSuspendRequestValidate, PdcoRequestValidate {

    @Override
    public void validate(PacInfoRequest pacInfoRequest) {
        if(StringUtils.isBlank(pacInfoRequest.getPolicyNumber())) {
            throw getBadRequestApiException(POLICY_NUMBER_ERROR);
        }
    }

    @Override
    public void validate(DividendRequest dividendRequest) {
        dividendRequest.setPolicyNumber(validatePolicyNumber(dividendRequest.getPolicyNumber()));
    }

    private String validatePolicyNumber(String policyNumber) {
        if(StringUtils.isBlank(policyNumber)) {
            throw getBadRequestApiException(POLICY_NUMBER_ERROR);
        }
        return JHConstants.modifyStr(policyNumber, '0', 9);
    }

    @Override
    public void validate(PacSuspendRequest pacSuspendRequest) {
        if(StringUtils.isBlank(pacSuspendRequest.getPolicyNumber())) {
            throw getBadRequestApiException(POLICY_NUMBER_ERROR);
        }
    }
}
