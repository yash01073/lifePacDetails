package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import com.jh.de.pacdetails.svc.PacTransactionSvc;
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
public class PacDetailsController {
    @Autowired
    private PacTransactionSvc pacTransactionSvc;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("plc", "policyNumber", "UUID", "transactionId", "callType", "business", "adminSystem");
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(value="/pacdetails")
    public ResponseEntity<PacTransactionInfoResponse> getPacTransactionDetails(@ParameterObject PacInfoRequest pacInfoRequest) {
        PacTransactionInfoResponse response = pacTransactionSvc.getPacTransactionDetails(pacInfoRequest);
        return ResponseEntity.ok().body(response);
    }

}
