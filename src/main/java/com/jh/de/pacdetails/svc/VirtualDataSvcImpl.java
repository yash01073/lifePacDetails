package com.jh.de.pacdetails.svc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jh.de.pacdetails.model.entity.billing.TPolicyPacInfoDividend;
import com.jh.de.pacdetails.model.request.DividendRequest;
import com.jh.de.pacdetails.model.request.PacInfoRequest;
import com.jh.de.pacdetails.model.response.PacInfoResult;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Profile({"!prod"})
@Slf4j
@Service("VirtualDataSvc")
public class VirtualDataSvcImpl implements VirtualDataSvc {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Map<String, TPolicyPacInfoDividend> dividendResponseMap;
    @Autowired
    private Map<String, PacInfoResult> pacResponseMap;
    @Value("classpath:pac-data.json")
    private Resource pacResource;
    @Value("classpath:dividend-data.json")
    private Resource dividendResource;

    @Override
    public List<PacInfoResult> getPacInfoRecords(PacInfoRequest request) {
        String requestKey = "";
        if(StringUtils.isNotBlank(request.getPlc())) {
            requestKey = request.getPolicyNumber()+request.getPlc();
        } else {
            requestKey = request.getPolicyNumber();
        }
        PacInfoResult result = pacResponseMap.get(requestKey);
        if(result == null) { // try to find by matching key
            for(String key: pacResponseMap.keySet()) {
                if(key.contains(requestKey)){
                    result = pacResponseMap.get(key);
                    break;
                }
            }
        }
        if(result != null) {
            return List.of(result);
        }
        return null;
    }

    @Override
    public List<TPolicyPacInfoDividend> getDividendRecords(DividendRequest request) {
        String requestKey = "";
        if(StringUtils.isNotBlank(request.getDividendAccountType())) {
            requestKey = request.getPolicyNumber()+request.getPlc()+request.getDividendAccountType();
        } else{
            requestKey = request.getPolicyNumber()+request.getPlc();
        }
        TPolicyPacInfoDividend result = dividendResponseMap.get(requestKey);
        if(result == null) { // try to find by matching key
            for(String key: dividendResponseMap.keySet()) {
                if(key.contains(requestKey)) {
                    result = dividendResponseMap.get(key);
                    break;
                }
            }
        }
        if(result != null) {
            return List.of(result);
        }
        return null;
    }

    @PostConstruct
    public void init() throws IOException {
        log.info("Loading Virtual Data");
        byte[] jSonBinaryData = dividendResource.getContentAsByteArray();
        List<TPolicyPacInfoDividend> list = objectMapper.readValue(jSonBinaryData, new TypeReference<List<TPolicyPacInfoDividend>>() {});
        list.forEach(div -> {
            dividendResponseMap.put(div.getPolicyNumber()+div.getPlc(), div);
            dividendResponseMap.put(div.getPolicyNumber()+div.getPlc()+div.getDividendTypes(), div);
        });
        jSonBinaryData = pacResource.getContentAsByteArray();
        List<PacInfoResult> pacList = objectMapper.readValue(jSonBinaryData, new TypeReference<List<PacInfoResult>>() {});
        pacList.forEach(pac -> {
            pacResponseMap.put(pac.getPolicyNumber()+pac.getPLC(), pac);
            pacResponseMap.put(pac.getPolicyNumber(), pac);
        });
    }
}