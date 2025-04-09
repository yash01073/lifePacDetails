package com.jh.de.pacdetails.validate.response;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.validate.BaseValidation;
import com.jh.de.pacdetails.validate.BillingPacInfoResponseValidate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Component
public class ValidateModalLoanAmount extends BaseValidation implements BillingPacInfoResponseValidate {
    @Override
    public String validate(TPolicyPacInfo billing) {
        try {
            double pacDraftAmount = StringUtils.isBlank(billing.getPacDraftAmt()) ? 0 : Double.parseDouble(billing.getPacDraftAmt());
            double modalPremAmount = StringUtils.isBlank(billing.getModalPremAmount()) ? 0 : Double.parseDouble(billing.getModalPremAmount());
            double modalLoanAmount = StringUtils.isBlank(billing.getModalLoanAmount()) ? 0 : Double.parseDouble(billing.getModalLoanAmount());

            if(pacDraftAmount > modalPremAmount && (Math.abs(modalPremAmount + modalLoanAmount - pacDraftAmount)>1e-9)){
                return "premium and loan does not total to pac draft amount";
            }
        } catch(NumberFormatException ex){
            return "unable to validate pac draft amount, modal premium amount, modal loan amount";
        }

        return "";
    }
}
