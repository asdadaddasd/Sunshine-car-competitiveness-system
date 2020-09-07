package com.bd2001;

import java.util.Date;

public class CtripParamInfo {
    private int id;//主键
    private int cityId;//城市id
    private String cityName;//城市名称
    private String airportName;//机场名称
    private String loccd;//机场三字码
    private int locsubcd;//机场编号
    private String airportLongitude;//机场经度
    private String airportLatitude;//机场维度
    private String address;//上下车地点
    private String longitude;//经度
    private String latitude;//维度
    private double distance;//距离
    private Date createTime;//创建时间
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getAirportName() {
        return airportName;
    }
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
    public String getLoccd() {
        return loccd;
    }
    public void setLoccd(String loccd) {
        this.loccd = loccd;
    }
    public int getLocsubcd() {
        return locsubcd;
    }
    public void setLocsubcd(int locsubcd) {
        this.locsubcd = locsubcd;
    }
    public String getAirportLongitude() {
        return airportLongitude;
    }
    public void setAirportLongitude(String airportLongitude) {
        this.airportLongitude = airportLongitude;
    }
    public String getAirportLatitude() {
        return airportLatitude;
    }
    public void setAirportLatitude(String airportLatitude) {
        this.airportLatitude = airportLatitude;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "CtripParamInfo [id=" + id + ", cityId=" + cityId + ", cityName=" + cityName + ", airportName="
                + airportName + ", loccd=" + loccd + ", locsubcd=" + locsubcd + ", airportLongitude=" + airportLongitude
                + ", airportLatitude=" + airportLatitude + ", address=" + address + ", longitude=" + longitude
                + ", latitude=" + latitude + ", distance=" + distance + ", createTime=" + createTime + "]";
    }
}
