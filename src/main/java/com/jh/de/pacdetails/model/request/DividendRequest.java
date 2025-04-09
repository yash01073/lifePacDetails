package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DividendRequest {
    @JsonProperty("plc")
    @NotBlank
    private String plc;

    @JsonProperty("PolicyNumber")
    @NotBlank
    private String policyNumber;

    @JsonProperty(value = "DividendType")
    private String dividendType;

    @JsonProperty(value = "DividendAccountType")
    private String dividendAccountType;

    private List<String> dividendTypes;

    private List<String> dividendAccountTypes;

}
