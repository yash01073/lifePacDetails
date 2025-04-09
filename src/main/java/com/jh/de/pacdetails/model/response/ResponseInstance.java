package com.jh.de.pacdetails.model.response;

import com.jh.de.pacdetails.model.response.AwdInstance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInstance {
    public AwdInstance awdInstance;
    public String status;
    public Object workFlow;
    public String queue;
    public String priority;
    public String priorityIncrease;
    public Object suspended;
    public String assignedTo;
    public String instanceType;
}
