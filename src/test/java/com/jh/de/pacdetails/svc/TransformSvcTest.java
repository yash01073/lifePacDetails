package com.jh.de.pacdetails.svc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.de.pacdetails.BaseTest;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class TransformSvcTest extends BaseTest {
    TransformSvc transformSvc = new TransformSvcImpl();

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetPacInfoResult() throws JsonProcessingException {
        LifeRecurringPaymentTransaction ptr1 = mapper.readValue(ptr, LifeRecurringPaymentTransaction.class);
        PacInfoResult result = transformSvc.getPacInfoResult(ptr1);
        assertEquals(ptr1.getPolicyNumber(), result.getPolicyNumber(), "policy number match success");
    }

    private static final String PacInfoResult = "{\n" +
            "            \"UUID\": \"TestUUID\",\n" +
            "            \"TransactionId\": \"smoketestdWUhVLurHUOxBFYRzeFt\",\n" +
            "            \"TokenId\": \"TestTokenId\",\n" +
            "            \"PolicyNumber\": \"062980532\",\n" +
            "            \"PLC\": \"11\",\n" +
            "            \"AdminSystemId\": \"VP\",\n" +
            "            \"ApplicationId\": null,\n" +
            "            \"SourceSystem\": null,\n" +
            "            \"Role\": \"Policy owner\",\n" +
            "            \"WorkItemId\": null,\n" +
            "            \"Error\": null,\n" +
            "            \"ErrorDetails\": null,\n" +
            "            \"CreatedTime\": \"2023-05-17T19:39:22.887+00:00\",\n" +
            "            \"PaymentDetails\": {\n" +
            "                \"PremiumAmount\": \"435.54\",\n" +
            "                \"LoanAmount\": \"9600.0\",\n" +
            "                \"PaymentAmount\": \"600.0\",\n" +
            "                \"ImmediatePremiumDraft\": null,\n" +
            "                \"ImmediateLoanDraft\": null,\n" +
            "                \"ModeOfFrequency\": \"Semi Annually\",\n" +
            "                \"PremiumDueDate\": null,\n" +
            "                \"DraftDueDate\": 28\n" +
            "            },\n" +
            "            \"BankDetails\": {\n" +
            "                \"NameOnAccount\": \"Test User Name\",\n" +
            "                \"BankName\": \"City\",\n" +
            "                \"BankAccountType\": \"checking\",\n" +
            "                \"BankAccountNumber\": \"5857878892\",\n" +
            "                \"BankRoutingNumber\": \"454345343\"\n" +
            "            }\n" +
            "        }";

    private static final String ptr = "{\n" +
            "            \"UUID\": \"TestUUID\",\n" +
            "            \"TransactionId\": \"smoketestdWUhVLurHUOxBFYRzeFt\",\n" +
            "            \"TokenId\": \"TestTokenId\",\n" +
            "            \"PolicyNumber\": \"062980532\",\n" +
            "            \"PLC\": \"11\",\n" +
            "            \"AdminSystemId\": \"VP\",\n" +
            "            \"ApplicationId\": null,\n" +
            "            \"SourceSystem\": null,\n" +
            "            \"Role\": \"Policy owner\",\n" +
            "            \"WorkItemId\": 12345,\n" +
            "            \"Error\": null,\n" +
            "            \"ErrorDetails\": null,\n" +
            "            \"CreatedTime\": \"2023-05-17T19:39:22.887+00:00\",\n" +
            "            \"PremiumAmount\": \"435.54\",\n" +
            "            \"LoanAmount\": \"9600.0\",\n" +
            "            \"PaymentAmount\": \"600.0\",\n" +
            "            \"ImmediatePremiumDraft\": null,\n" +
            "            \"ImmediateLoanDraft\": null,\n" +
            "            \"ModeOfFrequency\": \"Semi Annually\",\n" +
            "            \"PremiumDueDate\": null,\n" +
            "            \"DraftDueDate\": 28,\n" +
            "            \"NameOnAccount\": \"Test User Name\",\n" +
            "            \"BankName\": \"City\",\n" +
            "            \"BankAccountType\": \"checking\",\n" +
            "            \"BankAccountNumber\": \"5857878892\",\n" +
            "            \"BankRoutingNumber\": \"454345343\"\n" +
            "        }";
}
