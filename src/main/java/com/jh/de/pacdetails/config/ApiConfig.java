package com.jh.de.pacdetails.config;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ApiConfig {
    @Bean
    public Map<String, TPolicyPacInfoDividend> getDividendResponseMap() {
        return new HashMap<>();
    }

    @Bean
    public Map<String, PacInfoResult> getPacResponseMap() {
        return new HashMap<>();
    }
}
