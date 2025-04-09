package com.jh.de.pacdetails.svc;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public final class TokenServiceImplTest {
    @InjectMocks
    private TokenServiceImpl tokenServiceImpl;

    @Mock
    private Builder builderMock;

    private AutoCloseable mockitoCloseable;


    @Test
    @DisplayName("getAPIMToken: requestBodyAuthDetails = ApimTokenizeConstants.CLIENT_ID + URLEncoder.encode(apimClientId, ApimTokenizeConstants.UTF8) + ApimTokenizeConstants.CLIENT_SECRET + URLEncoder.encode(apimClientSecret, ApimTokenizeConstants.UTF8) + ApimTokenizeConstants.GRANT_TYPE + apimClientCredentials -> ThrowNullPointerException")
    public void testGetAPIMToken_ThrowNullPointerException() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
        MockedStatic mockedStatic = null;
        try {
            mockedStatic = mockStatic(Charset.class);
            (mockedStatic.when(() -> Charset.forName(any()))).thenReturn(((Charset) null));
            String string = "";
            ReflectionTestUtils.setField(tokenServiceImpl, "apimClientId", string);

            assertThrows(NullPointerException.class, () -> tokenServiceImpl.getAPIMToken(null));
        } finally {
            mockedStatic.close();
        }
    }
    ///endregion

    ///region FUZZER: TIMEOUTS for method getAPIMToken(java.lang.String)

    @BeforeEach
    public void setUp() {
        mockitoCloseable = openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

}
