package com.jh.de.pacdetails.svc;

import com.jh.de.pacdetails.exception.ApiException;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfo;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.entity.ptr.LifeRecurringPaymentTransaction;
import com.jh.de.pacdetails.model.request.AwdRequest;
import com.jh.de.pacdetails.model.request.FieldValue;
import com.jh.de.pacdetails.model.request.Instance;
import com.jh.de.pacdetails.model.request.PacSuspendRequest;
import com.jh.de.pacdetails.model.request.FieldValues;
import com.jh.de.pacdetails.model.response.DividendRecord;
import com.jh.de.pacdetails.model.response.DividendResponse;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import com.jh.de.pacdetails.model.response.PacSuspendResponse;
import com.jh.de.pacdetails.model.response.VPasOneResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jh.de.pacdetails.constants.JHConstants.INVALID_MODE_OF_FREQUENCY_ERROR;
import static com.jh.de.pacdetails.constants.JHConstants.REQUEST_ERROR;


@Service
public class TransformSvcImpl implements TransformSvc {

    @Value("${awdRest.application.id}")
    private String applicationId;

    @Override
    public PacInfoResult getPacInfoResult(LifeRecurringPaymentTransaction ptr) {
        PacInfoResult result = new PacInfoResult();
        result.setId(ptr.getId());
        result.setApplicationId(ptr.getApplicationId());
        result.setError(ptr.getError());
        result.setTransactionId(ptr.getTransactionId());
        result.setBankDetails(getBankDetails(ptr));
        result.setPLC(ptr.getPLC());
        result.setCreatedTime(ptr.getCreatedTime());
        result.setRole(ptr.getRole());
        result.setErrorDetails(ptr.getErrorDetails());
        result.setUUID(ptr.getUUID());
        result.setAdminSystemId(ptr.getAdminSystemId());
        result.setTransactionId(ptr.getTransactionId());
        result.setTokenId(ptr.getTokenId());
        result.setError(ptr.getError());
        result.setPolicyNumber(ptr.getPolicyNumber());
        result.setPaymentDetails(getPaymentDetails(ptr));
        result.setSourceSystem(ptr.getSourceSystem());
        return result;
    }

    @Override
    public PacInfoResult getPacInfoResult(TPolicyPacInfo billing, String policyNumber) {
        PacInfoResult result = new PacInfoResult();
        result.setBankDetails(getBankDetails(billing));
        result.setPLC(billing.getPlc());
        result.setAdminSystemId(billing.getSystemId());
        result.setPolicyNumber(policyNumber);
        result.setPaymentDetails(getPaymentDetails(billing));
        return result;
    }

    @Override
    public DividendResponse getDividendResponse(List<TPolicyPacInfoDividend> records) {
        DividendResponse response = getDividendResponse();
        List<DividendRecord> dividendRecords = new ArrayList<>();
        records.forEach(record -> {
            DividendRecord dividendRecord = new DividendRecord();
            dividendRecord.setDividendAmounts(StringUtils.isBlank(record.getDividendAmounts()) ? "" : record.getDividendAmounts());
            dividendRecord.setDividendTypes(record.getDividendTypes());
            dividendRecord.setDividendAcctId(StringUtils.equalsIgnoreCase("XX", record.getDividendAcctId()) ? "" : record.getDividendAcctId());
            dividendRecord.setPlc(record.getPlc());
            dividendRecord.setPolicyNumber(record.getPolicyNumber());
            dividendRecord.setCoverageId(StringUtils.equalsIgnoreCase("XX", record.getCoverageid()) ? "" : record.getCoverageid());
            dividendRecord.setPolicyCategory(record.getPolicyCategory());
            dividendRecord.setSystemId(record.getSystemId());
            dividendRecord.setUpdateTs(record.getUpdateTs());
            dividendRecord.setPolicyUpdTs(record.getPolicyUpdTs());
            dividendRecord.setPolicyActiveStatus(record.getPolicyActiveStatus());
            dividendRecord.setPrdCoverageId(StringUtils.equalsIgnoreCase("XX",record.getPrdcoverageid()) ? "" : record.getPrdcoverageid());
            dividendRecords.add(dividendRecord);
        });
        response.setResults(dividendRecords);
        return response;
    }
    public AwdRequest getAWDRequest(PacSuspendRequest pacSuspendRequest, String vPasOneSuccess){
        AwdRequest awdRequest = new AwdRequest();
        awdRequest.setApplicationId(applicationId);
        List<Instance> createInstance = new ArrayList<Instance>();
        Instance instance = new Instance();
        instance.setBusinessAreaName("LIFE");
        instance.setStatusName("INDEXED");
        instance.setTypeName("PACSUSPEND");
        List<FieldValue> customAttributesList = new ArrayList<>();
        buildCustomAttribute(customAttributesList, "ACCT", pacSuspendRequest.getPolicyNumber()); //Policy number
        buildCustomAttribute(customAttributesList, "CORG", "Web");
        switch (pacSuspendRequest.getModeOfFrequency()){
            case "AN":
                buildCustomAttribute(customAttributesList, "MODE", "Annual");
                break;
            case "SA":
                buildCustomAttribute(customAttributesList, "MODE", "Semi-Annual");
                break;
            case "QU":
                buildCustomAttribute(customAttributesList, "MODE", "Quarterly");
                break;
            default:
                throw new ApiException.BadRequestException(REQUEST_ERROR, Arrays.asList(INVALID_MODE_OF_FREQUENCY_ERROR));
        }
        /*if("2V".equals(pacSuspendRequest.getAdminSystemId()) && "YES".equals(vPasOneSuccess)){
            buildCustomAttribute(customAttributesList, "ROUT", "AUTOEND");
        }else{
            buildCustomAttribute(customAttributesList, "ROUT", "AUTO");
        }*/
        buildCustomAttribute(customAttributesList, "ROUT", "AUTO");
        FieldValues fieldValues = new FieldValues();
        fieldValues.setFieldValue(customAttributesList);
        instance.setFieldValues(fieldValues);
        createInstance.add(instance);
        awdRequest.setCreateInstance(createInstance);
        return awdRequest;
    }

    private void buildCustomAttribute(List<FieldValue> customAttributesList, String name, String value) {

        FieldValue customAttribute = new FieldValue();

        customAttribute.setName(name);
        customAttribute.setValue(value);

        customAttributesList.add(customAttribute);
    }
    @Override
    public PacSuspendResponse getPacSuspendResponse(String id, PacSuspendRequest request) {
        PacSuspendResponse pacSuspendResponse = new PacSuspendResponse();
        pacSuspendResponse.setCode("workitem");
        pacSuspendResponse.setSuccessful(true);
        pacSuspendResponse.setPlc(request.getPlc());
        pacSuspendResponse.setPolicyNumber(request.getPolicyNumber());
        pacSuspendResponse.setWorkItemId(id);
        return  pacSuspendResponse;
    }

    @Override
    public PacSuspendResponse getPacSuspendResponse(String id, PacSuspendRequest request,
                                                    VPasOneResponse vPasOneResponse) {
        PacSuspendResponse pacSuspendResponse = new PacSuspendResponse();
        if("YES".equals(vPasOneResponse.getRetVal().getSuccessful())){
            pacSuspendResponse.setCode("vpasone");
        }else{
            pacSuspendResponse.setCode("workitem");
        }
        pacSuspendResponse.setSuccessful(true);
        pacSuspendResponse.setPlc(request.getPlc());
        pacSuspendResponse.setPolicyNumber(request.getPolicyNumber());
        pacSuspendResponse.setWorkItemId(id);
        pacSuspendResponse.setVPasOneResponse(vPasOneResponse);
        return  pacSuspendResponse;
    }


}
