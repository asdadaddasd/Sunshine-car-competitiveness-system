package com.bd2001;

import java.util.Date;

public class ChannelOrderInfo {
    private int channelOrderId;
    private String channelName;
    private String cityName;
    private String productName;
    private Date useTime;
    private String beginAddr;
    private String endAddr;
    private String carType;
    private String brandName;
    private double estimatePrice;
    private double estimateDistance;
    private double estimateTimeLength;
    private Date climbTime;
    private  Date createTime;
    private int flag;
    public int getFlag(){
        return flag;
    }
    public void setFlag(int flag){
        this.flag = flag;
    }
    public int getChannelOrderId() {
        return channelOrderId;
    }
    public void setChannelOrderId(int channelOrderId) {
        this.channelOrderId = channelOrderId;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getBeginAddr() {
        return beginAddr;
    }
    public void setBeginAddr(String beginAddr) {
        this.beginAddr = beginAddr;
    }
    public String getEndAddr() {
        return endAddr;
    }
    public void setEndAddr(String endAddr) {
        this.endAddr = endAddr;
    }
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public double getEstimatePrice() {
        return estimatePrice;
    }
    public void setEstimatePrice(double estimatePrice) {
        this.estimatePrice = estimatePrice;
    }
    public double getEstimateDistance() {
        return estimateDistance;
    }
    public void setEstimateDistance(double estimateDistance) {
        this.estimateDistance = estimateDistance;
    }
    public double getEstimateTimeLength() {
        return estimateTimeLength;
    }
    public void setEstimateTimeLength(double estimateTimeLength) {
        this.estimateTimeLength = estimateTimeLength;
    }
    public Date getClimbTime() {
        return climbTime;
    }
    public void setClimbTime(Date climbTime) {
        this.climbTime = climbTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUseTime() {
        return useTime;
    }
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
    @Override
    public String toString() {
        return "channelOrderId="+channelOrderId+",channelName="+channelName+",cityName="
                +cityName+",productName="+productName+",useTime="+useTime+",beginAddr="+beginAddr
                +",endAddr="+endAddr+",carType="+carType+",brandName="+brandName+",estimatePrice="
                +estimatePrice+",estimateDistance="+estimateDistance+",estimateTimeLength="
                +estimateTimeLength+",climbTime="+climbTime+",createTime="+createTime+",flag="+flag;
    }
}
