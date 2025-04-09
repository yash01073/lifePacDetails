package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import com.jh.de.pacdetails.validate.PdcoRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.jh.de.pacdetails.constants.JHConstants.ADMIN_SYSTEM_2V;


@Component
public class ValidateAdminSystemId extends BaseValidation implements PacSuspendRequestValidate, PdcoRequestValidate {
    @Override
    public void validate(PacSuspendRequest pacSuspendRequest) {
        if(StringUtils.isBlank(pacSuspendRequest.getAdminSystemId())) {
            throw getBadRequestApiException(ADMIN_SYSTEM_ID_ERROR);
        }
    }

    @Override
    public void validate(PacInfoRequest request) {
        if (StringUtils.isBlank(request.getAdminSystem())) {
            request.setAdminSystem(ADMIN_SYSTEM_2V);
        }
    }
}
