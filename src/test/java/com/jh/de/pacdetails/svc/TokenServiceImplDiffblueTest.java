package com.jh.de.pacdetails.svc;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.model.response.APIMTokenResponse;
import com.jh.de.pacdetails.model.response.VPASOneTokenResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {TokenServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TokenServiceImplDiffblueTest {
    @MockitoBean
    private Builder builder;

    @Autowired
    private TokenServiceImpl tokenServiceImpl;

    /**
     * Test {@link TokenServiceImpl#getVPASOneToken(String)}.
     * <p>
     * Method under test: {@link TokenServiceImpl#getVPASOneToken(String)}
     */
    @Test
    @DisplayName("Test getVPASOneToken(String)")
    @Tag("MaintainedByDiffblue")
    void testGetVPASOneToken() {
        // Arrange
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        VPASOneTokenResponse vpasOneTokenResponse = new VPASOneTokenResponse(true, "An error occurred",
                "An error occurred");

        Mono<VPASOneTokenResponse> justResult = Mono.just(vpasOneTokenResponse);
        when(responseSpec.bodyToMono(Mockito.<Class<VPASOneTokenResponse>>any())).thenReturn(justResult);
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        // Act
        VPASOneTokenResponse actualVPASOneToken = tokenServiceImpl.getVPASOneToken("42");

        // Assert
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Content-Type"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${vpasOne.token.url}"), isA(Object[].class));
        assertSame(vpasOneTokenResponse, actualVPASOneToken);
    }

    /**
     * Test {@link TokenServiceImpl#getVPASOneToken(String)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link TokenServiceImpl#getVPASOneToken(String)}
     */
    @Test
    @DisplayName("Test getVPASOneToken(String); then throw RuntimeException")
    @Tag("MaintainedByDiffblue")
    void testGetVPASOneToken_thenThrowRuntimeException() {
        // Arrange
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        when(responseSpec.bodyToMono(Mockito.<Class<VPASOneTokenResponse>>any())).thenThrow(new RuntimeException("UTF-8"));
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> tokenServiceImpl.getVPASOneToken("42"));
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Content-Type"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${vpasOne.token.url}"), isA(Object[].class));
    }

    /**
     * Test {@link TokenServiceImpl#getAPIMToken(String)}.
     * <p>
     * Method under test: {@link TokenServiceImpl#getAPIMToken(String)}
     */
    @Test
    @DisplayName("Test getAPIMToken(String)")
    @Tag("MaintainedByDiffblue")
    void testGetAPIMToken() {
        // Arrange
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        APIMTokenResponse apimTokenResponse = new APIMTokenResponse("ABC123", "ABC123");

        Mono<APIMTokenResponse> justResult = Mono.just(apimTokenResponse);
        when(responseSpec.bodyToMono(Mockito.<Class<APIMTokenResponse>>any())).thenReturn(justResult);
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        // Act
        APIMTokenResponse actualAPIMToken = tokenServiceImpl.getAPIMToken("42");

        // Assert
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Content-Type"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${apim.url}"), isA(Object[].class));
        assertSame(apimTokenResponse, actualAPIMToken);
    }

    /**
     * Test {@link TokenServiceImpl#getAPIMToken(String)}.
     * <p>
     * Method under test: {@link TokenServiceImpl#getAPIMToken(String)}
     */
    @Test
    @DisplayName("Test getAPIMToken(String)")
    @Tag("MaintainedByDiffblue")
    void testGetAPIMToken2() {
        // Arrange
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        APIMTokenResponse apimTokenResponse = new APIMTokenResponse("ExampleToken", "ABC123");

        Mono<APIMTokenResponse> justResult = Mono.just(apimTokenResponse);
        when(responseSpec.bodyToMono(Mockito.<Class<APIMTokenResponse>>any())).thenReturn(justResult);
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        // Act
        APIMTokenResponse actualAPIMToken = tokenServiceImpl.getAPIMToken("42");

        // Assert
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Content-Type"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${apim.url}"), isA(Object[].class));
        assertSame(apimTokenResponse, actualAPIMToken);
    }

    /**
     * Test {@link TokenServiceImpl#getAPIMToken(String)}.
     * <ul>
     *   <li>Then throw {@link RuntimeException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link TokenServiceImpl#getAPIMToken(String)}
     */
    @Test
    @DisplayName("Test getAPIMToken(String); then throw RuntimeException")
    @Tag("MaintainedByDiffblue")
    void testGetAPIMToken_thenThrowRuntimeException() {
        // Arrange
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        when(responseSpec.bodyToMono(Mockito.<Class<APIMTokenResponse>>any())).thenThrow(new RuntimeException("UTF-8"));
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> tokenServiceImpl.getAPIMToken("42"));
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Content-Type"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${apim.url}"), isA(Object[].class));
    }
}
