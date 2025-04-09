package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class VPasOneRequest {

    @JsonProperty("paymentForm")
    private String paymentForm;

    @JsonProperty("paymentMode")
    private String paymentMode;

}
