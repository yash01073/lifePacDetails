package com.jh.de.pacdetails.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldValues {
    @JsonProperty("FieldValue")
    private List<FieldValue> FieldValue;
}
