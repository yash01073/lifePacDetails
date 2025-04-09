package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.PdcoRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import static com.jh.de.pacdetails.constants.JHConstants.LIFE;


@Component
public class ValidateBusiness extends BaseValidation implements PdcoRequestValidate {
    @Override
    public void validate(PacInfoRequest request) {
        if (StringUtils.isBlank(request.getBusiness())) {
            request.setBusiness(LIFE);
        }
    }

}
