package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.constants.ApimTokenizeConstants;
import com.jh.de.pacdetails.constants.VPasOneTokenizeConstants;
import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.response.APIMTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.util.Arrays;

import static com.jh.de.pacdetails.constants.JHConstants.*;

@Service
public class TokenServiceImpl implements TokenService{

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${apim.url}")
    private String apimTokenUrl;
    @Value("${apim.clientId}")
    private String apimClientId;
    @Value("${apim.clientSecret}")
    private String apimClientSecret;
    @Value("${apim.clientCredentials}")
    private String apimClientCredentials;
    @Value("${vpasOne.token.url}")
    private String vpasOneTokenUrl;
    @Value("${vpasOne.token.clientId}")
    private String vpasOneClientId;
    @Value("${vpasOne.token.clientSecret}")
    private String vpasOneClientSecret;
    @Value("${vpasOne.token.clientCredentials}")
    private String vpasOneClientCredentials;

    @Override
    public VPASOneTokenResponse getVPASOneToken(String policyNumber) {
        String requestBodyAuthDetails = null;
        try {
            requestBodyAuthDetails = VPasOneTokenizeConstants.CLIENT_ID + URLEncoder.encode(vpasOneClientId, VPasOneTokenizeConstants.UTF8) +
                    VPasOneTokenizeConstants.CLIENT_SECRET + URLEncoder.encode(vpasOneClientSecret, VPasOneTokenizeConstants.UTF8) +
                    VPasOneTokenizeConstants.GRANT_TYPE + vpasOneClientCredentials;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return webClientBuilder.build()
                .post()
                .uri(vpasOneTokenUrl)
                .header(VPasOneTokenizeConstants.CONTENT_TYPE,VPasOneTokenizeConstants.APPLICATION_URL_ENCODE)
                .body(Mono.just(requestBodyAuthDetails), String.class)
                .retrieve()
                .bodyToMono(VPASOneTokenResponse.class)
                .onErrorResume(WebClientException.class, ex -> Mono.just(new VPASOneTokenResponse(true,ex.getMessage(),"503")))
                .onErrorResume(WebClientResponseException.class, ex -> Mono.just(new VPASOneTokenResponse(true,ex.getMessage(),String.valueOf(ex.getStatusCode()))))
                .onErrorResume(ConnectException.class, ex -> Mono.just(new VPASOneTokenResponse(true,ex.getMessage(),"503")))
                .block();

    }

    public APIMTokenResponse getAPIMToken(String policyNumber){
        String requestBodyAuthDetails = null;
        try {
            requestBodyAuthDetails = ApimTokenizeConstants.CLIENT_ID + URLEncoder.encode(apimClientId, ApimTokenizeConstants.UTF8) +
                    ApimTokenizeConstants.CLIENT_SECRET + URLEncoder.encode(apimClientSecret, ApimTokenizeConstants.UTF8) +
                    ApimTokenizeConstants.GRANT_TYPE + apimClientCredentials;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return webClientBuilder.build()
                .post()
                .uri(apimTokenUrl)
                .header(ApimTokenizeConstants.CONTENT_TYPE,ApimTokenizeConstants.APPLICATION_URL_ENCODE)
                .body(Mono.just(requestBodyAuthDetails), String.class)
                .retrieve()
                .bodyToMono(APIMTokenResponse.class)
                .onErrorResume(WebClientException.class, ex ->{
                    throw getApiException(APIM_TOKENSERVICE_ERROR, ex.getMessage(),policyNumber);
                })
                .onErrorResume(WebClientResponseException.class, ex ->{
                    throw getApiException(APIM_TOKENSERVICE_ERROR, ex.getMessage(), policyNumber);
                })
                .onErrorResume(ConnectException.class, ex ->{
                    throw getApiException(APIM_TOKENSERVICE_ERROR, ex.getMessage(), policyNumber);
                })
                .block();
    }

    private ApiException getApiException(String message, String exMessage, String policyNumber) {
        return new ApiException.InternalServerException(message,
                Arrays.asList(POLICY_WITH + policyNumber + ERRORED_WITH_EX + exMessage));
    }

}
