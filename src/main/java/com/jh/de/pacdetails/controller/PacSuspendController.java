package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.svc.pacsuspend.PacSuspendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/v1")
public class PacSuspendController {

    @Autowired
    private PacSuspendService pacSuspendService;

    @PostMapping(value="/suspend")
    public ResponseEntity<PacSuspendResponse> pacSuspend(@RequestBody PacSuspendRequest request) {
        PacSuspendResponse pacSuspendResponse = pacSuspendService.suspendPacSetUp(request);
        return ResponseEntity.ok().body(pacSuspendResponse);
    }

}
