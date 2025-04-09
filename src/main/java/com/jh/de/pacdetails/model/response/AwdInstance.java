package com.jh.de.pacdetails.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwdInstance {
    public String summary;
    public String permission;
    public String state;
    public String priority;
    public String time;
    public String date;
    @JsonProperty("@id")
    public String id;

}
