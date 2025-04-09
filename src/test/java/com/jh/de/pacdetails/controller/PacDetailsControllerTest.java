package com.jh.de.pacdetails.controller;

import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.PacTransactionSvc;
import com.jh.de.pacdetails.svc.ValidateSvc;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


public final class PacDetailsControllerTest {
    @InjectMocks
    private PacDetailsController pacDetailsController;

    @Mock
    private ValidateSvc validateSvcMock;

    @Mock
    private NewRelicService newRelicServiceMock;

    @Mock
    private PacTransactionSvc pacTransactionSvcMock;

    private AutoCloseable mockitoCloseable;


    @Test
    @DisplayName("getPacTransactionDetails: NewRelicServicePopulateInputRequest -> return ResponseEntity.ok().body(response)")
    public void testGetPacTransactionDetails_ResponseEntityBody() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(ResponseEntity.class);
            BodyBuilder bodyBuilderMock = mock(BodyBuilder.class);
            (when(bodyBuilderMock.body(any()))).thenReturn(((ResponseEntity) null));
            (mockedStatic.when(ResponseEntity::ok)).thenReturn(bodyBuilderMock);

            (((ValidateSvc) (doNothing()).when(validateSvcMock))).validateRequest(any(PacInfoRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateInputRequest(any(PacInfoRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateResponse(any(PacTransactionInfoResponse.class));
            (when(pacTransactionSvcMock.getPacTransactionDetails(any()))).thenReturn(((PacTransactionInfoResponse) null));



            ResponseEntity actual = pacDetailsController.getPacTransactionDetails(null);

            assertNull(actual);
        } finally {
            mockedStatic.close();
        }
    }


    @Test
    @DisplayName("getPacTransactionDetails: return ResponseEntity.ok().body(response) : True -> ThrowNullPointerException")
    public void testGetPacTransactionDetails_ThrowNullPointerException() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(ResponseEntity.class);
            (mockedStatic.when(ResponseEntity::ok)).thenReturn(((BodyBuilder) null));
            (((ValidateSvc) (doNothing()).when(validateSvcMock))).validateRequest(any(PacInfoRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateInputRequest(any(PacInfoRequest.class));
            (((NewRelicService) (doNothing()).when(newRelicServiceMock))).populateResponse(any(PacTransactionInfoResponse.class));
            (when(pacTransactionSvcMock.getPacTransactionDetails(any()))).thenReturn(((PacTransactionInfoResponse) null));

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
