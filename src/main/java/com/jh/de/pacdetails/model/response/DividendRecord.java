package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DividendRecord {

    @JsonProperty("SystemId")
    private String SystemId;

    @JsonProperty("PolicyNumber")
    private String PolicyNumber;

    @JsonProperty("Plc")
    private String Plc;

    @JsonProperty("DividendTypes")
    private String DividendTypes;

    @JsonProperty("DividendAcctId")
    private String DividendAcctId;

    @JsonProperty("CoverageId")
    private String CoverageId;

    @JsonProperty("PrdCoverageId")
    private String PrdCoverageId;

    @JsonProperty("DividendAmounts")
    private String DividendAmounts;

    @JsonProperty("UpdateTs")
    private Date UpdateTs;

    @JsonProperty("PolicyActiveStatus")
    private String PolicyActiveStatus;

    @JsonProperty("PolicyCategory")
    private String PolicyCategory;

    @JsonProperty("PolicyUpdTs")
    private Date PolicyUpdTs;
}
