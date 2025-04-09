package com.jh.de.pacdetails.svc.pacsuspend;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.AWDResponse;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.model.response.AwdInstance;
import com.jh.de.pacdetails.model.response.Detail;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.RetVal;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.TransformSvc;
import com.jh.de.pacdetails.svc.ValidateSvc;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PacSuspendServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PacSuspendServiceImplTest {
    @MockBean
    private AWDWorkItemService aWDWorkItemService;

    @MockBean
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;

    @MockBean(name = "NewRelicService")
    private NewRelicService newRelicService;

    @Autowired
    private PacSuspendServiceImpl pacSuspendServiceImpl;

    @MockBean
    private TransformSvc transformSvc;

    @MockBean
    private ValidateSvc validateSvc;

    @Test
    void testSuspendPacSetUp() {
        // Arrange
        Detail details = new Detail();
        details.setList(new ArrayList<>());

        AwdInstance instance = new AwdInstance();
        instance.setDate("2020-03-01");
        instance.setId("42");
        instance.setPermission("Permission");
        instance.setPriority("Priority");
        instance.setState("MD");
        instance.setSummary("Summary");
        instance.setTime("Time");

        AWDResponse awdResponse = new AWDResponse();
        awdResponse.setInstance(instance);

        ArrayList<AWDResponse> list = new ArrayList<>();
        list.add(awdResponse);

        Detail detail = new Detail();
        detail.setList(list);
        AwdCreateWorkItemResponse awdCreateWorkItemResponse = mock(AwdCreateWorkItemResponse.class);
        when(awdCreateWorkItemResponse.getDetails()).thenReturn(detail);
        doNothing().when(awdCreateWorkItemResponse).setCode(Mockito.<String>any());
        doNothing().when(awdCreateWorkItemResponse).setDetails(Mockito.<Detail>any());
        doNothing().when(awdCreateWorkItemResponse).setMessage(Mockito.<String>any());
        awdCreateWorkItemResponse.setCode("Code");
        awdCreateWorkItemResponse.setDetails(details);
        awdCreateWorkItemResponse.setMessage("Not all who wander are lost");
        when(aWDWorkItemService.createWorkItem(Mockito.<AwdRequest>any(), Mockito.<String>any()))
                .thenReturn(awdCreateWorkItemResponse);
        doNothing().when(lifeRecurringPaymentTransactionRepository)
                .updatePacSuspendTransactionByPolicyNumberAndPlc(Mockito.<String>any(), Mockito.<String>any());
        doNothing().when(newRelicService).populatePacSuspendResponse(Mockito.<PacSuspendResponse>any());
        doNothing().when(newRelicService).populateInputRequest(Mockito.<PacSuspendRequest>any());

        AwdRequest awdRequest = new AwdRequest();
        awdRequest.setApplicationId("42");
        awdRequest.setCreateInstance(new ArrayList<>());

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
        when(transformSvc.getPacSuspendResponse(Mockito.<String>any(), Mockito.<PacSuspendRequest>any()))
                .thenReturn(pacSuspendResponse);
        when(transformSvc.getAWDRequest(Mockito.<PacSuspendRequest>any(), Mockito.<String>any())).thenReturn(awdRequest);
        doNothing().when(validateSvc).validateRequest(Mockito.<PacSuspendRequest>any());
        doNothing().when(validateSvc).validateResponse(Mockito.<AwdCreateWorkItemResponse>any());

        PacSuspendRequest pacSuspendRequest = new PacSuspendRequest();
        pacSuspendRequest.setAdminSystemId("42");
        pacSuspendRequest.setAppId("42");
        pacSuspendRequest.setModeOfFrequency("Mode Of Frequency");
        pacSuspendRequest.setPlc("Plc");
        pacSuspendRequest.setPolicyNumber("42");
        pacSuspendRequest.setSourceSystem("Source System");

        // Act
        PacSuspendResponse actualSuspendPacSetUpResult = pacSuspendServiceImpl.suspendPacSetUp(pacSuspendRequest);

        // Assert
        verify(awdCreateWorkItemResponse).getDetails();
        verify(awdCreateWorkItemResponse).setCode(eq("Code"));
        verify(awdCreateWorkItemResponse).setDetails(isA(Detail.class));
        verify(awdCreateWorkItemResponse).setMessage(eq("Not all who wander are lost"));
        verify(lifeRecurringPaymentTransactionRepository).updatePacSuspendTransactionByPolicyNumberAndPlc(eq("42"),
                eq("Plc"));
        verify(newRelicService).populateInputRequest(isA(PacSuspendRequest.class));
        verify(newRelicService).populatePacSuspendResponse(isA(PacSuspendResponse.class));
        verify(transformSvc).getAWDRequest(isA(PacSuspendRequest.class), eq("NO"));
        verify(transformSvc).getPacSuspendResponse(eq("42"), isA(PacSuspendRequest.class));
        verify(validateSvc).validateRequest(isA(PacSuspendRequest.class));
        verify(validateSvc).validateResponse(isA(AwdCreateWorkItemResponse.class));
        verify(aWDWorkItemService).createWorkItem(isA(AwdRequest.class), eq("42"));
        assertSame(pacSuspendResponse, actualSuspendPacSetUpResult);
    }
}
