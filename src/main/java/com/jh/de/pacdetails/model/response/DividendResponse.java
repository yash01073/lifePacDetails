package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DividendResponse {

    @JsonProperty("code")
    private String code;

    @JsonProperty("Results")
    private List<DividendRecord> results = new ArrayList<>();

    @JsonProperty("IsSuccessful")
    private boolean isSuccessful;

    @JsonProperty("ErrorMessage")
    private String errorMessage;
}
