package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.svc.DividendSvc;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/api/v1")
public class DividendController {
    @Autowired
    private DividendSvc dividendSvc;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("plc", "policyNumber", "dividendType", "dividendAccountType");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(value="/dividend")
    public ResponseEntity<DividendResponse> getDividendDetails(@ParameterObject DividendRequest dividendRequest) {
        DividendResponse response = dividendSvc.getDividendDetails(dividendRequest);
        return ResponseEntity.ok().body(response);
    }

}
