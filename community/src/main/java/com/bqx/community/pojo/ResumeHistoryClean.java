package com.bqx.community.pojo;

public class ResumeHistoryClean {
    /**
     * 现居住地
     */
    private  String presentAddress;
    /**
     * 简历编号
     */
    private  String resumeId;
    /**
     *  省
     */
    private  Integer presentProvinceId;
    /**
     *  市
     */
    private  Integer presentCityId;
    /**
     *  区
     */
    private  Integer presentAreaId;
    /**
     * 户口所在地
     */
    private  String nativePlace;
    /**
     * 简历工作地点
     */
    private  String resumeWorkplace;

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getPresentProvinceId() {
        return presentProvinceId;
    }

    public void setPresentProvinceId(Integer presentProvinceId) {
        this.presentProvinceId = presentProvinceId;
    }

    public Integer getPresentCityId() {
        return presentCityId;
    }

    public void setPresentCityId(Integer presentCityId) {
        this.presentCityId = presentCityId;
    }

    public Integer getPresentAreaId() {
        return presentAreaId;
    }

    public void setPresentAreaId(Integer presentAreaId) {
        this.presentAreaId = presentAreaId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getResumeWorkplace() {
        return resumeWorkplace;
    }

    public void setResumeWorkplace(String resumeWorkplace) {
        this.resumeWorkplace = resumeWorkplace;
    }
}
