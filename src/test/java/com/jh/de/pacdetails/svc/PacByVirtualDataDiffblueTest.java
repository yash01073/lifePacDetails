package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoRepository;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class PacByVirtualDataDiffblueTest {
    @Mock
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;

    @Mock
    private PDCOPaymentDelegatorService pDCOPaymentDelegatorService;

    @InjectMocks
    private PacByVirtualData pacByVirtualData;

    @Mock
    private RequestHeaderBean requestHeaderBean;

    @Mock
    private TPolicyPacInfoRepository tPolicyPacInfoRepository;

    @Mock
    private TransformSvc transformSvc;

    @Mock
    private ValidateSvc validateSvc;

    @Mock
    private VirtualDataSvc virtualDataSvc;

    /**
     * Test {@link PacByVirtualData#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@link RequestHeaderBean} {@link RequestHeaderBean#getIsVirtual()} return {@code false}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByVirtualData#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given RequestHeaderBean getIsVirtual() return 'false'; then return 'false'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenRequestHeaderBeanGetIsVirtualReturnFalse_thenReturnFalse() {
        // Arrange
        when(requestHeaderBean.getIsVirtual()).thenReturn(false);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        Boolean actualIsApplicableResult = pacByVirtualData.isApplicable(request);

        // Assert
        verify(requestHeaderBean).getIsVirtual();
        assertFalse(actualIsApplicableResult);
    }

    /**
     * Test {@link PacByVirtualData#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@link RequestHeaderBean} {@link RequestHeaderBean#getIsVirtual()} return {@code true}.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByVirtualData#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given RequestHeaderBean getIsVirtual() return 'true'; then return 'true'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenRequestHeaderBeanGetIsVirtualReturnTrue_thenReturnTrue() {
        // Arrange
        when(requestHeaderBean.getIsVirtual()).thenReturn(true);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        Boolean actualIsApplicableResult = pacByVirtualData.isApplicable(request);

        // Assert
        verify(requestHeaderBean).getIsVirtual();
        assertTrue(actualIsApplicableResult);
    }

    /**
     * Test {@link PacByVirtualData#getResponse(PacInfoRequest)}.
     * <ul>
     *   <li>Then {@link PacByVirtualData} TgtSrc is {@code ptr}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByVirtualData#getResponse(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getResponse(PacInfoRequest); then PacByVirtualData TgtSrc is 'ptr'")
    @Tag("MaintainedByDiffblue")
    void testGetResponse_thenPacByVirtualDataTgtSrcIsPtr() {
        // Arrange
        pacByVirtualData.setVirtualDataSvc(null);

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualResponse = pacByVirtualData.getResponse(request);

        // Assert
        assertEquals("ptr", pacByVirtualData.getTgtSrc());
        assertTrue(actualResponse.isEmpty());
    }

    /**
     * Test {@link PacByVirtualData#getResponse(PacInfoRequest)}.
     * <ul>
     *   <li>Then {@link PacByVirtualData} TgtSrc is {@code virtual}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByVirtualData#getResponse(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getResponse(PacInfoRequest); then PacByVirtualData TgtSrc is 'virtual'")
    @Tag("MaintainedByDiffblue")
    void testGetResponse_thenPacByVirtualDataTgtSrcIsVirtual() {
        // Arrange
        when(virtualDataSvc.getPacInfoRecords(Mockito.<PacInfoRequest>any())).thenReturn(new ArrayList<>());

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualResponse = pacByVirtualData.getResponse(request);

        // Assert
        verify(virtualDataSvc).getPacInfoRecords(isA(PacInfoRequest.class));
        assertEquals("virtual", pacByVirtualData.getTgtSrc());
        assertTrue(actualResponse.isEmpty());
    }

    /**
     * Test {@link PacByVirtualData#getErrorMessage(PacInfoRequest)}.
     * <p>
     * Method under test: {@link PacByVirtualData#getErrorMessage(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getErrorMessage(PacInfoRequest)")
    @Tag("MaintainedByDiffblue")
    void testGetErrorMessage() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertEquals("Latest PAC info does not exist for  virtual data policy: 42",
                pacByVirtualData.getErrorMessage(request));
    }
}
