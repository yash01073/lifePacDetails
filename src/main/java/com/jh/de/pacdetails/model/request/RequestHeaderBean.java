package com.jh.de.pacdetails.model.request;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@Component
@RequestScope
public class RequestHeaderBean {
    private Boolean isVirtual;
}
