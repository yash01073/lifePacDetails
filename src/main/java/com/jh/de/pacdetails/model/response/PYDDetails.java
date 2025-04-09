package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PYDDetails {
    @JsonProperty("PydDate")
    private String pydDate;

    @JsonProperty("Backdating")
    private String backdating;
}

/*
    PaymentPlanOption:
        All Premium Payments (including initial premium), Subsequent Premiums (Initial by check)
        All Premium Payments (including TIA) *Please note, John Hancock will not draft until the policy is issued.
        Monthly, Quarterly, Semi-Annual, Annual

    BankInformationAvailable: indicator to know if the bank information is available or not - Day 0 scenario

    PydDate: Backdating date if applicable.
*/