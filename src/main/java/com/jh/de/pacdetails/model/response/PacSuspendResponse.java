package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PacSuspendResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("ConfirmationId")
    private String confirmId;

    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;

    @JsonProperty("plc")
    private String plc;

    @JsonProperty("PolicyNumber")
    private String policyNumber;

    @JsonProperty("AwdWorkItemId")
    private String workItemId;

    @JsonProperty("VPasOneResponse")
    private VPasOneResponse vPasOneResponse;

}
