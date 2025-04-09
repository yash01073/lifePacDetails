package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.constants.JHConstants.ProductLineCodes;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import com.jh.de.pacdetails.validate.PacInfoRequestValidate;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ValidateProductLineCode extends BaseValidation implements DividendRequestValidate, PacSuspendRequestValidate {
    @Override
    public void validate(DividendRequest dividendRequest) {
        if(StringUtils.isBlank(dividendRequest.getPlc())) {
            throw getBadRequestApiException(PLC_ERROR);
        }
        String mPlc = JHConstants.modifyStr(dividendRequest.getPlc(), '0', 3);
        if(ProductLineCodes.getProductLineCodesByDividend().contains(mPlc)) {
            dividendRequest.setPlc(mPlc);
        } else {
            throw getBadRequestApiException(PLC_DIVIDEND_ERROR);
        }
    }

    @Override
    public void validate(PacSuspendRequest pacSuspendRequest) {
        if(StringUtils.isBlank(pacSuspendRequest.getPlc())) {
            throw getBadRequestApiException(PLC_ERROR);
        }
    }
}
