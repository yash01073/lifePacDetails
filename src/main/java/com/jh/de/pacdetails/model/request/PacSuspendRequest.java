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
public class PacSuspendRequest {
    @JsonProperty("Plc")
    @NotBlank
    private String plc;

    @JsonProperty("PolicyNumber")
    @NotBlank
    private String policyNumber;

    @JsonProperty("PolicyAdminKey")
    private String policyAdminKey;

    @JsonProperty("ModeOfFrequency")
    @NotBlank
    private String modeOfFrequency;

    @JsonProperty("AdminSystemId")
    @NotBlank
    private String adminSystemId;

    @JsonProperty("ApplicationId")
    private String appId;

    @JsonProperty("SourceSystem")
    private String sourceSystem;

}
