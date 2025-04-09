package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.response.DividendResponse;
import org.springframework.util.CollectionUtils;

public interface DividendSvc {
    DividendResponse getDividendDetails(DividendRequest dividendRequest);

    default String getErrorMessage(DividendRequest dividendRequest) {
        StringBuilder sb = new StringBuilder("Dividend info does not exist for policy: ");
        sb.append(dividendRequest.getPolicyNumber()).append(" and PLC: ").append(dividendRequest.getPlc());
        if(!CollectionUtils.isEmpty(dividendRequest.getDividendTypes())) {
            sb.append(" and dividend type: ").append(dividendRequest.getDividendTypes());
        }
        if(!CollectionUtils.isEmpty(dividendRequest.getDividendAccountTypes())) {
            sb.append(" and dividend account type: ").append(dividendRequest.getDividendAccountTypes());
        }
        return sb.toString();
    }
}
