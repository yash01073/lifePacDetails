package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.model.response.BankDetails;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.PaymentDetails;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface TransformSvc {
    PacInfoResult getPacInfoResult(LifeRecurringPaymentTransaction ptr);

    PacInfoResult getPacInfoResult(TPolicyPacInfo ptr, String policyNumber);

    DividendResponse getDividendResponse(List<TPolicyPacInfoDividend> records);

    AwdRequest getAWDRequest(PacSuspendRequest pacSuspendRequest, String vPasOneSuccess);

    PacSuspendResponse getPacSuspendResponse(String WorkItemId, PacSuspendRequest pacSuspendRequest);

    PacSuspendResponse getPacSuspendResponse(String WorkItemId, PacSuspendRequest pacSuspendRequest, VPasOneResponse vPasOneResponse);


    default BankDetails getBankDetails(LifeRecurringPaymentTransaction ptr) {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setBankName(ptr.getBankName());
        bankDetails.setBankAccountType(ptr.getBankAccountType());
        bankDetails.setNameOnAccount(ptr.getNameOnAccount());
        bankDetails.setBankRoutingNumber(StringUtils.right(StringUtils.trimToEmpty(ptr.getBankRoutingNumber()), 4));
        String ban = StringUtils.right(StringUtils.trimToEmpty(ptr.getBankAccountNumber()), 4);
        bankDetails.setBankAccountNumber(ban);
        bankDetails.setDisplay("Bank account ending with "+ban);
        return bankDetails;
    }

    default PaymentDetails getPaymentDetails(LifeRecurringPaymentTransaction ptr) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentAmount(ptr.getPaymentAmount());
        paymentDetails.setDraftDueDate(ptr.getDraftDueDate());
        paymentDetails.setImmediateLoanDraft(ptr.getImmediateLoanDraft());
        paymentDetails.setLoanAmount(ptr.getLoanAmount());
        paymentDetails.setPremiumAmount(ptr.getPremiumAmount());
        paymentDetails.setImmediatePremiumDraft(ptr.getImmediatePremiumDraft());
        paymentDetails.setModeOfFrequency(ptr.getModeOfFrequency());
        paymentDetails.setPremiumDueDate(ptr.getPremiumDueDate());
        return paymentDetails;
    }

    default BankDetails getBankDetails(TPolicyPacInfo billing) {
        BankDetails bankDetails = new BankDetails();
        //bankDetails.setBankName(billing.getBankName());
        //bankDetails.setBankAccountType(billing.getBankAccountType());
        //bankDetails.setNameOnAccount(billing.getNameOnAccount());
        //bankDetails.setBankRoutingNumber(billing.getBankRoutingNumber());
        //bankDetails.setBankAccountNumber(billing.getBankAccountNumber());
        return bankDetails;
    }

    default PaymentDetails getPaymentDetails(TPolicyPacInfo billing) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPremiumAmount(billing.getModalPremAmount());
        paymentDetails.setPaymentAmount(billing.getPacDraftAmt());
        paymentDetails.setLoanAmount(getLoanAmount(billing.getPacDraftAmt(), billing.getModalPremAmount(),billing.getModalLoanAmount()));
        paymentDetails.setModeOfFrequency(processFrequency(billing.getPacFrequency()));
        paymentDetails.setDraftDueDate(getDraftDueDay(billing.getPacDraftDate(), "-"));
        return paymentDetails;
    }
    default int getDraftDueDay(String pacDraftDate, String separator) {
        int start = StringUtils.lastIndexOf(pacDraftDate, separator) + 1;
        int end = start + 2;
        try {
            return Integer.parseInt(StringUtils.substring(pacDraftDate, start, end));
        } catch(NumberFormatException x) {
            return -1;
        }

    };

    default String getLoanAmount(String pacDraftAmount, String modalPremAmount, String modalLoanAmount) {
        double draftAmount = Double.parseDouble(pacDraftAmount);
        double premAmount = Double.parseDouble(modalPremAmount);
        double loanAmount;
        if(Math.abs(draftAmount - premAmount)<1e-9) {
            return "0.0";
        }
        if(StringUtils.isNotBlank(modalLoanAmount)){
            loanAmount = Double.parseDouble(modalLoanAmount);
            if(Math.abs(premAmount + loanAmount - draftAmount)<1e-9) {
                return modalLoanAmount;
            }
        }
        throw new ApiException.BadResponseException("Billing: Loan Amount Not Found");
    }

    //To avoid VPASOne error for mode_of_frequency in recurringpayment/savepayment
    default  String processFrequency(String billingFrequency){
        String lowerCaseFrequency = StringUtils.lowerCase(billingFrequency);
        switch(lowerCaseFrequency) {
            case "monthly":
                return "Monthly";

            case "quarterly":
                return "Quarterly";

            case "semi annually":
            case "semi-annually":
                return "Semi Annually";

            case "annually":
                return "Annually";

            default:
                throw new ApiException.BadRequestException("Billing: Frequency value not valid");
        }
    }

    default DividendResponse getDividendResponse() {
        DividendResponse response = new DividendResponse();
        response.setSuccessful(true);
        response.setErrorMessage(null);
        response.setCode("billing");
        return response;
    }
    default PacTransactionInfoResponse getPacTransactionInfoResponse(String tgtSource) {
        PacTransactionInfoResponse response = new PacTransactionInfoResponse();
        response.setCode(tgtSource);
        response.setSuccessful(true);
        response.setErrorMessage(null);
        return response;
    }
}
