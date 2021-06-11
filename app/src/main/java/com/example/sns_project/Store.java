package com.example.sns_project;

public class Store {
    private static int i=0;
    private String index;
    private String SIGUN_NM;
    private String SIGUN_CD;
    private String FACLT_DIV_NM;
    private String MARKET_NM;
    private String TELNO;
    private String REFINE_LOTNO_ADDR;
    private String REFINE_ROADNM_ADDR;
    private String REFINE_ZIP_CD;
    private String REFINE_WGS84_LOGT;
    private String REFINE_WGS84_LAT;

    public Store() {
        this.index = ""+i;
        i++;
        this.SIGUN_NM = null;
        this.SIGUN_CD = null;
        this.FACLT_DIV_NM = null;
        this.MARKET_NM = null;
        this.TELNO = null;
        this.REFINE_LOTNO_ADDR = null;
        this.REFINE_ROADNM_ADDR = null;
        this.REFINE_ZIP_CD = null;
        this.REFINE_WGS84_LOGT = null;
        this.REFINE_WGS84_LAT = null;
    }

    //    public Store(String SIGUN_NM, String SIGUN_CD, String FACLT_DIV_NM, String MARKET_NM, String TELNO, String REFINE_LOTNO_ADDR, String REFINE_ROADNM_ADDR, String REFINE_ZIP_CD, String REFINE_WGS84_LOGT, String REFINE_WGS84_LAT) {
//        this.SIGUN_NM = SIGUN_NM;
//        this.SIGUN_CD = SIGUN_CD;
//        this.FACLT_DIV_NM = FACLT_DIV_NM;
//        this.MARKET_NM = MARKET_NM;
//        this.TELNO = TELNO;
//        this.REFINE_LOTNO_ADDR = REFINE_LOTNO_ADDR;
//        this.REFINE_ROADNM_ADDR = REFINE_ROADNM_ADDR;
//        this.REFINE_ZIP_CD = REFINE_ZIP_CD;
//        this.REFINE_WGS84_LOGT = REFINE_WGS84_LOGT;
//        this.REFINE_WGS84_LAT = REFINE_WGS84_LAT;
//    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSIGUN_NM() {
        return SIGUN_NM;
    }

    public void setSIGUN_NM(String SIGUN_NM) {
        this.SIGUN_NM = SIGUN_NM;
    }

    public String getSIGUN_CD() {
        return SIGUN_CD;
    }

    public void setSIGUN_CD(String SIGUN_CD) {
        this.SIGUN_CD = SIGUN_CD;
    }

    public String getFACLT_DIV_NM() {
        return FACLT_DIV_NM;
    }

    public void setFACLT_DIV_NM(String FACLT_DIV_NM) {
        this.FACLT_DIV_NM = FACLT_DIV_NM;
    }

    public String getMARKET_NM() {
        return MARKET_NM;
    }

    public void setMARKET_NM(String MARKET_NM) {
        this.MARKET_NM = MARKET_NM;
    }

    public String getTELNO() {
        return TELNO;
    }

    public void setTELNO(String TELNO) {
        this.TELNO = TELNO;
    }

    public String getREFINE_LOTNO_ADDR() {
        return REFINE_LOTNO_ADDR;
    }

    public void setREFINE_LOTNO_ADDR(String REFINE_LOTNO_ADDR) {
        this.REFINE_LOTNO_ADDR = REFINE_LOTNO_ADDR;
    }

    public String getREFINE_ROADNM_ADDR() {
        return REFINE_ROADNM_ADDR;
    }

    public void setREFINE_ROADNM_ADDR(String REFINE_ROADNM_ADDR) {
        this.REFINE_ROADNM_ADDR = REFINE_ROADNM_ADDR;
    }

    public String getREFINE_ZIP_CD() {
        return REFINE_ZIP_CD;
    }

    public void setREFINE_ZIP_CD(String REFINE_ZIP_CD) {
        this.REFINE_ZIP_CD = REFINE_ZIP_CD;
    }

    public String getREFINE_WGS84_LOGT() {
        return REFINE_WGS84_LOGT;
    }

    public void setREFINE_WGS84_LOGT(String REFINE_WGS84_LOGT) {
        this.REFINE_WGS84_LOGT = REFINE_WGS84_LOGT;
    }

    public String getREFINE_WGS84_LAT() {
        return REFINE_WGS84_LAT;
    }

    public void setREFINE_WGS84_LAT(String REFINE_WGS84_LAT) {
        this.REFINE_WGS84_LAT = REFINE_WGS84_LAT;
    }

    public String findIndex(String name){
        if(this.MARKET_NM.equals(name)){
            return this.index;
        }else return null;
    }
}
