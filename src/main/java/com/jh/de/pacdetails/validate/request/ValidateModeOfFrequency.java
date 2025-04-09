package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.PacSuspendRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


@Component
public class ValidateModeOfFrequency extends BaseValidation implements PacSuspendRequestValidate {
    @Override
    public void validate(PacSuspendRequest pacSuspendRequest) {
        if(StringUtils.isBlank(pacSuspendRequest.getModeOfFrequency())) {
            throw getBadRequestApiException(MODE_OF_FREQUENCY_ERROR);
        }
        switch (pacSuspendRequest.getModeOfFrequency()){
            case "Annually":
                pacSuspendRequest.setModeOfFrequency("AN");
                break;
            case "Semi-Annually":
            case "Semi-annually":
                pacSuspendRequest.setModeOfFrequency("SA");
                break;
            case "Quarterly":
                pacSuspendRequest.setModeOfFrequency("QU");
                break;
            default:
                throw getBadRequestApiException(INVALID_MODE_OF_FREQUENCY_ERROR);
        }
    }

}
