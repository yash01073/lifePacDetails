package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankDetails {
    @JsonProperty("NameOnAccount")
    private String NameOnAccount;

    @JsonProperty("BankName")
    private String BankName;

    @JsonProperty("BankAccountType")
    private String BankAccountType;

    @JsonProperty("BankAccountNumber")
    private String BankAccountNumber;

    @JsonProperty("BankRoutingNumber")
    private String BankRoutingNumber;

    @JsonProperty("display")
    private String display;
}
