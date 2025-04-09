package com.jh.de.pacdetails.repo.ptr;

import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeRecurringPaymentTransactionRepository extends JpaRepository<LifeRecurringPaymentTransaction, Long> {

    static final String SELECT_TOP_1 = "Select top 1 ";

    static final String FROM_LRPT_WHERE = " from life_recurring_payment_transaction r where ";

    static final String UPDATE_LRPT = "update life_recurring_payment_transaction ";


    static final String COLUMN_NAMES = "id,transaction_id,uuid,token_id,policy_number,admin_system_id,mode_of_frequency," +
            "premium_due_date,draft_due_date,premium_amount,loan_amount,payment_amount,bank_account_type," +
            "name_on_account,bank_name,bank_account_number,bank_routing_number,role,work_item_id,error,error_details," +
            "created_time,plc,immediate_premium_draft,immediate_loan_draft," +
            "application_id,source_system,call_type";
    static final String QUERY_BY_TRANSACTION_ID = SELECT_TOP_1 + COLUMN_NAMES + FROM_LRPT_WHERE +
            "r.transaction_id = :transactionId ORDER BY created_time DESC";
    static final String QUERY_BY_POLICY_NUMBER = SELECT_TOP_1 + COLUMN_NAMES + FROM_LRPT_WHERE +
            "r.policy_number = :policyNumber and error is NULL and work_item_id is not NULL and created_time >= DATEADD(day, -31, getdate()) and call_type != 'Suspend' ORDER BY created_time DESC";

    static final String QUERY_BY_POLICY_NUMBER_AND_PLC = SELECT_TOP_1 + COLUMN_NAMES + FROM_LRPT_WHERE +
            "r.policy_number = :policyNumber and r.plc = :plc and error is NULL and work_item_id is not NULL and created_time >= DATEADD(day, -31, getdate()) and call_type != 'Suspend' ORDER BY created_time DESC";

    static final String UPDATE_PAC_SUSPEND_BY_POLICY_NUMBER_AND_PLC = UPDATE_LRPT +
            "set call_type = 'Suspend' where policy_number = :policyNumber and plc = :plc and error is NULL and work_item_id is not NULL and created_time >= DATEADD(day, -31, getdate())";
    @Query(nativeQuery=true, value = QUERY_BY_TRANSACTION_ID)
    LifeRecurringPaymentTransaction findTransactionByTransactionId(@Param("transactionId") String transactionId);
    @Query(nativeQuery=true, value = QUERY_BY_POLICY_NUMBER)
    LifeRecurringPaymentTransaction findTransactionByPolicyNumber(@Param("policyNumber") String policyNumber);
    @Query(nativeQuery=true, value = QUERY_BY_POLICY_NUMBER_AND_PLC)
    LifeRecurringPaymentTransaction findTransactionByPolicyNumberAndPlc(@Param("policyNumber") String policyNumber, @Param("plc") String plc);
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = UPDATE_PAC_SUSPEND_BY_POLICY_NUMBER_AND_PLC)
    void updatePacSuspendTransactionByPolicyNumberAndPlc(@Param("policyNumber") String policyNumber, @Param("plc") String plc);
}
