package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AwdRequest {



    @JsonProperty("createInstance")
    private List<Instance> createInstance;


    @JsonProperty("ApplicationID")
    private String ApplicationId; //application id



}
