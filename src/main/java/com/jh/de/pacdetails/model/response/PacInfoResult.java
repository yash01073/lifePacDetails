package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PacInfoResult {

    @JsonIgnore
    private Long id;

    @JsonProperty("UUID")
    private String UUID;

    @JsonProperty("TransactionId")
    private String TransactionId;

    @JsonProperty("TokenId")
    private String TokenId;

    @JsonProperty("PolicyNumber")
    private String PolicyNumber;

    @JsonProperty("PLC")
    private String PLC;

    @JsonProperty("AdminSystemId")
    private String AdminSystemId;

    @JsonProperty("ApplicationId")
    private String ApplicationId;

    @JsonProperty("SourceSystem")
    private String SourceSystem;

    @JsonProperty("Role")
    private String Role;

    @JsonProperty("WorkItemId")
    private String WorkItemId;

    @JsonProperty("Error")
    private String Error;

    @JsonProperty("ErrorDetails")
    private String ErrorDetails;

    @JsonProperty("CreatedTime")
    private Date CreatedTime;

    @JsonProperty("PaymentDetails")
    private PaymentDetails paymentDetails;

    @JsonProperty("BankDetails")
    private BankDetails bankDetails;

    @JsonProperty("PYDDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PYDDetails pydDetails;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

}
