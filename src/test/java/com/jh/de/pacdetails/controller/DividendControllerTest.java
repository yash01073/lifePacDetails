package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.svc.DividendSvc;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.ValidateSvc;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public final class DividendControllerTest {
    @InjectMocks
    private DividendController dividendController;

    @Mock
    private ValidateSvc validateSvcMock;

    @Mock
    private NewRelicService newRelicServiceMock;

    @Mock
    private DividendSvc dividendSvcMock;

    private AutoCloseable mockitoCloseable;

    @Test
    @DisplayName("getDividendDetails: NewRelicServicePopulateInputRequest -> return ResponseEntity.ok().body(response)")
    public void testGetDividendDetails_ResponseEntityBody() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(ResponseEntity.class);
            BodyBuilder bodyBuilderMock = mock(BodyBuilder.class);
            (when(bodyBuilderMock.body(any()))).thenReturn(((ResponseEntity) null));
            (mockedStatic.when(ResponseEntity::ok)).thenReturn(bodyBuilderMock);

            (((ValidateSvc) (doNothing()).when(validateSvcMock))).validateRequest(any(com.jh.de.pacdetails.model.request.DividendRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateInputRequest(any(com.jh.de.pacdetails.model.request.DividendRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateResponse(any(DividendResponse.class));
            (when(dividendSvcMock.getDividendDetails(any()))).thenReturn(((DividendResponse) null));

            ResponseEntity actual = dividendController.getDividendDetails(null);

            assertNull(actual);
        } finally {
            mockedStatic.close();
        }
    }

    @Test
    @DisplayName("getDividendDetails: return ResponseEntity.ok().body(response) : True -> ThrowNullPointerException")
    public void testGetDividendDetails_ThrowNullPointerException() {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(ResponseEntity.class);
            (mockedStatic.when(ResponseEntity::ok)).thenReturn(((BodyBuilder) null));
            (((ValidateSvc) (doNothing()).when(validateSvcMock))).validateRequest(any(com.jh.de.pacdetails.model.request.DividendRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateInputRequest(any(com.jh.de.pacdetails.model.request.DividendRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateResponse(any(DividendResponse.class));
            (when(dividendSvcMock.getDividendDetails(any()))).thenReturn(((DividendResponse) null));

        } finally {
            mockedStatic.close();
        }
    }

    @BeforeEach
    public void setUp() {
        mockitoCloseable = openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

}
