package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PacTransactionInfoResponse;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoRepository;
import com.jh.de.pacdetails.repo.ptr.LifeRecurringPaymentTransactionRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

import static com.jh.de.pacdetails.constants.JHConstants.AND_PLC;
import static com.jh.de.pacdetails.constants.JHConstants.POLICY;
import static com.jh.de.pacdetails.constants.JHConstants.PDCO;

@Slf4j
@Service
public class PacTransactionSvcImpl implements PacTransactionSvc {

    @Autowired
    private List<PacCriteria> pacCriteria;
    @Autowired
    private ValidateSvc validateSvc;
    @Autowired
    private NewRelicService newRelicService;
    @Autowired
    private TransformSvc transformSvc;

    public PacTransactionInfoResponse getPacTransactionDetails(PacInfoRequest request) {
        log.info("pac input request: "+request);
        newRelicService.populateInputRequest(request);
        validateSvc.validateRequest(request);
        for (PacCriteria criteria : pacCriteria) {
            if (criteria.isApplicable(request)) {
                List<PacInfoResult> results = criteria.getResponse(request);
                if(CollectionUtils.isEmpty(results)) {
                    throw new ApiException.NotFoundException(JHConstants.PAC_NOT_FOUND, Arrays.asList(criteria.getErrorMessage(request)));
                }
                return getResponse(results, criteria.getTgtSrc());
            }
        }
        throw new ApiException(JHConstants.UNKNOWN_ERROR);
    }

    private PacTransactionInfoResponse getResponse(List<PacInfoResult> results, String tgtSrc) {
        PacTransactionInfoResponse response = transformSvc.getPacTransactionInfoResponse(tgtSrc);
        response.setResults(results);
        newRelicService.populateResponse(response);
        return response;
    }
}

@Getter
@Setter
abstract class PacCriteria {
    protected String tgtSrc = JHConstants.PTR;
    @Autowired
    protected TransformSvc transformSvc;
    @Autowired
    protected ValidateSvc validateSvc;
    @Autowired
    private NewRelicService newRelicService;
    @Autowired
    protected LifeRecurringPaymentTransactionRepository ptrRepo;
    @Lazy
    @Autowired
    protected TPolicyPacInfoRepository billingRepo;
    @Autowired
    protected RequestHeaderBean requestHeaderBean;
    @Value("${spring.datasource.sqlmi.billing.enable}")
    protected Boolean connectToBillingDb;
    @Autowired(required = false)
    protected VirtualDataSvc virtualDataSvc;
    abstract Boolean isApplicable(PacInfoRequest request);
    abstract List<PacInfoResult> getResponse(PacInfoRequest request);
    abstract String getErrorMessage(PacInfoRequest request);
    @Autowired
    PDCOPaymentDelegatorService pdcoPaymentDelegatorService;
}

@Order(1)
@Component
class PacByVirtualData extends PacCriteria {
    @Override
    Boolean isApplicable(PacInfoRequest request) {
        return requestHeaderBean.getIsVirtual();
    }
    @Override
    List<PacInfoResult> getResponse(PacInfoRequest request) {
        if(virtualDataSvc != null) {
            tgtSrc = JHConstants.VIRTUAL;
            return virtualDataSvc.getPacInfoRecords(request);
        } else {
            return List.of();
        }
    }

    @Override
    String getErrorMessage(PacInfoRequest request) {
        return JHConstants.PAC_NOT_EXIST+" virtual data policy: "+request.getPolicyNumber();
    }
}

@Order(2)
@Component
class PacByCallTypeAsPdco extends PacCriteria {
    @Override
    Boolean isApplicable(PacInfoRequest request) {
        return PDCO.equalsIgnoreCase(request.getCallType());
    }
    @Override
    List<PacInfoResult> getResponse(PacInfoRequest request) {
        validateSvc.validateRequest(request);
        tgtSrc = JHConstants.PDCO;
        return pdcoPaymentDelegatorService.getPacInfoRecords(request);
    }
    @Override
    String getErrorMessage(PacInfoRequest request) {
        return JHConstants.PAC_NOT_EXIST+ POLICY +request.getPolicyNumber()+ AND_PLC +request.getPlc();
    }
}

@Order(3)
@Component
class PacByPolicyAndPlc extends PacCriteria {
    @Override
    Boolean isApplicable(PacInfoRequest request) {
        return StringUtils.isNotEmpty(request.getPolicyNumber())
                && StringUtils.isNotEmpty(request.getPlc());
    }
    @Override
    List<PacInfoResult> getResponse(PacInfoRequest request) {
        LifeRecurringPaymentTransaction ptr = ptrRepo.findTransactionByPolicyNumberAndPlc(request.getPolicyNumber(), request.getPlc());
        if(ptr != null) {
            return List.of(transformSvc.getPacInfoResult(ptr));
        }
        if(Boolean.TRUE.equals(connectToBillingDb)) {
            String mPolicyNumber = JHConstants.modifyStr(request.getPolicyNumber(), '0', 9);
            String mPlc = JHConstants.modifyStr(request.getPlc(), '0', 3);
            TPolicyPacInfo pacInfo = billingRepo.findTransactionByPolicyNumberAndPlc(mPolicyNumber, mPlc);
            if(pacInfo != null) {
                tgtSrc = JHConstants.BILLING;
                validateSvc.validateResponse(pacInfo);
                return List.of(transformSvc.getPacInfoResult(pacInfo,request.getPolicyNumber()));
            }
        }
        return List.of();
    }
    @Override
    String getErrorMessage(PacInfoRequest request) {
        return JHConstants.PAC_NOT_EXIST+ POLICY +request.getPolicyNumber()+ AND_PLC +request.getPlc();
    }
}

@Order(4)
@Component
class PacByPolicy extends PacCriteria {
    @Override
    Boolean isApplicable(PacInfoRequest request) {
        return StringUtils.isNotEmpty(request.getPolicyNumber())
                && StringUtils.isEmpty(request.getPlc());
    }
    @Override
    List<PacInfoResult> getResponse(PacInfoRequest request) {
        LifeRecurringPaymentTransaction ptr = ptrRepo.findTransactionByPolicyNumber(request.getPolicyNumber());
        if(ptr != null) {
            return List.of(transformSvc.getPacInfoResult(ptr));
        }
        if(Boolean.TRUE.equals(connectToBillingDb)) {
            String mPolicyNumber = JHConstants.modifyStr(request.getPolicyNumber(), '0', 9);
            TPolicyPacInfo pacInfo = billingRepo.findTransactionByPolicyNumber(mPolicyNumber);
            if(pacInfo != null) {
                tgtSrc = JHConstants.BILLING;
                validateSvc.validateResponse(pacInfo);
                return List.of(transformSvc.getPacInfoResult(pacInfo,request.getPolicyNumber()));
            }
        }
        return List.of();
    }
    @Override
    String getErrorMessage(PacInfoRequest request) {
        return JHConstants.PAC_NOT_EXIST+ POLICY +request.getPolicyNumber();
    }
}
@Order(5)
@Component
class PacByTransactionId extends PacCriteria {
    @Override
    Boolean isApplicable(PacInfoRequest request) {
        return StringUtils.isNotEmpty(request.getTransactionId());
    }
    @Override
    List<PacInfoResult> getResponse(PacInfoRequest request) {
        LifeRecurringPaymentTransaction ptr = ptrRepo.findTransactionByTransactionId(request.getTransactionId());
        if(ptr != null) {
            return List.of(transformSvc.getPacInfoResult(ptr));
        }
        return List.of();
    }
    @Override
    String getErrorMessage(PacInfoRequest request) {
        return JHConstants.PAC_NOT_EXIST+" transaction id: "+request.getTransactionId();
    }
}