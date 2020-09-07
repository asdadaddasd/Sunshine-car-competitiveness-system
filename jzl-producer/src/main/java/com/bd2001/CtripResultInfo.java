package com.bd2001;

import java.util.List;

public class CtripResultInfo {
    private Args  args;
    private Result result;
    private String rencode;//响应状态码
    private String renmessage;//响应描述信息
    public Args getArgs() {
        return args;
    }

    public void setArgs(Args args) {
        this.args = args;
    }

    public String getRencode() {
        return rencode;
    }

    public void setRencode(String rencode) {
        this.rencode = rencode;
    }

    public String getRenmessage() {
        return renmessage;
    }

    public void setRenmessage(String renmessage) {
        this.renmessage = renmessage;
    }

    @Override
    public String toString() {
        return "CtripResultInfo [result=" + result + "]";
    }

    public Result getResult() {
        if (result == null){
            return null;
        }
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Args{
        private String udt;//用车时间
        private String locnm;//机场名
        private String loccd;//机场三字码
        private String locsubcd;//机场编码
        private String poiaddr;//上下车地点
        public String getUdt() {
            return udt;
        }
        public void setUdt(String udt) {
            this.udt = udt;
        }
        public String getLocnm() {
            return locnm;
        }
        public void setLocnm(String locnm) {
            this.locnm = locnm;
        }
        public String getLoccd() {
            return loccd;
        }
        public void setLoccd(String loccd) {
            this.loccd = loccd;
        }
        public String getLocsubcd() {
            return locsubcd;
        }
        public void setLocsubcd(String locsubcd) {
            this.locsubcd = locsubcd;
        }
        public String getPoiaddr() {
            return poiaddr;
        }
        public void setPoiaddr(String poiaddr) {
            this.poiaddr = poiaddr;
        }
        @Override
        public String toString() {
            return "Args [udt=" + udt + ", locnm=" + locnm + ", loccd=" + loccd + ", locsubcd=" + locsubcd
                    + ", poiaddr=" + poiaddr + "]";
        }

    }
    public static class Result {
        private Ext ext; // 预估时间 里程
        private List<Group> grps; // 车型信息
        private List<Vnd> vnds;// 平台信息
        private List<Product> prds;// 产品价格信息

        @Override
        public String toString() {
            return "Result [ext=" + ext + ", grps=" + grps + ", vnds=" + vnds + ", prds=" + prds + "]";
        }

        public Ext getExt() {
            if (ext == null){
                return null;
            }
            return ext;
        }

        public void setExt(Ext ext) {
            this.ext = ext;
        }

        public List<Group> getGrps() {
            return grps;
        }

        public void setGrps(List<Group> grps) {
            this.grps = grps;
        }

        public List<Vnd> getVnds() {
            return vnds;
        }

        public void setVnds(List<Vnd> vnds) {
            this.vnds = vnds;
        }

        public List<Product> getPrds() {
            return prds;
        }

        public void setPrds(List<Product> prds) {
            this.prds = prds;
        }
    }

    public static class Product {
        @Override
        public String toString() {
            return "Product [pk=" + pk + ", vndcode=" + vndcode + ", valuedisnm=" + valuedisnm + ", oriamt=" + oriamt
                    + ", showamt=" + showamt + ", cashbkamt=" + cashbkamt + "]";
        }

        private String pk;
        private String vndcode;
        private String valuedisnm;// 活动产品名
        private double oriamt;// 原价
        private double showamt;// 展示价格
        private double cashbkamt;// 返现价格

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }

        public String getVndcode() {
            return vndcode;
        }

        public void setVndcode(String vndcode) {
            this.vndcode = vndcode;
        }

        public String getValuedisnm() {
            return valuedisnm;
        }

        public void setValuedisnm(String valuedisnm) {
            this.valuedisnm = valuedisnm;
        }

        public double getOriamt() {
            return oriamt;
        }

        public void setOriamt(double oriamt) {
            this.oriamt = oriamt;
        }

        public double getShowamt() {
            return showamt;
        }

        public void setShowamt(double showamt) {
            this.showamt = showamt;
        }

        public double getCashbkamt() {
            return cashbkamt;
        }

        public void setCashbkamt(double cashbkamt) {
            this.cashbkamt = cashbkamt;
        }
    }

    public static class Vnd {
        private String id;// 约车平台id
        private String nm;// 约车平台名称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        @Override
        public String toString() {
            return "Vnd [id=" + id + ", nm=" + nm + "]";
        }

    }

    public static class Group {
        private int id;
        private String nm;// 车型
        private List<String> sort;// 各平台价格pk
        private List<BusGroup> busgrps;

        @Override
        public String toString() {
            return "Group [id=" + id + ", nm=" + nm + ", sort=" + sort + ", busgrps=" + busgrps + "]";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }

        public List<BusGroup> getBusgrps() {
            return busgrps;
        }

        public void setBusgrps(List<BusGroup> busgrps) {
            this.busgrps = busgrps;
        }
    }

    /**
     *车辆类型 比如 大巴5座 大巴10座
     * @author sulitao
     */
    public static class BusGroup {
        private String nm;
        private int seat;// 座位数
        private List<String> sort;// 各平台价格id

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public List<String> getSort() {
            return sort;
        }

        public void setSort(List<String> sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return "BusGroup [nm=" + nm + ", seat=" + seat + ", sort=" + sort + "]";
        }

    }

    public static class Ext {

        @Override
        public String toString() {
            return "Ext [tips=" + tips + ", km=" + km + "]";
        }

        private String tips;// 预计时间 公里数
        private double km;// 实际公里数

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }

        public double getKm() {
            return km;
        }

        public void setKm(double km) {
            this.km = km;
        }
    }
}
