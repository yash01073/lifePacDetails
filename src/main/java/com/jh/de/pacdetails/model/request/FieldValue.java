package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldValue {
    @JsonProperty("name")
    private String Name;

    @JsonProperty("value")
    private String Value;
}
