package com.jh.de.pacdetails.model.entity.billing;

import lombok.Data;

import java.io.Serializable;

@Data
public class DividendKey implements Serializable {
    private String PolicyNumber;
    private String Plc;
    private String DividendTypes;
    private String DividendAcctId;
    private String Coverageid;
    private String Prdcoverageid;
}
