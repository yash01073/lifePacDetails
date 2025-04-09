package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.request.VPasOneRequest;
import com.jh.de.pacdetails.model.response.ConvertedPolicyResponse;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.ValidateSvc;
import com.newrelic.api.agent.NewRelic;
import org.springframework.beans.factory.annotation.Autowired;
import com.jh.de.pacdetails.svc.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.List;

import static com.jh.de.pacdetails.constants.JHConstants.*;

@Service
public class VPasOneServiceImpl implements VPasOneService{

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ValidateSvc validateSvc;
    @Autowired
    private NewRelicService newRelicService;
    @Value("${vpasOne.service.url}")
    private String vpasOneUrl;
    @Value("${vpasOne.service.env}")
    private String env;
    @Value("${convertedPolicy.url}")
    private String convertedPolicyUrl;

    public VPasOneResponse suspendPacForVPasOne(PacSuspendRequest pacSuspendRequest) {
        VPasOneRequest vPasOneRequest = new VPasOneRequest(
                "M", pacSuspendRequest.getModeOfFrequency());
        VPASOneTokenResponse vpasOneTokenResponse = tokenService.getVPASOneToken(pacSuspendRequest.getPolicyNumber());
        return getvPasOneResponse(pacSuspendRequest, vpasOneTokenResponse, vPasOneRequest);
    }

    private VPasOneResponse getvPasOneResponse(PacSuspendRequest pacSuspendRequest, VPASOneTokenResponse vpasOneTokenResponse, VPasOneRequest vPasOneRequest) {
        if(vpasOneTokenResponse.isError()){
            newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponse);
            return createErrorVpasOneResponse(vpasOneTokenResponse.getErrorMessage(), vpasOneTokenResponse.getErrorCode());
        }else {
            String vPasOneToken = vpasOneTokenResponse.getAccessToken();
            String vPasOnePolicyNumber = getVPasOnePolicy(pacSuspendRequest.getPolicyNumber(), pacSuspendRequest.getPlc()).getConvertedPolicy();
            NewRelic.addCustomParameter("VPasOnePolicyNumber", vPasOnePolicyNumber);
            return updatePayment(vPasOneRequest, vPasOneToken, vPasOnePolicyNumber);
        }
    }

    private VPasOneResponse updatePayment(VPasOneRequest vPasOneRequest, String token, String policyNumber) {
        String url = vpasOneUrl+env+"/payments/"+policyNumber+"/updatePayment";
        return webClientBuilder.build()
                .post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .body(Mono.just(vPasOneRequest), VPasOneRequest.class)
                .retrieve()
                .bodyToMono(VPasOneResponse.class)
                .onErrorResume(WebClientException.class, ex -> Mono.just(createErrorVpasOneResponse(ex.getMessage(),"503")))
                .onErrorResume(WebClientResponseException.class, ex -> Mono.just(createErrorVpasOneResponse(ex.getMessage(),String.valueOf(ex.getStatusCode()))))
                .onErrorResume(ConnectException.class, ex -> Mono.just(createErrorVpasOneResponse(ex.getMessage(),"503")))
                .block();
    }

    private ConvertedPolicyResponse getVPasOnePolicy(String policyNumber, String plc) {
        ConvertedPolicyResponse convertedPolicyResponse;
        String url = convertedPolicyUrl+policyNumber+"&PLCode="+plc;
        convertedPolicyResponse = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ConvertedPolicyResponse.class)
                .onErrorResume(WebClientException.class, ex -> {
                    throw getApiException(CONVERTED_POLICY_ERROR, ex.getMessage(), policyNumber);
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    throw getApiException(CONVERTED_POLICY_ERROR, ex.getMessage(), policyNumber);
                })
                .onErrorResume(ConnectException.class, ex -> {
                    throw getApiException(CONVERTED_POLICY_ERROR, ex.getMessage(), policyNumber);
                })
                .block();
        if(convertedPolicyResponse!=null){
            return convertedPolicyResponse;
        }else{
            throw getApiException(CONVERTED_POLICY_ERROR, "ConvertedPolicyAPI Null Response", policyNumber);
        }

    }

    private ApiException getApiException(String message, String exMessage, String policyNumber) {
        return new ApiException.InternalServerException(message,
                List.of(POLICY_WITH + policyNumber + ERRORED_WITH_EX + exMessage));
    }

    public VPasOneResponse createErrorVpasOneResponse(String errorMessage,String errorCode){
        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        RetVal retVal = new RetVal();
        retVal.setSuccessful("NO");
        retVal.setMessage(errorMessage);
        vPasOneResponse.setRetVal(retVal);
        vPasOneResponse.setErrorStatus(errorCode);
        return vPasOneResponse;
    }
}
