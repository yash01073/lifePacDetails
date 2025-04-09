package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import org.springframework.stereotype.Component;

@Component
public interface AWDWorkItemService {

    AwdCreateWorkItemResponse createWorkItem(AwdRequest awdRequest, String policyNumber);
}
