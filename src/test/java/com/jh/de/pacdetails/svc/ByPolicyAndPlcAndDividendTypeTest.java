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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public final class ByPolicyAndPlcAndDividendTypeTest {
    ///region Test suites for executable com.jh.de.pacdetails.svc.ByPolicyAndPlcAndDividendType.isApplicable



    /**
     *  {@link ByPolicyAndPlcAndDividendType}
     *  {@link ByPolicyAndPlcAndDividendType#isApplicable(DividendRequest)}
     *  {@code (CollectionUtils.isEmpty(dr.getDividendAccountTypes())): False}
     *  {@code return !CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes());}
     */
    @Test
    @DisplayName("isApplicable: CollectionUtils.isEmpty(dr.getDividendAccountTypes()) : False -> return !CollectionUtils.isEmpty(dr.getDividendTypes()) && CollectionUtils.isEmpty(dr.getDividendAccountTypes())")
    public void testIsApplicable_NotCollectionUtilsIsEmpty() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(CollectionUtils.class);
            (mockedStatic.when(() -> CollectionUtils.isEmpty(any(java.util.Collection.class)))).thenReturn(false, false);
            ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
            DividendRequest drMock = mock(DividendRequest.class);
            (when(drMock.getDividendTypes())).thenReturn(((List) null));
            (when(drMock.getDividendAccountTypes())).thenReturn(((List) null));

            Boolean actual = byPolicyAndPlcAndDividendType.isApplicable(drMock);

            Boolean expected = false;

            assertEquals(expected, actual);
        } finally {
            mockedStatic.close();
        }
    }
    ///endregion

    ///region FUZZER: SUCCESSFUL EXECUTIONS for method isApplicable(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlcAndDividendType}
     *  {@link ByPolicyAndPlcAndDividendType#isApplicable(DividendRequest)}
     */
    @Test
    @DisplayName("isApplicable: dr = mock()")
    public void testIsApplicable() {
        ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlcAndDividendType.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlcAndDividendType.requestHeaderBean = requestHeaderBeanMock;
        DividendRequest drMock = mock(DividendRequest.class);
        List listMock = mock(List.class);
        (when(listMock.isEmpty())).thenReturn(false);
        (when(drMock.getDividendTypes())).thenReturn(listMock);
        List listMock1 = mock(List.class);
        (when(listMock1.isEmpty())).thenReturn(false);
        (when(drMock.getDividendAccountTypes())).thenReturn(listMock1);

        Boolean actual = byPolicyAndPlcAndDividendType.isApplicable(drMock);

        Boolean expected = false;

        assertEquals(expected, actual);
    }

    /**
     *  {@link ByPolicyAndPlcAndDividendType}
     *  {@link ByPolicyAndPlcAndDividendType#isApplicable(DividendRequest)}
     */
    @Test
    @DisplayName("isApplicable: dr = mock()")
    public void testIsApplicable1() {
        ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlcAndDividendType.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlcAndDividendType.requestHeaderBean = requestHeaderBeanMock;
        DividendRequest drMock = mock(DividendRequest.class);
        List list = emptyList();
        (when(drMock.getDividendTypes())).thenReturn(list);

        Boolean actual = byPolicyAndPlcAndDividendType.isApplicable(drMock);

        Boolean expected = false;

        assertEquals(expected, actual);
    }

    /**
     * {@link ByPolicyAndPlcAndDividendType}
     * {@link ByPolicyAndPlcAndDividendType#isApplicable(DividendRequest)}
     */
    @Test
    @DisplayName("isApplicable: dr = mock()")
    public void testIsApplicable2() {
        ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlcAndDividendType.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlcAndDividendType.requestHeaderBean = requestHeaderBeanMock;
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        byPolicyAndPlcAndDividendType.virtualDataSvc = virtualDataSvcMock;
        DividendRequest drMock = mock(DividendRequest.class);
        LinkedList linkedList = new LinkedList();
        linkedList.add("XZ");
        (when(drMock.getDividendTypes())).thenReturn(linkedList);
        List list = emptyList();
        (when(drMock.getDividendAccountTypes())).thenReturn(list);

        Boolean actual = byPolicyAndPlcAndDividendType.isApplicable(drMock);

        Boolean expected = true;

        assertEquals(expected, actual);
    }
    ///endregion

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlcAndDividendType}
     *  {@link ByPolicyAndPlcAndDividendType#getDividendRecords(DividendRequest)}
     *  {@link DividendRequest#getPolicyNumber()}
     *  {@link DividendRequest#getPlc()}
     *  {@link DividendRequest#getDividendTypes()}
     *  {@link TPolicyPacInfoDividendRepository#findByPolicyNumberAndPlcAndDividendType(String, String, List)}
     *  {@code return dividendRepo.findByPolicyNumberAndPlcAndDividendType(dr.getPolicyNumber(), dr.getPlc(), dr.getDividendTypes());}
     */
    @Test
    @DisplayName("getDividendRecords: DividendRequestGetPolicyNumber -> return dividendRepo.findByPolicyNumberAndPlcAndDividendType(dr.getPolicyNumber(), dr.getPlc(), dr.getDividendTypes())")
    public void testGetDividendRecords_DividendRequestGetDividendTypes() {
        ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        (when(dividendRepoMock.findByPolicyNumberAndPlcAndDividendType(any(), any(), any()))).thenReturn(((List) null));
        byPolicyAndPlcAndDividendType.dividendRepo = dividendRepoMock;
        DividendRequest drMock = mock(DividendRequest.class);
        (when(drMock.getPolicyNumber())).thenReturn(((String) null));
        (when(drMock.getPlc())).thenReturn(((String) null));
        (when(drMock.getDividendTypes())).thenReturn(((List) null));

        List actual = byPolicyAndPlcAndDividendType.getDividendRecords(drMock);

        assertNull(actual);
    }
    ///endregion


    ///region FUZZER: TIMEOUTS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByPolicyAndPlcAndDividendType}
     *  {@link ByPolicyAndPlcAndDividendType#getDividendRecords(DividendRequest)}
     */
    @Test
    @DisplayName("getDividendRecords: dr = mock()")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testGetDividendRecords() {
        ByPolicyAndPlcAndDividendType byPolicyAndPlcAndDividendType = new ByPolicyAndPlcAndDividendType();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byPolicyAndPlcAndDividendType.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byPolicyAndPlcAndDividendType.requestHeaderBean = requestHeaderBeanMock;
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        byPolicyAndPlcAndDividendType.virtualDataSvc = virtualDataSvcMock;
        DividendRequest drMock = mock(DividendRequest.class);
        
        /* This execution may take longer than the 1000 ms timeout
         and therefore fail due to exceeding the timeout. */
        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> byPolicyAndPlcAndDividendType.getDividendRecords(drMock));
    }
    ///endregion

    ///endregion
}
