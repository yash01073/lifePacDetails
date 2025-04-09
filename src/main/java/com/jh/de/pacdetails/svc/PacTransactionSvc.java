package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;

public interface PacTransactionSvc {
    PacTransactionInfoResponse getPacTransactionDetails(PacInfoRequest pacInfoRequest);

    default PacTransactionInfoResponse getPacTransactionInfoResponse(String tgtSource) {
        PacTransactionInfoResponse response = new PacTransactionInfoResponse();
        response.setCode(tgtSource);
        response.setSuccessful(true);
        response.setErrorMessage(null);
        return response;
    }
}
