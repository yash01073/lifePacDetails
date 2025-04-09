package com.jh.de.pacdetails.model.entity.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TPOLICY_PAC_INFO")
public class TPolicyPacInfo {

    @JsonProperty("Plc")
    @Column(name = "Plc")
    private String Plc;

    @Id
    @JsonProperty("SystemId")
    @Column(name = "SystemId")
    private String SystemId;

    @JsonProperty("PolicyNumber")
    @Column(name = "PolicyNumber")
    private String PolicyNumber;

    @JsonProperty("PaymentMethod")
    @Column(name = "PaymentMethod")
    private String PaymentMethod;

    @JsonProperty("PacFrequency")
    @Column(name = "PacFrequency")
    private String PacFrequency;

    @JsonProperty("PacDraftDate")
    @Column(name = "PacDraftDate")
    private String PacDraftDate;

    @JsonProperty("PacDraftAmt")
    @Column(name = "PacDraftAmt")
    private String PacDraftAmt;

    @JsonProperty("OutstandingLoanAmount")
    @Column(name = "OutstandingLoanAmount")
    private String OutstandingLoanAmount;

    @JsonProperty("MaxLoanAvilable")
    @Column(name = "MaxLoanAvilable")
    private String MaxLoanAvilable;

    @JsonProperty("CreatedTs")
    @Column(name = "CreatedTs")
    private Date CreatedTs;

    @JsonProperty("UpdatedTs")
    @Column(name = "UpdatedTs")
    private Date UpdatedTs;

    @JsonProperty("ModalPremAmount")
    @Column(name = "ModalPremAmount")
    private String ModalPremAmount;

    @JsonProperty("ModalLoanAmount")
    @Column(name = "ModalLoanAmount")
    private String ModalLoanAmount;

}



