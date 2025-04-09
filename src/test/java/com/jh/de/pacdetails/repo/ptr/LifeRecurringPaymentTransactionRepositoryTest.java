package com.jh.de.pacdetails.repo.ptr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jh.de.pacdetails.BaseTest;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
public class LifeRecurringPaymentTransactionRepositoryTest extends BaseTest {

    private LifeRecurringPaymentTransaction ptr1;

    @Autowired
    private LifeRecurringPaymentTransactionRepository lrpt;

    @BeforeAll
    public void setup() throws JsonProcessingException {
        ptr1 = mapper.readValue(ptr, LifeRecurringPaymentTransaction.class);
        lrpt.save(ptr1);
    }

  /*@Test
    public void test_findTransactionByPolicyNumber_ReturnPolicyTransactionData() {
        LifeRecurringPaymentTransaction ptr2 = lrpt.findTransactionByPolicyNumber("062980532");
        assertEquals(ptr1.getPolicyNumber(), ptr2.getPolicyNumber(), "Policy does match");
    }

    @Test
    public void test_findTransactionByPolicyNumberAndPlc_ReturnPolicyTransactionData() {
        LifeRecurringPaymentTransaction ptr2 = lrpt.findTransactionByPolicyNumberAndPlc("062980532", "11");
        assertEquals(ptr1.getPolicyNumber(), ptr2.getPolicyNumber(), "Policy does match");
        assertEquals(ptr1.getPLC(), ptr2.getPLC(), "PLC does match");
    }*/


    //@Test
    public void test_findTransactionByTransactionId_ReturnPolicyTransactionData() {
        LifeRecurringPaymentTransaction ptr2 = lrpt.findTransactionByTransactionId("smoketestdWUhVLurHUOxBFYRzeFt");
        assertEquals(ptr1.getTransactionId(), ptr2.getTransactionId(), "Transaction does match");
    }
}
