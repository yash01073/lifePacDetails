package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoDividendRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public final class ByPolicyAndPlcTest {
    ///region Test suites for executable com.jh.de.pacdetails.svc.ByPolicyAndPlc.isApplicable

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method isApplicable(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     * {@link ByPolicyAndPlc}
     * {@link ByPolicyAndPlc#isApplicable(DividendRequest)}
     * {@code return CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes());}
     */
    @Test
    @DisplayName("isApplicable: return CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes()) : False -> return CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes())")
    public void testIsApplicable_CollectionUtilsIsEmptyAndCollectionUtilsIsEmpty() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(CollectionUtils.class);
            (mockedStatic.when(() -> CollectionUtils.isEmpty(any(java.util.Collection.class)))).thenReturn(false);
            ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
            DividendRequest drMock = mock(DividendRequest.class);
            (when(drMock.getDividendTypes())).thenReturn(((List) null));

            Boolean actual = byPolicyAndPlc.isApplicable(drMock);

            Boolean expected = false;

            assertEquals(expected, actual);
        } finally {
            mockedStatic.close();
        }
    }


    /**
     *  {@link ByPolicyAndPlc}
     *  {@link ByPolicyAndPlc#isApplicable(DividendRequest)}
     *  {@code (CollectionUtils.isEmpty(dr.getDividendAccountTypes())): False}
     *  {@code return CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes());}
     */
    @Test
    @DisplayName("isApplicable: CollectionUtils.isEmpty(dr.getDividendAccountTypes()) : False -> return CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes())")
    public void testIsApplicable_NotCollectionUtilsIsEmpty() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(CollectionUtils.class);
            (mockedStatic.when(() -> CollectionUtils.isEmpty(any(java.util.Collection.class)))).thenReturn(true, false);
            ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
            DividendRequest drMock = mock(DividendRequest.class);
            (when(drMock.getDividendTypes())).thenReturn(((List) null));
            (when(drMock.getDividendAccountTypes())).thenReturn(((List) null));

            Boolean actual = byPolicyAndPlc.isApplicable(drMock);

            Boolean expected = false;

            assertEquals(expected, actual);
        } finally {
            mockedStatic.close();
        }
    }
    ///endregion

    ///region FUZZER: SUCCESSFUL EXECUTIONS for method isApplicable(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlc}
     *  {@link ByPolicyAndPlc#isApplicable(DividendRequest)}
     */
    @Test
    @DisplayName("isApplicable: dr = mock()")
    public void testIsApplicable() {
        ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlc.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlc.requestHeaderBean = requestHeaderBeanMock;
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        byPolicyAndPlc.virtualDataSvc = virtualDataSvcMock;
        DividendRequest drMock = mock(DividendRequest.class);
        List listMock = mock(List.class);
        (when(listMock.isEmpty())).thenReturn(false);
        (when(drMock.getDividendTypes())).thenReturn(listMock);

        Boolean actual = byPolicyAndPlc.isApplicable(drMock);

        Boolean expected = false;

        assertEquals(expected, actual);
    }

    /**
     *  {@link ByPolicyAndPlc}
     *  {@link ByPolicyAndPlc#isApplicable(DividendRequest)}
     */
    @Test
    @DisplayName("isApplicable: dr = mock()")
    public void testIsApplicable1() {
        ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlc.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlc.requestHeaderBean = requestHeaderBeanMock;
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        byPolicyAndPlc.virtualDataSvc = virtualDataSvcMock;
        DividendRequest drMock = mock(DividendRequest.class);
        List list = emptyList();
        (when(drMock.getDividendTypes())).thenReturn(list);
        List listMock = mock(List.class);
        (when(listMock.isEmpty())).thenReturn(false);
        (when(drMock.getDividendAccountTypes())).thenReturn(listMock);

        Boolean actual = byPolicyAndPlc.isApplicable(drMock);

        Boolean expected = false;

        assertEquals(expected, actual);
    }
    ///endregion

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlc}
     *  {@link ByPolicyAndPlc#getDividendRecords(DividendRequest)}
     *  {@link DividendRequest#getPolicyNumber()}
     *  {@link DividendRequest#getPlc()}
     *  {@link TPolicyPacInfoDividendRepository#findByPolicyNumberAndPlc(String, String)}
     *  {@code return dividendRepo.findByPolicyNumberAndPlc(dr.getPolicyNumber(), dr.getPlc());}
     */
    @Test
    @DisplayName("getDividendRecords: DividendRequestGetPolicyNumber -> return dividendRepo.findByPolicyNumberAndPlc(dr.getPolicyNumber(), dr.getPlc())")
    public void testGetDividendRecords_TPolicyPacInfoDividendRepositoryFindByPolicyNumberAndPlc() {
        ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        (when(dividendRepoMock.findByPolicyNumberAndPlc(any(), any()))).thenReturn(((List) null));
        byPolicyAndPlc.dividendRepo = dividendRepoMock;
        DividendRequest drMock = mock(DividendRequest.class);
        (when(drMock.getPolicyNumber())).thenReturn(((String) null));
        (when(drMock.getPlc())).thenReturn(((String) null));

        List actual = byPolicyAndPlc.getDividendRecords(drMock);

        assertNull(actual);
    }
    ///endregion


    ///region FUZZER: TIMEOUTS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlc}
     *  {@link ByPolicyAndPlc#getDividendRecords(DividendRequest)}
     */
    @Test
    @DisplayName("getDividendRecords: dr = mock()")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testGetDividendRecords() {
        ByPolicyAndPlc byPolicyAndPlc = new ByPolicyAndPlc();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlc.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlc.requestHeaderBean = requestHeaderBeanMock;
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        byPolicyAndPlc.virtualDataSvc = virtualDataSvcMock;
        DividendRequest drMock = mock(DividendRequest.class);
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> byPolicyAndPlc.getDividendRecords(drMock));
    }
    ///endregion

    ///endregion
}
