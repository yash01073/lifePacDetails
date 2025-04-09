package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import java.util.List;

public interface PDCOPaymentDelegatorService {
    public List<PacInfoResult> getPacInfoRecords(PacInfoRequest request);
}
