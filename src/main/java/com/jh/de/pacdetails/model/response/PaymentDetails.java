package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDetails {
    @JsonProperty("PremiumAmount")
    private String PremiumAmount;

    @JsonProperty("LoanAmount")
    private String LoanAmount;

    @JsonProperty("PaymentAmount")
    private String PaymentAmount;

    @JsonProperty("ImmediatePremiumDraft")
    private String ImmediatePremiumDraft;

    @JsonProperty("ImmediateLoanDraft")
    private String ImmediateLoanDraft;

    @JsonProperty("ModeOfFrequency")
    private String ModeOfFrequency;

    @JsonProperty("PremiumDueDate")
    private String PremiumDueDate;

    @JsonProperty("DraftDueDate")
    private int DraftDueDate=0;

}
