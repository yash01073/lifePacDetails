package com.jh.de.pacdetails.validate.request;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.constants.JHConstants.DividendAccountTypes;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.DividendRequestValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValidateDividendAccountType extends BaseValidation implements DividendRequestValidate {
    @Override
    public void validate(DividendRequest dividendRequest) {
        if(StringUtils.isBlank(dividendRequest.getDividendAccountType())) {
            return;
        }
        if(JHConstants.ALL.equalsIgnoreCase(dividendRequest.getDividendAccountType())) {
            return;
        }
        List<String> dividendAccountTypes = Arrays.stream(dividendRequest.getDividendAccountType().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        if(!DividendAccountTypes.getDividendAccountTypes().containsAll(dividendAccountTypes)) {
            throw getBadRequestApiException(DIVIDEND_ACCT_TYPE_ERROR + dividendRequest.getDividendType());
        }
        dividendRequest.setDividendAccountTypes(dividendAccountTypes);
    }

}
