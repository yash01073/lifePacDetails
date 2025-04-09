package com.jh.de.pacdetails.validate.response;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.BillingPacInfoResponseValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class ValidateModalPremAmount extends BaseValidation implements BillingPacInfoResponseValidate {
    @Override
    public String validate(TPolicyPacInfo billing){
        try {
            double pacDraftAmount = StringUtils.isBlank(billing.getPacDraftAmt()) ? 0 : Double.parseDouble(billing.getPacDraftAmt());
            double modalPremAmount = StringUtils.isBlank(billing.getModalPremAmount()) ? 0 : Double.parseDouble(billing.getModalPremAmount());
            if (modalPremAmount > pacDraftAmount) {
                return "pac draft amount less than modal premium amount";
            }
        } catch(NumberFormatException ex){
            return "unable to validate pac draft amount and modal premium amount";
        }

        return "";
    }
}
