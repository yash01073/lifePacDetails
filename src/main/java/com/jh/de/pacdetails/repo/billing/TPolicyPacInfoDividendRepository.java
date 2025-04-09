package com.jh.de.pacdetails.repo.billing;

import com.jh.de.pacdetails.model.entity.billing.DividendKey;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@ConditionalOnProperty(name = "spring.datasource.sqlmi.billing.enable", havingValue = "true")
@Repository
public interface TPolicyPacInfoDividendRepository extends JpaRepository<TPolicyPacInfoDividend, DividendKey> {

    static final String SELECT = "Select ";

    static final String PAC_INFO_DIVIDEND_COLUMN_NAMES = " SYSTEM_ID,POLICY_NUMBER,PLC,DIVIDEND_TYPES,DIVIDEND_ACCT_ID," +
            "COVERAGEID,PRDCOVERAGEID,DIVIDEND_AMOUNTS,UPDATE_TS,POLICY_ACTIVE_STATUS,POLICY_CATEGORY,POLICY_UPD_TS ";

    static final String FROM_TPOLICY_DIVIDEND_WHERE = " from {h-schema}TPOLICY_PAC_INFO_DIVIDEND r where ";

    static final String QUERY_BY_POLICY_AND_PLC = SELECT + PAC_INFO_DIVIDEND_COLUMN_NAMES + FROM_TPOLICY_DIVIDEND_WHERE +
            "r.POLICY_NUMBER = :policyNumber " +
            "and r.PLC = :plc ";
    static final String QUERY_BY_POLICY_AND_PLC_AND_D_TYPE = QUERY_BY_POLICY_AND_PLC +
            "and DIVIDEND_TYPES in (:dividendTypes) ";
    static final String QUERY_BY_POLICY_AND_PLC_AND_DA_TYPE = QUERY_BY_POLICY_AND_PLC +
            "and DIVIDEND_ACCT_ID in (:dividendAccountTypes) ";
    static final String QUERY_BY_POLICY_AND_PLC_AND_D_TYPE_AND_DA_TYPE = QUERY_BY_POLICY_AND_PLC_AND_D_TYPE +
            "and DIVIDEND_ACCT_ID in (:dividendAccountTypes) ";

    @Query(nativeQuery=true, value = QUERY_BY_POLICY_AND_PLC)
    List<TPolicyPacInfoDividend> findByPolicyNumberAndPlc(
            @Param("policyNumber") String policyNumber,
            @Param("plc") String plc);
    @Query(nativeQuery=true, value = QUERY_BY_POLICY_AND_PLC_AND_D_TYPE)
    List<TPolicyPacInfoDividend> findByPolicyNumberAndPlcAndDividendType(
            @Param("policyNumber") String policyNumber,
            @Param("plc") String plc,
            @Param("dividendTypes") List<String> dividendTypes);
    @Query(nativeQuery=true, value = QUERY_BY_POLICY_AND_PLC_AND_DA_TYPE)
    List<TPolicyPacInfoDividend> findByPolicyNumberAndPlcAndDividendAccountType(
            @Param("policyNumber") String policyNumber,
            @Param("plc") String plc,
            @Param("dividendAccountTypes") List<String> dividendAccountTypes);
    @Query(nativeQuery=true, value = QUERY_BY_POLICY_AND_PLC_AND_D_TYPE_AND_DA_TYPE)
    List<TPolicyPacInfoDividend> findByPolicyNumberAndPlcAndDividendTypeAndDividendAccountType(
            @Param("policyNumber") String policyNumber,
            @Param("plc") String plc,
            @Param("dividendTypes") List<String> dividendTypes,
            @Param("dividendAccountTypes") List<String> dividendAccountTypes);
}
