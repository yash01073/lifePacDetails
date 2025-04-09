package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import com.newrelic.api.agent.NewRelic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.*;

public final class NewRelicServiceTest {

    @Test
    @DisplayName("populateErrorDetails: -> NewRelicAddCustomParameter")
    public void testPopulateErrorDetails_NewRelicAddCustomParameter() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(NewRelic.class);
            NewRelicService newRelicService = new NewRelicService();

            newRelicService.populateErrorDetails(((String) null));
            assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> newRelicService.populateErrorDetails((String) null));
        } finally {
            mockedStatic.close();
        }
    }

    @Test
    @DisplayName("populateVPasOneTokenErrorResponse: VPASOneTokenResponseIsError -> NewRelicAddCustomParameter")
    public void testPopulateVPasOneTokenErrorResponse_NewRelicAddCustomParameter() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(NewRelic.class);
            NewRelicService newRelicService = new NewRelicService();
            VPASOneTokenResponse vpasOneTokenResponseMock = mock(VPASOneTokenResponse.class);
            (when(vpasOneTokenResponseMock.isError())).thenReturn(false);
            (when(vpasOneTokenResponseMock.getErrorMessage())).thenReturn(((String) null));
            (when(vpasOneTokenResponseMock.getErrorCode())).thenReturn(((String) null));

            newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponseMock);
            assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponseMock));
        } finally {
            mockedStatic.close();
        }
    }

    @Test
    @DisplayName("populateVPasOneTokenErrorResponse: vpasOneTokenResponse = mock()")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testPopulateVPasOneTokenErrorResponse() {
        NewRelicService newRelicService = new NewRelicService();
        VPASOneTokenResponse vpasOneTokenResponseMock = mock(VPASOneTokenResponse.class);

        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> newRelicService.populateVPasOneTokenErrorResponse(vpasOneTokenResponseMock));
    }

}
