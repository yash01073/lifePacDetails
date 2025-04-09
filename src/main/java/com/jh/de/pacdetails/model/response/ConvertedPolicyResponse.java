package com.jh.de.pacdetails.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConvertedPolicyResponse {

    private String code;
    private String message;
    private String convertedPolicy;
}
