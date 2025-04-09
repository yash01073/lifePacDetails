package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.constants.JHConstants.DividendTypes;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidateDividendType extends BaseValidation implements DividendRequestValidate {
    @Override
    public void validate(DividendRequest dividendRequest) {
        if(StringUtils.isBlank(dividendRequest.getDividendType())) {
            return;
        }
        List<String> dividendTypes = Arrays.stream(dividendRequest.getDividendType().split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        if(!DividendTypes.getDividendTypes().containsAll(dividendTypes)) {
            throw getBadRequestApiException(DIVIDEND_TYPE_ERROR + dividendRequest.getDividendType());
        }
        dividendRequest.setDividendTypes(dividendTypes);
    }

}
