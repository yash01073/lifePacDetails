package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoRepository;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;
import com.jh.de.pacdetails.svc.NewRelicService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@Slf4j
@RequestMapping(value = "/health")
@RestController
public class HealthController {

    @Autowired
    private NewRelicService newRelicService;
    @Autowired
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;
    @Lazy
    @Autowired
    private TPolicyPacInfoRepository tPolicyPacInfoRepository;

    @GetMapping
    public String healthEndpoint() {
        try {
            lifeRecurringPaymentTransactionRepository.findTransactionByPolicyNumberAndPlc("94581691","8");
            tPolicyPacInfoRepository.findTransactionByPolicyNumber("094581691");
        } catch(Throwable ex) {
            newRelicService.populateErrorDetails(ex.getMessage());
            throw ex;
        }
        return "Healthy";
    }
}
