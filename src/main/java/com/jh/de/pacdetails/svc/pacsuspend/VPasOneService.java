package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import org.springframework.stereotype.Component;
@Component
public interface VPasOneService {
     VPasOneResponse suspendPacForVPasOne(PacSuspendRequest pacSuspendRequest);

     VPasOneResponse createErrorVpasOneResponse(String errorMessage,String errorCode);

}
