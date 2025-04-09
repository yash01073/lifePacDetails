package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instance {
    @JsonProperty("typeName")
    private String typeName; //Work type

    @JsonProperty("statusName")
    private String statusName; //Work status

    @JsonProperty("FieldValues")
    private FieldValues FieldValues; //Metadata

    @JsonProperty("businessAreaName")
    private String businessAreaName; //Business Area


}
