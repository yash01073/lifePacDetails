package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;
import java.util.List;
import java.util.Arrays;

import static com.jh.de.pacdetails.constants.JHConstants.*;

@Slf4j
@Service()
public class PDCOPaymentDelegatorServiceImpl implements PDCOPaymentDelegatorService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${pdcoPaymentService.url}")
    private String pdcoPaymentServiceUrl;

    public List<PacInfoResult> getPacInfoRecords(PacInfoRequest request) {
        log.info("calling PDCO pac service: "+ pdcoPaymentServiceUrl);
        return webClientBuilder.build()
                .get()
                .uri(pdcoPaymentServiceUrl, UriBuilder -> UriBuilder
                        .queryParam("policyNumber", request.getPolicyNumber())
                        .queryParam("plc", request.getPlc())
                        .queryParam("UUID", request.getUUID())
                        .queryParam("transactionId", request.getTransactionId())
                        .queryParam("callType", request.getCallType())
                        .queryParam("business", request.getBusiness())
                        .queryParam("adminSystem", request.getAdminSystem())
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PacInfoResult>>() {})
                .onErrorResume(WebClientResponseException.class, ex-> {
                    throw handleWebClientResponseError(ex,request);
                })
                .onErrorResume(WebClientException.class, ex -> {
                    throw getApiException(PDCO_SERVICE_ERROR,
                            ex.getMessage(),
                            request.getPolicyNumber(),
                            request.getTransactionId());
                })
                .onErrorResume(ConnectException.class, ex -> {
                    throw getApiException(PDCO_SERVICE_ERROR,
                            ex.getMessage(),
                            request.getPolicyNumber(),
                            request.getTransactionId());
                })
                .block();
    }

    private ApiException getApiException(String message, String exMessage, String policyNumber, String transactionId) {
        return new ApiException.InternalServerException(message,
                Arrays.asList(POLICY_WITH + policyNumber, TXN_ID_WITH + transactionId , ERRORED_WITH_EX + exMessage));
    }
    private ApiException handleWebClientResponseError(WebClientResponseException ex, PacInfoRequest request) {
        if (ex.getStatusCode().value() == 404) {
            return new ApiException.NotFoundException(JHConstants.PAC_NOT_FOUND, Arrays.asList(JHConstants.PAC_NOT_EXIST+ POLICY +request.getPolicyNumber()+ AND_PLC +request.getPlc()));
        }
        return new ApiException.InternalServerException(PDCO_SERVICE_ERROR,
                Arrays.asList(POLICY_WITH + request.getPolicyNumber(), TXN_ID_WITH + request.getTransactionId() , ERRORED_WITH_EX + ex.getMessage()));
    }

}
