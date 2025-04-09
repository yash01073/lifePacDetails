package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PacTransactionInfoResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("Results")
    private List<PacInfoResult> results = new ArrayList<>();

    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;

    @JsonProperty("ErrorMessage")
    private String errorMessage;
}
