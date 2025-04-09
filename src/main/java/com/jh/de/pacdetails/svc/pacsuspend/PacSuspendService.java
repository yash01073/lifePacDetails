package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import org.springframework.stereotype.Component;

@Component
public interface PacSuspendService {
    PacSuspendResponse suspendPacSetUp(PacSuspendRequest pacSuspendRequest);

}
