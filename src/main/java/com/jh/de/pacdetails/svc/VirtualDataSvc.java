package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacInfoResult;

import java.util.List;

public interface VirtualDataSvc {
    List<PacInfoResult> getPacInfoRecords(PacInfoRequest request);
    List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest request);
}
