package com.jh.de.pacdetails.model.entity.ptr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "life_recurring_payment_transaction")
public class LifeRecurringPaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @JsonProperty("UUID")
    @Column(name = "UUID")
    private String UUID;

    @JsonProperty("TransactionId")
    @Column(name = "TransactionId")
    private String TransactionId;

    @JsonProperty("TokenId")
    @Column(name = "TokenId")
    private String TokenId;

    @JsonProperty("PolicyNumber")
    @Column(name = "PolicyNumber")
    private String PolicyNumber;

    @JsonProperty("PLC")
    private String PLC;

    @JsonProperty("AdminSystemId")
    @Column(name = "AdminSystemId")
    private String AdminSystemId;

    @JsonProperty("ModeOfFrequency")
    @Column(name = "ModeOfFrequency")
    private String ModeOfFrequency;

    @JsonProperty("PremiumDueDate")
    @Column(name = "PremiumDueDate")
    private String PremiumDueDate;

    @JsonProperty("DraftDueDate")
    @Column(name = "DraftDueDate")
    private int DraftDueDate=0;

    @JsonProperty("PremiumAmount")
    @Column(name = "PremiumAmount")
    private String PremiumAmount;

    @JsonProperty("LoanAmount")
    @Column(name = "LoanAmount")
    private String LoanAmount;

    @JsonProperty("PaymentAmount")
    @Column(name = "PaymentAmount")
    private String PaymentAmount;

    @JsonProperty("ImmediatePremiumDraft")
    private String ImmediatePremiumDraft;

    @JsonProperty("ImmediateLoanDraft")
    private String ImmediateLoanDraft;

    @JsonProperty("ApplicationId")
    private String ApplicationId;

    @JsonProperty("SourceSystem")
    private String SourceSystem;

    @JsonProperty("NameOnAccount")
    @Column(name = "NameOnAccount")
    private String NameOnAccount;

    @JsonProperty("BankName")
    @Column(name = "BankName")
    private String BankName;

    @JsonProperty("BankAccountType")
    @Column(name = "BankAccountType")
    private String BankAccountType;

    @JsonProperty("BankAccountNumber")
    @Column(name = "BankAccountNumber")
    private String BankAccountNumber;

    @JsonProperty("BankRoutingNumber")
    @Column(name = "BankRoutingNumber")
    private String BankRoutingNumber;

    @JsonProperty("Role")
    @Column(name = "Role")
    private String Role;

    @JsonProperty("WorkItemId")
    @Column(name = "WorkItemId")
    private String WorkItemId;

    @JsonProperty("Error")
    @Column(name = "Error")
    private String Error;

    @JsonProperty("ErrorDetails")
    @Column(name = "ErrorDetails", columnDefinition = "nvarchar", nullable = true)
    private String ErrorDetails;

    @JsonProperty("CreatedTime")
    @Column(name = "CreatedTime")
    private Date CreatedTime;

    @JsonProperty("CallType")
    @Column(name = "CallType")
    private String CallType;

}
