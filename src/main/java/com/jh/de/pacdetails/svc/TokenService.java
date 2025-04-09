package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.response.APIMTokenResponse;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    APIMTokenResponse getAPIMToken(String policyNumber);

    VPASOneTokenResponse getVPASOneToken(String policyNumber);


}
