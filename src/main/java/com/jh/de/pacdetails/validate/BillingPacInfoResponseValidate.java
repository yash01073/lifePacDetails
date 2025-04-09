package com.jh.de.pacdetails.validate;


import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;

public interface BillingPacInfoResponseValidate {
    String validate(TPolicyPacInfo billing);
}
