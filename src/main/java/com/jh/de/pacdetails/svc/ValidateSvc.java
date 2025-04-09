package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;

public interface ValidateSvc {
    public void validateRequest(PacInfoRequest pacInfoRequest);

    public void validateResponse(TPolicyPacInfo billing);

    public void validateRequest(DividendRequest dividendRequest);

    public void validateRequest(PacSuspendRequest pacSuspendRequest);

    //public void validateResponse(VPasOneResponse vPasOneResponse);

    public void validateResponse(AwdCreateWorkItemResponse awdResponse);

}
