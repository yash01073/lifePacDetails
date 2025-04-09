package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PacInfoRequest {
    @JsonProperty("plc")
    private String plc;

    @JsonProperty(value = "PolicyNumber")
    @NotBlank
    private String policyNumber;

    @JsonProperty("UUID")
    private String UUID;

    @JsonProperty("TransactionId")
    private String transactionId;

    @JsonProperty("CallType")
    private String callType;

    @JsonProperty("Business") // line of business or business unit id (BU_ID)
    private String business;

    @JsonProperty("AdminSystem")
    private String adminSystem;

}
