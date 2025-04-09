package com.jh.de.pacdetails.svc.pacsuspend;

//import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.response.AwdCreateWorkItemResponse;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
//import com.jh.de.pacdetails.model.response.VPasOneResponse;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;
import com.jh.de.pacdetails.svc.NewRelicService;
import com.jh.de.pacdetails.svc.TransformSvc;
import com.jh.de.pacdetails.svc.ValidateSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import static com.jh.de.pacdetails.constants.JHConstants.VPASONE_PROCESSING_RESTRICTED;

@Service
public class PacSuspendServiceImpl implements PacSuspendService {

    //@Autowired
    //private VPasOneService vPasOneService;

    @Autowired
    private TransformSvc transformSvc;

    @Autowired
    private AWDWorkItemService awdWorkItemService;
    
    @Autowired
    private NewRelicService newRelicService;

    @Autowired
    private ValidateSvc validateSvc;

    @Autowired
    private LifeRecurringPaymentTransactionRepository lifeRecurringPaymentTransactionRepository;

    @Override
    public PacSuspendResponse suspendPacSetUp(PacSuspendRequest pacSuspendRequest) {
        newRelicService.populateInputRequest(pacSuspendRequest);
        validateSvc.validateRequest(pacSuspendRequest);
        AwdCreateWorkItemResponse awdResponse = createWorkItem(pacSuspendRequest);
        return getPacSuspendResponse(pacSuspendRequest, awdResponse);
    }

    private AwdCreateWorkItemResponse createWorkItem(PacSuspendRequest pacSuspendRequest) {
        AwdRequest awdRequest = transformSvc.getAWDRequest(pacSuspendRequest,"NO");
        AwdCreateWorkItemResponse awdResponse = awdWorkItemService.createWorkItem(awdRequest,pacSuspendRequest.getPolicyNumber());
        validateSvc.validateResponse(awdResponse);
        return awdResponse;
    }

    private PacSuspendResponse getPacSuspendResponse(PacSuspendRequest pacSuspendRequest, AwdCreateWorkItemResponse awdResponse) {
        lifeRecurringPaymentTransactionRepository.updatePacSuspendTransactionByPolicyNumberAndPlc(pacSuspendRequest.getPolicyNumber(),
                pacSuspendRequest.getPlc());
        PacSuspendResponse pacSuspendResponse = transformSvc.getPacSuspendResponse(awdResponse.getDetails().getList().get(0).getInstance().getId()
                ,pacSuspendRequest);
        newRelicService.populatePacSuspendResponse(pacSuspendResponse);
        return pacSuspendResponse;
    }

    /*@Override
    public PacSuspendResponse suspendPacSetUp(PacSuspendRequest pacSuspendRequest) {
        VPasOneResponse vPasOneResponse;
        vPasOneResponse = getvPasOneResponse(pacSuspendRequest);
        newRelicService.populateVPasOneResponse(vPasOneResponse);
        AwdRequest awdRequest = transformSvc.getAWDRequest(pacSuspendRequest,vPasOneResponse.getRetVal().getSuccessful());
        AwdCreateWorkItemResponse awdResponse = awdWorkItemService.createWorkItem(awdRequest,pacSuspendRequest.getPolicyNumber());
        lifeRecurringPaymentTransactionRepository.updatePacSuspendTransactionByPolicyNumberAndPlc(pacSuspendRequest.getPolicyNumber(),
                pacSuspendRequest.getPlc());
        return transformSvc.getPacSuspendResponse(awdResponse.getDetails().getList().get(0).getInstance().getId()
                ,pacSuspendRequest,vPasOneResponse);
    }
    private VPasOneResponse getvPasOneResponse(PacSuspendRequest pacSuspendRequest) {
        if("2V".equals(pacSuspendRequest.getAdminSystemId()) &&
            JHConstants.isFixedByPlc(pacSuspendRequest.getPlc())){
            return vPasOneService.suspendPacForVPasOne(pacSuspendRequest);
        }else{
            return vPasOneService.createErrorVpasOneResponse(VPASONE_PROCESSING_RESTRICTED,"400");
        }
    }*/
}
