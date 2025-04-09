package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.constants.JHConstants;
import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.RequestHeaderBean;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.repo.billing.TPolicyPacInfoDividendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@Service
public class DividendSvcImpl implements DividendSvc {
    @Autowired
    private TransformSvc transformSvc;
    @Autowired
    private ValidateSvc validateSvc;
    @Autowired
    private NewRelicService newRelicService;
    @Autowired
    private List<DividendCriteria> dividendCriteria;
    @Autowired
    private RequestHeaderBean requestHeaderBean;

    @Override
    public DividendResponse getDividendDetails(DividendRequest dividendRequest) {
        newRelicService.populateInputRequest(dividendRequest);
        validateSvc.validateRequest(dividendRequest);
        List<TPolicyPacInfoDividend> dividendRecords = new ArrayList<>();
        for(DividendCriteria criteria: dividendCriteria) {
            if(criteria.isApplicable(dividendRequest)) {
                dividendRecords = criteria.getDividendRecords(dividendRequest);
                if (CollectionUtils.isEmpty(dividendRecords)) {
                    throw new ApiException.NotFoundException(JHConstants.DIV_NOT_FOUND, Arrays.asList(getErrorMessage(dividendRequest)));
                }
                return getResponse(dividendRecords);
            }
        }
        throw new ApiException(JHConstants.UNKNOWN_ERROR);
    }

    private DividendResponse getResponse(List<TPolicyPacInfoDividend> dividendRecords) {
        DividendResponse response = transformSvc.getDividendResponse(dividendRecords);
        if(requestHeaderBean.getIsVirtual()) {
            response.setCode(JHConstants.VIRTUAL);
        }
        newRelicService.populateResponse(response);
        return response;
    }
}

abstract class DividendCriteria {
    @Autowired(required = false)
    protected VirtualDataSvc virtualDataSvc;
    @Lazy
    @Autowired
    protected TPolicyPacInfoDividendRepository dividendRepo;
    abstract Boolean isApplicable(DividendRequest dr);
    abstract List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr);
    @Autowired
    protected RequestHeaderBean requestHeaderBean;
}

@Order(1)
@Component
class ByVirtualData extends DividendCriteria {

    @Override
    public Boolean isApplicable(DividendRequest dr) {
        return requestHeaderBean.getIsVirtual();
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr) {
        if(virtualDataSvc != null) {
            return virtualDataSvc.getDividendRecords(dr);
        } else {
            return List.of();
        }
    }
}
@Order(2)
@Component
class ByPolicyAndPlc extends DividendCriteria {

    @Override
    public Boolean isApplicable(DividendRequest dr) {
        return CollectionUtils.isEmpty(dr.getDividendTypes())
                && CollectionUtils.isEmpty(dr.getDividendAccountTypes());
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr) {
        return dividendRepo.findByPolicyNumberAndPlc(dr.getPolicyNumber(), dr.getPlc());
    }
}

@Order(3)
@Component
class ByPolicyAndPlcAndDividendType extends DividendCriteria {
    @Override
    public Boolean isApplicable(DividendRequest dr) {
        return !CollectionUtils.isEmpty(dr.getDividendTypes())
                && CollectionUtils.isEmpty(dr.getDividendAccountTypes());
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr) {
        return dividendRepo.findByPolicyNumberAndPlcAndDividendType(dr.getPolicyNumber(),
                dr.getPlc(),
                dr.getDividendTypes());
    }
}

@Order(4)
@Component
class ByPolicyAndPlcAndDividendAccountType extends DividendCriteria {
    @Override
    public Boolean isApplicable(DividendRequest dr) {
        return CollectionUtils.isEmpty(dr.getDividendTypes())
                && !CollectionUtils.isEmpty(dr.getDividendAccountTypes());
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr) {
        return dividendRepo.findByPolicyNumberAndPlcAndDividendAccountType(
                dr.getPolicyNumber(),
                dr.getPlc(),
                dr.getDividendAccountTypes());
    }
}

@Order(4)
@Component
class ByPolicyAndPlcAndDividendTypeAndDividendAccountType extends DividendCriteria {
    @Override
    public Boolean isApplicable(DividendRequest dr) {
        return !CollectionUtils.isEmpty(dr.getDividendTypes())
                && !CollectionUtils.isEmpty(dr.getDividendAccountTypes());
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest dr) {
        return dividendRepo.findByPolicyNumberAndPlcAndDividendTypeAndDividendAccountType(
                dr.getPolicyNumber(),
                dr.getPlc(),
                dr.getDividendTypes(),
                dr.getDividendAccountTypes());
    }
}
