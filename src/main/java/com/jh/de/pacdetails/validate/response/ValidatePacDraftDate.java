package com.jh.de.pacdetails.validate.response;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.BillingPacInfoResponseValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class ValidatePacDraftDate extends BaseValidation implements BillingPacInfoResponseValidate {

    @Override
    public String validate(TPolicyPacInfo billing) {
        if (StringUtils.isBlank(billing.getPacDraftDate())) {
            return "pac draft date not found";
        }
        return "";
    }
}
