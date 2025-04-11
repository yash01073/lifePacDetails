package com.jh.de.pacdetails.svc.pacsuspend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import  org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.exception.ApiException.InternalServerException;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.TokenService;
import com.jh.de.pacdetails.svc.ValidateSvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;

@ContextConfiguration(classes = {VPasOneServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class VPasOneServiceImplDiffblueTest {
    @MockitoBean
    private Builder builder;

    @MockitoBean(name = "NewRelicService")
    private NewRelicService newRelicService;

    @MockitoBean
    private TokenService tokenService;

    @Autowired
    private VPasOneServiceImpl vPasOneServiceImpl;

    @MockitoBean
    private ValidateSvc validateSvc;

    /**
     * Test {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}.
     * <p>
     * Method under test: {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test suspendPacForVPasOne(PacSuspendRequest)")
    @Tag("MaintainedByDiffblue")
    void testSuspendPacForVPasOne() {
        // Arrange
        doThrow(new InternalServerException("An error occurred")).when(newRelicService)
                .populateVPasOneTokenErrorResponse(Mockito.<VPASOneTokenResponse>any());
        when(tokenService.getVPASOneToken(Mockito.<String>any()))
                .thenReturn(new VPASOneTokenResponse(true, "An error occurred", "An error occurred"));

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act and Assert
        assertThrows(InternalServerException.class, () -> vPasOneServiceImpl.suspendPacForVPasOne(pacSuspendRequest));
        verify(newRelicService).populateVPasOneTokenErrorResponse(isA(VPASOneTokenResponse.class));
        verify(tokenService).getVPASOneToken(eq("42"));
    }

    /**
     * Test {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}.
     * <ul>
     *   <li>Given Bean Name{NewRelicService}.</li>
     *   <li>Then calls {@link WebClient#get()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test suspendPacForVPasOne(PacSuspendRequest); given Bean Name{NewRelicService}; then calls get()")
    @Tag("MaintainedByDiffblue")
    void testSuspendPacForVPasOne_givenBeanNameNewRelicService_thenCallsGet() {
        // Arrange
        when(tokenService.getVPASOneToken(Mockito.<String>any()))
                .thenReturn(new VPASOneTokenResponse(false, "An error occurred", "An error occurred"));
        RequestHeadersUriSpec requestHeadersUriSpec = mock(RequestHeadersUriSpec.class);
        when(requestHeadersUriSpec.uri(Mockito.<String>any(), isA(Object[].class)))
                .thenThrow(new InternalServerException("An error occurred"));
        WebClient webClient = mock(WebClient.class);
        Mockito.<RequestHeadersUriSpec<?>>when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(builder.build()).thenReturn(webClient);

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act and Assert
        assertThrows(InternalServerException.class, () -> vPasOneServiceImpl.suspendPacForVPasOne(pacSuspendRequest));
        verify(tokenService).getVPASOneToken(eq("42"));
        verify(webClient).get();
        verify(builder).build();
        verify(requestHeadersUriSpec).uri(eq("${convertedPolicy.url}42&PLCode=Plc"), isA(Object[].class));
    }

    /**
     * Test {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}.
     * <ul>
     *   <li>Then return RetVal Message is {@code An error occurred}.</li>
     * </ul>
     * <p>
     * Method under test: {@link VPasOneServiceImpl#suspendPacForVPasOne(PacSuspendRequest)}
     */
    @Test
    @DisplayName("Test suspendPacForVPasOne(PacSuspendRequest); then return RetVal Message is 'An error occurred'")
    @Tag("MaintainedByDiffblue")
    void testSuspendPacForVPasOne_thenReturnRetValMessageIsAnErrorOccurred() {
        // Arrange
        doNothing().when(newRelicService).populateVPasOneTokenErrorResponse(Mockito.<VPASOneTokenResponse>any());
        when(tokenService.getVPASOneToken(Mockito.<String>any()))
                .thenReturn(new VPASOneTokenResponse(true, "An error occurred", "An error occurred"));

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyAdminKey("Policy Admin Key");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act
        VPasOneResponse actualSuspendPacForVPasOneResult = vPasOneServiceImpl.suspendPacForVPasOne(pacSuspendRequest);

        // Assert
        verify(newRelicService).populateVPasOneTokenErrorResponse(isA(VPASOneTokenResponse.class));
        verify(tokenService).getVPASOneToken(eq("42"));
        RetVal retVal = actualSuspendPacForVPasOneResult.getRetVal();
        assertEquals("An error occurred", retVal.getMessage());
        assertEquals("An error occurred", actualSuspendPacForVPasOneResult.getErrorStatus());
        assertEquals("NO", retVal.getSuccessful());
        assertNull(actualSuspendPacForVPasOneResult.getChangeEffDate());
    }

    /**
     * Test {@link VPasOneServiceImpl#createErrorVpasOneResponse(String, String)}.
     * <p>
     * Method under test: {@link VPasOneServiceImpl#createErrorVpasOneResponse(String, String)}
     */
    @Test
    @DisplayName("Test createErrorVpasOneResponse(String, String)")
    @Tag("MaintainedByDiffblue")
    void testCreateErrorVpasOneResponse() {
        // Arrange and Act
        VPasOneResponse actualCreateErrorVpasOneResponseResult = vPasOneServiceImpl
                .createErrorVpasOneResponse("An error occurred", "An error occurred");

        // Assert
        RetVal retVal = actualCreateErrorVpasOneResponseResult.getRetVal();
        assertEquals("An error occurred", retVal.getMessage());
        assertEquals("An error occurred", actualCreateErrorVpasOneResponseResult.getErrorStatus());
        assertEquals("NO", retVal.getSuccessful());
        assertNull(actualCreateErrorVpasOneResponseResult.getChangeEffDate());
    }
}
