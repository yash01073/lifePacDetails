package com.jh.de.pacdetails.svc.pacsuspend;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.response.APIMTokenResponse;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.svc.TokenService;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {AWDWorkItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AWDWorkItemServiceImplDiffblueTest {
    @Autowired
    private AWDWorkItemServiceImpl aWDWorkItemServiceImpl;

    @MockitoBean
    private Builder builder;

    @MockitoBean
    private TokenService tokenService;

    /**
     * Test {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}.
     * <p>
     * Method under test: {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}
     */
    @Test
    @DisplayName("Test createWorkItem(AwdRequest, String)")
    @Tag("MaintainedByDiffblue")
    void testCreateWorkItem() {
        // Arrange
        when(tokenService.getAPIMToken(Mockito.<String>any())).thenReturn(new APIMTokenResponse("ExampleToken", "ABC123"));
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        AwdCreateWorkItemResponse awdCreateWorkItemResponse = new AwdCreateWorkItemResponse();
        Mono<AwdCreateWorkItemResponse> justResult = Mono.just(awdCreateWorkItemResponse);
        when(responseSpec.bodyToMono(Mockito.<Class<AwdCreateWorkItemResponse>>any())).thenReturn(justResult);
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodySpec requestBodySpec3 = mock(RequestBodySpec.class);
        when(requestBodySpec3.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec2);
        RequestBodySpec requestBodySpec4 = mock(RequestBodySpec.class);
        when(requestBodySpec4.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec3);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec4);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        AwdRequest awdRequest = new AwdRequest();
        awdRequest.setApplicationId("42");
        awdRequest.setCreateInstance(new ArrayList<>());

        // Act
        AwdCreateWorkItemResponse actualCreateWorkItemResult = aWDWorkItemServiceImpl.createWorkItem(awdRequest, "42");

        // Assert
        verify(tokenService).getAPIMToken(eq("42"));
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Authorization"), isA(String[].class));
        verify(requestBodySpec4).header(eq("Ocp-Apim-Subscription-Key"), isA(String[].class));
        verify(requestBodySpec3).header(eq("Ocp-Apim-Trace"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${awdRest.url}"), isA(Object[].class));
        assertSame(awdCreateWorkItemResponse, actualCreateWorkItemResult);
    }

    /**
     * Test {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}.
     * <ul>
     *   <li>Then return {@link AwdCreateWorkItemResponse} (default constructor).</li>
     * </ul>
     * <p>
     * Method under test: {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}
     */
    @Test
    @DisplayName("Test createWorkItem(AwdRequest, String); then return AwdCreateWorkItemResponse (default constructor)")
    @Tag("MaintainedByDiffblue")
    void testCreateWorkItem_thenReturnAwdCreateWorkItemResponse() {
        // Arrange
        when(tokenService.getAPIMToken(Mockito.<String>any())).thenReturn(new APIMTokenResponse("ABC123", "ABC123"));
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        AwdCreateWorkItemResponse awdCreateWorkItemResponse = new AwdCreateWorkItemResponse();
        Mono<AwdCreateWorkItemResponse> justResult = Mono.just(awdCreateWorkItemResponse);
        when(responseSpec.bodyToMono(Mockito.<Class<AwdCreateWorkItemResponse>>any())).thenReturn(justResult);
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodySpec requestBodySpec3 = mock(RequestBodySpec.class);
        when(requestBodySpec3.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec2);
        RequestBodySpec requestBodySpec4 = mock(RequestBodySpec.class);
        when(requestBodySpec4.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec3);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec4);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        AwdRequest awdRequest = new AwdRequest();
        awdRequest.setApplicationId("42");
        awdRequest.setCreateInstance(new ArrayList<>());

        // Act
        AwdCreateWorkItemResponse actualCreateWorkItemResult = aWDWorkItemServiceImpl.createWorkItem(awdRequest, "42");

        // Assert
        verify(tokenService).getAPIMToken(eq("42"));
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Authorization"), isA(String[].class));
        verify(requestBodySpec4).header(eq("Ocp-Apim-Subscription-Key"), isA(String[].class));
        verify(requestBodySpec3).header(eq("Ocp-Apim-Trace"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${awdRest.url}"), isA(Object[].class));
        assertSame(awdCreateWorkItemResponse, actualCreateWorkItemResult);
    }

    /**
     * Test {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}.
     * <ul>
     *   <li>Then throw {@link ApiException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link AWDWorkItemServiceImpl#createWorkItem(AwdRequest, String)}
     */
    @Test
    @DisplayName("Test createWorkItem(AwdRequest, String); then throw ApiException")
    @Tag("MaintainedByDiffblue")
    void testCreateWorkItem_thenThrowApiException() {
        // Arrange
        when(tokenService.getAPIMToken(Mockito.<String>any())).thenReturn(new APIMTokenResponse("ABC123", "ABC123"));
        ResponseSpec responseSpec = mock(ResponseSpec.class);
        when(responseSpec.bodyToMono(Mockito.<Class<AwdCreateWorkItemResponse>>any()))
                .thenThrow(new ApiException("An error occurred"));
        RequestHeadersSpec requestHeadersSpec = mock(RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        RequestBodySpec requestBodySpec = mock(RequestBodySpec.class);
        Mockito
                .<RequestHeadersSpec<?>>when(
                        requestBodySpec.body(Mockito.<Publisher<Object>>any(), Mockito.<Class<Object>>any()))
                .thenReturn(requestHeadersSpec);
        RequestBodySpec requestBodySpec2 = mock(RequestBodySpec.class);
        when(requestBodySpec2.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec);
        RequestBodySpec requestBodySpec3 = mock(RequestBodySpec.class);
        when(requestBodySpec3.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec2);
        RequestBodySpec requestBodySpec4 = mock(RequestBodySpec.class);
        when(requestBodySpec4.header(Mockito.<String>any(), isA(String[].class))).thenReturn(requestBodySpec3);
        RequestBodyUriSpec requestBodyUriSpec = mock(RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.<String>any(), isA(Object[].class))).thenReturn(requestBodySpec4);
        WebClient webClient = mock(WebClient.class);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(builder.build()).thenReturn(webClient);

        AwdRequest awdRequest = new AwdRequest();
        awdRequest.setApplicationId("42");
        awdRequest.setCreateInstance(new ArrayList<>());

        // Act and Assert
        assertThrows(ApiException.class, () -> aWDWorkItemServiceImpl.createWorkItem(awdRequest, "42"));
        verify(tokenService).getAPIMToken(eq("42"));
        verify(webClient).post();
        verify(builder).build();
        verify(requestBodySpec).body(isA(Publisher.class), isA(Class.class));
        verify(requestBodySpec2).header(eq("Authorization"), isA(String[].class));
        verify(requestBodySpec4).header(eq("Ocp-Apim-Subscription-Key"), isA(String[].class));
        verify(requestBodySpec3).header(eq("Ocp-Apim-Trace"), isA(String[].class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).bodyToMono(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("${awdRest.url}"), isA(Object[].class));
    }
}
