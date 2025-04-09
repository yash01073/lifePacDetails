/*
package com.jh.de.pacdetails.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.svc.pacsuspend.PacSuspendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PacSuspendController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PacSuspendControllerTest {
    @Autowired
    private PacSuspendController pacSuspendController;

    @MockBean
    private PacSuspendService pacSuspendService;

    */
/**
     * Method under test: {@link PacSuspendController#pacSuspend(PacSuspendRequest)}
     *//*

    @Test
    void testPacSuspend() throws Exception {
        // Arrange
        RetVal retVal = new RetVal();
        retVal.setMessage("Not all who wander are lost");
        retVal.setSuccessful("Successful");

        VPasOneResponse vPasOneResponse = new VPasOneResponse();
        vPasOneResponse.setChangeEffDate("2020-03-01");
        vPasOneResponse.setErrorStatus("An error occurred");
        vPasOneResponse.setRetVal(retVal);

        PacSuspendResponse pacSuspendResponse = new PacSuspendResponse();
        pacSuspendResponse.setCode("Code");
        pacSuspendResponse.setConfirmId("42");
        pacSuspendResponse.setPlc("Plc");
        pacSuspendResponse.setPolicyNumber("42");
        pacSuspendResponse.setSuccessful(true);
        pacSuspendResponse.setVPasOneResponse(vPasOneResponse);
        pacSuspendResponse.setWorkItemId("42");
        when(pacSuspendService.suspendPacSetUp(Mockito.<PacSuspendRequest>any())).thenReturn(pacSuspendResponse);

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");
        String content = (new ObjectMapper()).writeValueAsString(pacSuspendRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/suspend")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(pacSuspendController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":\"Code\",\"ConfirmationId\":\"42\",\"IsSuccessful\":true,\"plc\":\"Plc\",\"PolicyNumber\":\"42\",\"AwdWorkItemId"
                                        + "\":\"42\",\"VPasOneResponse\":{\"retVal\":{\"successful\":\"Successful\",\"message\":\"Not all who wander are"
                                        + " lost\"},\"changeEffDate\":\"2020-03-01\",\"errorStatus\":\"An error occurred\"}}"));
    }
}
*/
