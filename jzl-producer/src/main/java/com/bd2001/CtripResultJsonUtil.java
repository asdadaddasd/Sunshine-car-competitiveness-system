package com.bd2001;

import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CtripResultJsonUtil {
   // private static Logger logger = Logger.getLogger(CtripResultJsonUtil.class);
    private static final String CHANNEL_NAME = "携程接送机";

    // 转换方法
    public static List<ChannelOrderInfo> inverse(Date climbTime, String beginAddr, String endAddr, String useTime,
                                                 String productName, String cityName, CtripResultInfo resultInfo,int flag) {

        if (resultInfo == null || resultInfo.getResult() == null) {
            //logger.error("爬取结果为空!");
            return null;
        }
        // 存放结果
        List<ChannelOrderInfo> channelOrderInfoList = new ArrayList<>();
        // 这里面存储了预估的时间以及公里数
        String tips = resultInfo.getResult().getExt().getTips();
        if(tips == null){
            return null;
        }
        System.out.println(tips);
        Pattern p = Pattern.compile("\\d{1,}");
        Matcher m = p.matcher(tips);
        List<Double> tipsList = new ArrayList<>();
        while (m.find()) {
            tipsList.add(Double.parseDouble(m.group()));
        }
        double estimateTimeLength = 0;
        double estimateDistance = 0;
        if (!CollectionUtils.isEmpty(tipsList)) {
            estimateTimeLength = tipsList.get(0);
            estimateDistance = tipsList.get(1);
        }
        // 获得车型及其对应的价格pk
        List<CtripResultInfo.Group> groupList = resultInfo.getResult().getGrps();
        Map<String, String> groupMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(groupList)) {
            groupList.forEach((group) -> {
                if (!CollectionUtils.isEmpty(group.getSort())) {
                    group.getSort().forEach((pk) -> {
                        groupMap.put(pk, group.getNm());
                    });
                }
                if (!CollectionUtils.isEmpty(group.getBusgrps())) {
                    group.getBusgrps().forEach((busGroup)->{
                        System.out.println(CollectionUtils.isEmpty(group.getSort()));
                        if (!CollectionUtils.isEmpty(busGroup.getSort())) {
                            busGroup.getSort().forEach((pk) -> {
                                groupMap.put(pk, group.getNm() + busGroup.getNm());
                            });
                        }
                    });
                }
            });
        }

        // 获得平台信息
        List<CtripResultInfo.Vnd> vndList = resultInfo.getResult().getVnds();
        Map<String, String> vndsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(vndList)) {
            for (CtripResultInfo.Vnd vnd : vndList) {
                vndsMap.put(vnd.getId(), vnd.getNm());
            }
        }
        ChannelOrderInfo channelOrderInfo = null;
        // 获得各个平台的价格
        List<CtripResultInfo.Product> productList = resultInfo.getResult().getPrds();
        if (!CollectionUtils.isEmpty(productList)) {
            for (CtripResultInfo.Product product : productList) {
                channelOrderInfo = new ChannelOrderInfo();
                channelOrderInfo.setEstimateDistance(estimateDistance);
                channelOrderInfo.setEstimateTimeLength(estimateTimeLength);
                channelOrderInfo.setChannelName(CHANNEL_NAME);
                channelOrderInfo.setCityName(cityName);
                channelOrderInfo.setProductName(productName);
                channelOrderInfo.setUseTime(getDateStr(useTime));
                channelOrderInfo.setBeginAddr(beginAddr);
                channelOrderInfo.setEndAddr(endAddr);
                channelOrderInfo.setCarType(groupMap.get(product.getPk()));
                channelOrderInfo.setBrandName(vndsMap.get(product.getVndcode()) + product.getValuedisnm());
                channelOrderInfo.setEstimatePrice(product.getShowamt());
                channelOrderInfo.setClimbTime(climbTime);
                channelOrderInfo.setFlag(flag);
                channelOrderInfoList.add(channelOrderInfo);
            }
        }

        return channelOrderInfoList;

    }

    private static Date getDateStr(String dateTtime) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormate.parse(dateTtime);
        } catch (ParseException e) {
            //logger.error("时间类型转换出错，param:" + dateTtime);
        }
        return null;
    }
}
