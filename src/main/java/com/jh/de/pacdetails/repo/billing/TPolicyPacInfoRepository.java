package com.jh.de.pacdetails.repo.billing;

import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@ConditionalOnProperty(name = "spring.datasource.sqlmi.billing.enable", havingValue = "true")
@Repository
public interface TPolicyPacInfoRepository extends JpaRepository<TPolicyPacInfo, Long> {

    static final String SELECT_TOP_1 = "Select top 1 ";

    static final String FROM_TPOLICY_WHERE = " from {h-schema}TPOLICY_PAC_INFO r where ";

    static final String PAC_INFO_COLUMN_NAMES = "POLICY_NUMBER,PLC,SYSTEM_ID,PAYMENT_METHOD," +
            "PAC_FREQUENCY,PAC_DRAFT_DATE,PAC_DRAFT_AMT,OUTSTANDING_LOAN_AMOUNT," +
            "MAX_LOAN_AVILABLE,CREATED_TS,UPDATED_TS, MODAL_PREM_AMOUNT, MODAL_LOAN_AMOUNT";

    static final String ORDER_BY_UPDATED_TS_DESC = " ORDER BY UPDATED_TS DESC ";

    static final String PAYMENT_METHOD_CONDITION = " and PAYMENT_METHOD in ('E', 'P') ";
    static final String QUERY_BY_POLICY = SELECT_TOP_1 + PAC_INFO_COLUMN_NAMES + FROM_TPOLICY_WHERE +
            "r.POLICY_NUMBER = :policyNumber " + PAYMENT_METHOD_CONDITION + ORDER_BY_UPDATED_TS_DESC;

    static final String QUERY_BY_POLICY_AND_PLC = SELECT_TOP_1 + PAC_INFO_COLUMN_NAMES + FROM_TPOLICY_WHERE +
                "r.POLICY_NUMBER = :policyNumber and r.PLC = :plc " + PAYMENT_METHOD_CONDITION + ORDER_BY_UPDATED_TS_DESC;

    @Query(nativeQuery=true, value = QUERY_BY_POLICY)
    TPolicyPacInfo findTransactionByPolicyNumber(@Param("policyNumber") String policyNumber);

    @Query(nativeQuery=true, value = QUERY_BY_POLICY_AND_PLC)
    TPolicyPacInfo findTransactionByPolicyNumberAndPlc(@Param("policyNumber") String policyNumber, @Param("plc") String plc);
}
