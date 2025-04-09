package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoDividendRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.deepEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ByVirtualDataTest {
    ///region Test suites for executable com.jh.de.pacdetails.svc.ByVirtualData.isApplicable

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method isApplicable(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByVirtualData}
     *  {@link ByVirtualData#isApplicable(DividendRequest)}
     *  {@link RequestHeaderBean#getIsVirtual()}
     *  {@code return requestHeaderBean.getIsVirtual();}
     */
    @Test
    @DisplayName("isApplicable: RequestHeaderBeanGetIsVirtual -> return requestHeaderBean.getIsVirtual()")
    public void testIsApplicable_RequestHeaderBeanGetIsVirtual() {
        ByVirtualData byVirtualData = new ByVirtualData();
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        (when(requestHeaderBeanMock.getIsVirtual())).thenReturn(((Boolean) null));
        byVirtualData.requestHeaderBean = requestHeaderBeanMock;

        Boolean actual = byVirtualData.isApplicable(null);

        assertNull(actual);
    }
    ///endregion

    ///region SYMBOLIC EXECUTION: SUCCESSFUL EXECUTIONS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByVirtualData}
     *  {@link ByVirtualData#getDividendRecords(DividendRequest)}
     *  {@code (virtualDataSvc != null): True}
     *  {@link VirtualDataSvc#getDividendRecords(DividendRequest)}
     *  {@code return virtualDataSvc.getDividendRecords(dr);}
     */
    @Test
    @DisplayName("getDividendRecords: virtualDataSvc != null : True -> return virtualDataSvc.getDividendRecords(dr)")
    public void testGetDividendRecords_VirtualDataSvcNotEqualsNull() {
        ByVirtualData byVirtualData = new ByVirtualData();
        VirtualDataSvc virtualDataSvcMock = mock(VirtualDataSvc.class);
        (when(virtualDataSvcMock.getDividendRecords(any()))).thenReturn(((List) null));
        byVirtualData.virtualDataSvc = virtualDataSvcMock;

        List actual = byVirtualData.getDividendRecords(null);

        assertNull(actual);
    }
    ///endregion

    ///region FUZZER: SUCCESSFUL EXECUTIONS for method getDividendRecords(com.jh.de.pacdetails.model.request.DividendRequest)

    /**
     *  {@link ByVirtualData}
     *  {@link ByVirtualData#getDividendRecords(DividendRequest)}
     */
    @Test
    @DisplayName("getDividendRecords: dr = mock()")
    public void testGetDividendRecords() {
        ByVirtualData byVirtualData = new ByVirtualData();
        TPolicyPacInfoDividendRepository dividendRepoMock = mock(TPolicyPacInfoDividendRepository.class);
        byVirtualData.dividendRepo = dividendRepoMock;
        RequestHeaderBean requestHeaderBeanMock = mock(RequestHeaderBean.class);
        byVirtualData.requestHeaderBean = requestHeaderBeanMock;
        DividendRequest drMock = mock(DividendRequest.class);

        List actual = byVirtualData.getDividendRecords(drMock);

        List expected = new ArrayList();

        assertTrue(deepEquals(expected, actual));
    }
    ///endregion

    ///endregion
}
