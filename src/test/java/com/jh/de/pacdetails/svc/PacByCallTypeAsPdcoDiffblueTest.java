package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
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
class PacByCallTypeAsPdcoDiffblueTest {
    @Mock
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;

    @Mock
    private PDCOPaymentDelegatorService pDCOPaymentDelegatorService;

    @InjectMocks
    private PacByCallTypeAsPdco pacByCallTypeAsPdco;

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
     * Test {@link PacByCallTypeAsPdco#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@code Call Type}.</li>
     *   <li>Then return {@code false}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByCallTypeAsPdco#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given 'Call Type'; then return 'false'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenCallType_thenReturnFalse() {
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
        assertFalse(pacByCallTypeAsPdco.isApplicable(request));
    }

    /**
     * Test {@link PacByCallTypeAsPdco#isApplicable(PacInfoRequest)}.
     * <ul>
     *   <li>Given {@code PDCO}.</li>
     *   <li>When {@link PacInfoRequest} (default constructor) CallType is {@code PDCO}.</li>
     *   <li>Then return {@code true}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PacByCallTypeAsPdco#isApplicable(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test isApplicable(PacInfoRequest); given 'PDCO'; when PacInfoRequest (default constructor) CallType is 'PDCO'; then return 'true'")
    @Tag("MaintainedByDiffblue")
    void testIsApplicable_givenPdco_whenPacInfoRequestCallTypeIsPdco_thenReturnTrue() {
        // Arrange
        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("PDCO");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertTrue(pacByCallTypeAsPdco.isApplicable(request));
    }

    /**
     * Test {@link PacByCallTypeAsPdco#getResponse(PacInfoRequest)}.
     * <p>
     * Method under test: {@link PacByCallTypeAsPdco#getResponse(PacInfoRequest)}
     */
    @Test
    @DisplayName("Test getResponse(PacInfoRequest)")
    @Tag("MaintainedByDiffblue")
    void testGetResponse() {
        // Arrange
        when(pDCOPaymentDelegatorService.getPacInfoRecords(Mockito.<PacInfoRequest>any())).thenReturn(new ArrayList<>());
        doNothing().when(validateSvc).validateRequest(Mockito.<PacInfoRequest>any());

        PacInfoRequest request = new PacInfoRequest();
        request.setAdminSystem("Admin System");
        request.setBusiness("Business");
        request.setCallType("Call Type");
        request.setPlc("Plc");
        request.setPolicyNumber("42");
        request.setTransactionId("42");
        request.setUUID("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        List<PacInfoResult> actualResponse = pacByCallTypeAsPdco.getResponse(request);

        // Assert
        verify(pDCOPaymentDelegatorService).getPacInfoRecords(isA(PacInfoRequest.class));
        verify(validateSvc).validateRequest(isA(PacInfoRequest.class));
        assertEquals("PDCO", pacByCallTypeAsPdco.getTgtSrc());
        assertTrue(actualResponse.isEmpty());
    }

    /**
     * Test {@link PacByCallTypeAsPdco#getErrorMessage(PacInfoRequest)}.
     * <p>
     * Method under test: {@link PacByCallTypeAsPdco#getErrorMessage(PacInfoRequest)}
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
        assertEquals("Latest PAC info does not exist for  policy: 42 and PLC: Plc",
                pacByCallTypeAsPdco.getErrorMessage(request));
    }
}
