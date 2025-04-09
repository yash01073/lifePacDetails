package com.jh.de.pacdetails.constants;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JHConstants {

    /*
    list of policies (categories) that were listed as eligible for the “Pay with Dividends”:
    Fixed with Divs:
        PNO (whole life, not term)
        MDO
        VLI
        PS Whole Life
        PT Whole Life

    Allowed PLC - 1, 2, 3, 8, 100, 102, 998
    */

    public static final String PAC_NOT_FOUND = "Pac info not found";
    public static final String DIV_NOT_FOUND = "Dividend info not found";
    public static final String PAC_NOT_EXIST = "Latest PAC info does not exist for ";
    public static final String UNKNOWN_ERROR = "Unknown error";
    public static final String VIRTUAL = "virtual";
    public static final String PTR = "ptr";
    public static final String BILLING = "billing";
    public static final String PT_PS = "PT, PS";

    public static final String APIM_TOKENSERVICE_ERROR = "APIM Token Service is not Responding";

    public static final String VPASONE_TOKEN_ERROR = "VPasOne Token Service is not Responding";

    public static final String VPASONE_SERVICE_ERROR = "VPasOne Payment Service is not Responding";

    public static final String CONVERTED_POLICY_ERROR = "Converted policy Service is not Responding";

    public static final String PDCO_SERVICE_ERROR = "PDCO Payment Service is not Responding";

    public static final String INVALID_MODE_OF_FREQUENCY_ERROR = "Invalid mode of frequency value";

    public static final String REQUEST_ERROR = "Please check request";

    public static final String AWD_SERVICE_ERROR = "AWD Service is not Responding";

    public static final String POLICY_WITH = "Policy: ";

    public static final String TXN_ID_WITH = " Transaction Id: ";

    public static final String ERRORED_WITH_EX = " errored with exception: ";

    public static final String VPASONE_PROCESSING_RESTRICTED = "VPasOne Processing is restricted for Non-2V or Flexible premium policy";

    public static final String ALL = "ALL";

    public static final String POLICY = " policy: ";

    public static final String AND_PLC = " and PLC: ";

    public static final String PDCO = "PDCO";

    public static final String LIFE = "LIFE";

    public static final String ADMIN_SYSTEM_2V = "2V";

    @Getter
    public enum ProductLineCodes {
        PLC_001("001", "PNO", "PNO", "PNO - Premium Notice Ordinary - Ordinary Life Insurance",
                "PNO", "PNO - Ordinary Life Insurance", "N", "Y", "N", true),
        PLC_002("002", "PNO", "ACC", "PNO - Premium Notice Ordinary - accumulation funds",
                "Accum Fund(PNO)", "Accumulation Funds", "N", "Y", "N", true),
        PLC_003("003", "VLI", "VLI", "VLI  -  Variable Life Insurance",
                "VLI", "VLI - Variable Life", "N", "Y", "N", true),
        PLC_004("004", "", "", "",
                "","", "Y", "", "", false),
        PLC_005("005", "", "", "",
                "","", "Y", "", "", false),
        PLC_006("006", "", "", "",
                "","", "", "", "", false),
        PLC_007("007", "", "", "",
                "","", "N", "", "", false),
        PLC_008("008", "MDO", "MDO", "MDO - Monthly Debit Ordinary -  Ordinary Life Insurance",
                "MDO","MDO - Ordinary Life Insurance", "N", "N", "N", true),
        PLC_018("018", "", "", "",
                "","", "", "", "", false),
        PLC_019("019", "", "", "",
                "","", "", "", "", false),
        PLC_030("030", "VP", "", "",
                "","MVUJ - McCamish VUL Joint Life", "Y", "N/A", "N", false),
        PLC_031("031", "VP", "", "",
                "","MVUJ - Majestic VUL Joint Life", "Y", "N/A", "N", false),
        PLC_032("032", "VP", "", "",
                "","CUL - Conversion Universal Life", "Y", "N/A", "N", false),
        PLC_050("050", "", "", "",
                "","", "N", "", "", false),
        PLC_051("050", "", "", "",
                "","", "", "", "", false),
        PLC_062("062", "PNO", "OAN", "PNO annuities - fixed annuities",
                "Ordinary Annuity (PNO)","Ordinary Annuity", "N", "Y", "N", false),
        PLC_064("064", "PNO", "IDP", "PNO Personal Health Insurance",
                "IDH","PNO Personal Health Insurance", "N", "N", "N ", false),
        PLC_065("065", "MDO", "IDM", "MDO Personal Health Insurance",
                "IDH","MDO Personal Health Insurance", "N", "N", "N", false),
        PLC_080("080", "PNO", "PNO", "",
                "PNO","PNO - IPL Term", "N", "N", "N", false),
        PLC_094("094", PT_PS, "", "",
                "","Survivorship Term and Whole Life", "N", "N", "N", false),
        PLC_095("095", "", "", "",
                "","", "Y", "", "", false),
        PLC_096("096", "", "", "",
                "","", "Y", "", "", false),
        PLC_097("097", "", "", "",
                "","", "Y", "", "", false),
        PLC_098("098", "", "", "",
                "","", "Y", "", "", false),
        PLC_099("095", "", "", "",
                "","", "", "", "", false),
        PLC_100("100", PT_PS, "", "",
                "","Manulife Single Life Whole Life", "N", "N", "N", true),
        PLC_101("101", PT_PS, "", "",
                "","Manulife Single Life Term", "N", "N", "N", false),
        PLC_102("102", "PS", "", "",
                "","Manulife Single Premium Whole Life", "N/A", "N/A", "Y", true),
        PLC_104("104", "", "", "",
                "","", "", "", "", false),
        PLC_105("105", "PS", "", "",
                "","Manulife Whole Life", "N/A", "N/A", "Y", true),
        PLC_106("106", "", "", "",
                "","", "N", "", "", false),
        PLC_107("107", "", "", "",
                "","", "Y", "", "", false),
        PLC_109("109", "", "", "",
                "","", "Y", "", "", false),
        PLC_110("110", "", "", "",
                "","", "Y", "", "", false),
        PLC_111("111", "", "", "",
                "","", "Y", "", "", false),
        PLC_113("113", "", "", "",
                "","", "Y", "", "", false),
        PLC_114("114", "", "", "",
                "","", "N", "", "", false),
        PLC_121("121", "", "", "",
                "","", "N", "", "", false),
        PLC_124("124", "", "", "",
                "","", "", "", "", false),
        PLC_125("125", "", "", "",
                "","", "Y", "", "", false),
        PLC_129("129", "2V", "", "",
                "","", "Y", "", "", false),
        PLC_998("998", "PS", "", "",
                "","Term and Whole Life", "N", "N", "N", true) ;

        private final String plcCode;
        private final String adminSystem;
        private final String shortDesc;
        private final String r2KDesc;
        private final String cswbProdDesc;
        private final String jhImPlcDesc;
        private final String flexiblePremium;
        private final String billLoanInterest;
        private final String coliBoli;
        private final Boolean isDividend;

        ProductLineCodes(String plcCode, String adminSystem, String shortDesc, String r2KDesc, String cswbProdDesc, String jhImPlcDesc,
                         String flexiblePremium, String billLoanInterest, String coliBoli, Boolean isDividend) {
            this.plcCode = plcCode;
            this.adminSystem = adminSystem;
            this.shortDesc = shortDesc;
            this.r2KDesc = r2KDesc;
            this.cswbProdDesc = cswbProdDesc;
            this.jhImPlcDesc = jhImPlcDesc;
            this.flexiblePremium = flexiblePremium;
            this.billLoanInterest = billLoanInterest;
            this.coliBoli = coliBoli;
            this.isDividend = isDividend;
        };

        public static Optional<ProductLineCodes> getProductLineCodeByValue(String plc) {
            return Arrays.stream(ProductLineCodes.values())
                    .filter(plcCode -> plcCode.getPlcCode().equals(plc))
                    .findFirst();
        };

        public static List<String> getProductLineCodesByDividend() {
            return Arrays.stream(ProductLineCodes.values())
                    .filter(plc -> Boolean.TRUE.equals(plc.getIsDividend()))
                    .map(ProductLineCodes::getPlcCode)
                    .collect(Collectors.toList());
        };

        public static List<String> getProductLineCodes() {
            return Arrays.stream(ProductLineCodes.values())
                    .map(ProductLineCodes::getPlcCode)
                    .collect(Collectors.toList());
        };


    }

    @Getter
    public enum DividendTypes {
        DIV_00("00"),DIV_1("1"),DIV_3("3"),DIV_5("5"),
        DIV_DEP("DIV_DEP"),DIV_HAI("HAI"),DIV_IND_PR49("IND_PR49"),
        DIV_IND_SETDIV("IND_SETDIV"),DIV_LUMPSUMDA("LUMPSUMDA"),DIV_N("N"),
        DIV_OFFSET("OFFSET"),DIV_P("P"),DIV_PDA("PDA"),DIV_PUA("PUA"),DIV_RAP("RAP"),DIV_U("U"),DIV_VPU("VPU");
        private String type;
        DividendTypes(String type){
            this.type = type;
        }
        public static List<String> getDividendTypes() {
            return Arrays.stream(DividendTypes.values())
                    .map(DividendTypes::getType)
                    .collect(Collectors.toList());
        };
    }

    @Getter
    public enum DividendAccountTypes {
        DIV_DEP,HAIDIVDA,IND_PR49,IND_SETDIV,LUMPSUMDA,OFFSETDA,PREMIUMDA,RAPDIVDA;
        public static List<String> getDividendAccountTypes() {
            return Arrays.stream(DividendAccountTypes.values())
                    .map(DividendAccountTypes::name)
                    .collect(Collectors.toList());
        };
    }

    public static final String modifyStr(String str, char ch, int repeat) {
        return StringUtils.repeat(ch, repeat - str.length()) + str;
    }

    public static final boolean isFixedByPlc(String plc){
        return Arrays.stream(ProductLineCodes.values())
                .filter(plcCode -> plcCode.getPlcCode().equals(modifyStr(plc, '0', 3)))
                .findFirst()
                .map(lineCodes -> "N".equals(lineCodes.flexiblePremium)).orElse(false);
    }

}
