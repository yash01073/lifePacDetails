package com.jh.de.pacdetails.svc.pacsuspend;

import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.TokenService;
import com.jh.de.pacdetails.svc.ValidateSvc;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;


public final class VPasOneServiceImplTest {
    @InjectMocks
    private VPasOneServiceImpl vPasOneServiceImpl;

    @Mock
    private Builder builderMock;

    @Mock
    private TokenService tokenServiceMock;

    @Mock
    private ValidateSvc validateSvcMock;

    @Mock
    private NewRelicService newRelicServiceMock;

    private AutoCloseable mockitoCloseable;

    @Test
    @DisplayName("createErrorVpasOneResponse: errorMessage = 'NO', errorCode = '#$\\\"''")
    @Timeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
    public void testCreateErrorVpasOneResponseWithNonEmptyStrings() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {

        assertTimeoutPreemptively(Duration.ofMillis(1000L), () -> vPasOneServiceImpl.createErrorVpasOneResponse("NO", "#$\\\"'"));
    }

    @BeforeEach
    public void setUp() {
        mockitoCloseable = openMocks(this);
        ReflectionTestUtils.setField(vPasOneServiceImpl, "env", "");
        ReflectionTestUtils.setField(vPasOneServiceImpl, "vpasOneUrl", "abc");
        ReflectionTestUtils.setField(vPasOneServiceImpl, "convertedPolicyUrl", "10");
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

}
