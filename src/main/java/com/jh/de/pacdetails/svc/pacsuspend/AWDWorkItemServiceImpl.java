package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.svc.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.ConnectException;
import java.util.List;

import static com.jh.de.pacdetails.constants.JHConstants.ERRORED_WITH_EX;
import static com.jh.de.pacdetails.constants.JHConstants.POLICY_WITH;
import static com.jh.de.pacdetails.constants.JHConstants.AWD_SERVICE_ERROR;

@Service
public class AWDWorkItemServiceImpl implements AWDWorkItemService{

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private TokenService tokenService;

    @Value("${awdRest.url}")
    private String Url;

    @Value("${awdRest.ocpApimSubscriptionKey}")
    private String ocpApimSubscriptionKey;


    @Override
    public AwdCreateWorkItemResponse createWorkItem(AwdRequest awdRequest, String policyNumber) {
        String token = tokenService.getAPIMToken(policyNumber).getAccessToken();
        return webClientBuilder.build()
                .post()
                .uri(Url)
                .header("Ocp-Apim-Subscription-Key",ocpApimSubscriptionKey)
                .header("Ocp-Apim-Trace","True")
                .header("Authorization", "Bearer " + token)
                .body(Mono.just(awdRequest), AwdRequest.class)
                .retrieve()
                .bodyToMono(AwdCreateWorkItemResponse.class)
                .onErrorResume(WebClientException.class, ex ->{
                    throw getApiException(ex.getMessage(),policyNumber);
                })
                .onErrorResume(WebClientResponseException.class, ex ->{
                    throw getApiException(ex.getMessage(),policyNumber);
                })
                .onErrorResume(ConnectException.class, ex ->{
                    throw getApiException(ex.getMessage(),policyNumber);
                })
                .block();
    }

    private ApiException getApiException(String message, String policyNumber) {
        return new ApiException(AWD_SERVICE_ERROR,
                List.of(POLICY_WITH + policyNumber + ERRORED_WITH_EX + message));
    }
}
