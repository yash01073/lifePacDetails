package com.jh.de.pacdetails.model.entity.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import java.util.Date;

@Getter
@Setter
@Entity
@IdClass(DividendKey.class)
@Table(name = "TPOLICY_PAC_INFO_DIVIDEND")
public class TPolicyPacInfoDividend {

    @JsonProperty("SystemId")
    @Column(name = "SystemId")
    private String SystemId;

    @Id
    @JsonProperty("PolicyNumber")
    @Column(name = "PolicyNumber")
    private String PolicyNumber;

    @Id
    @JsonProperty("Plc")
    @Column(name = "Plc")
    private String Plc;

    @Id
    @JsonProperty("DividendTypes")
    @Column(name = "DividendTypes")
    private String DividendTypes;

    @Id
    @JsonProperty("DividendAcctId")
    @Column(name = "DividendAcctId")
    private String DividendAcctId;

    @Id
    @JsonProperty("Coverageid")
    @Column(name = "Coverageid")
    private String Coverageid;

    @Id
    @JsonProperty("Prdcoverageid")
    @Column(name = "Prdcoverageid")
    private String Prdcoverageid;

    @JsonProperty("DividendAmounts")
    @Column(name = "DividendAmounts")
    private String DividendAmounts;

    @JsonProperty("UpdateTs")
    @Column(name = "UpdateTs")
    private Date UpdateTs;

    @JsonProperty("PolicyActiveStatus")
    @Column(name = "PolicyActiveStatus")
    private String PolicyActiveStatus;

    @JsonProperty("PolicyCategory")
    @Column(name = "PolicyCategory")
    private String PolicyCategory;

    @JsonProperty("PolicyUpdTs")
    @Column(name = "PolicyUpdTs")
    private Date PolicyUpdTs;

}



