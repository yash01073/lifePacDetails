package com.jh.de.pacdetails.validate;

import com.jh.de.pacdetails.exception.ApiException;
import java.util.Arrays;

public abstract class BaseValidation {

    protected static final String POLICY_NUMBER_ERROR = "Required request parameter 'policyNumber' is not present";
    protected static final String PLC_ERROR = "Required request parameter 'plc' is not present";
    protected static final String PLC_DIVIDEND_ERROR = "Pay with dividends is not allowed for the plc: ";
    protected static final String DIVIDEND_TYPE_ERROR = "Invalid dividend types: ";
    protected static final String DIVIDEND_ACCT_TYPE_ERROR = "Invalid dividend account types: ";
    protected static final String REQUEST_ERROR = "Please check request";
    protected static final String ADMIN_SYSTEM_ID_ERROR = "Required request parameter 'AdminSystemId' is not present";
    protected static final String INVALID_ADMIN_SYSTEM_ID_ERROR = "PAC Suspend not available for admin system: ";
    protected static final String MODE_OF_FREQUENCY_ERROR = "Required request parameter 'ModeOfFrequency' is not present";
    protected static final String INVALID_MODE_OF_FREQUENCY_ERROR = "Invalid mode of frequency value";
    protected static final String UUID_ERROR = "Required request parameter 'UUID' is not present";
    protected static final String TXN_ID_ERROR = "Required request parameter 'TransactionId' is not present";
    protected ApiException.BadRequestException getBadRequestApiException(String message) {
        return new ApiException.BadRequestException(REQUEST_ERROR, Arrays.asList(message));
    }
}
