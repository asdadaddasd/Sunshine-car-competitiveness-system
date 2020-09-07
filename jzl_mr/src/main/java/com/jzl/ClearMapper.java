package com.jzl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class ClearMapper extends Mapper<LongWritable, Text,Entity,NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String time = calendar.get(Calendar.YEAR)+"-"
                +(calendar.get(Calendar.MONTH)+1)+"-"
                +calendar.get(Calendar.DAY_OF_MONTH);
        String[] str = value.toString().split(",");
        if (str.length != 15){
            return;
        }
        //channelOrderId=0,
        // channelName=携程接送机,
        // cityName=北京,
        // productName=接送机,
        // useTime=Wed Aug 26 12:00:00 CST 2020,
        // beginAddr=国泰生活广场（机场店）,
        // endAddr=首都国际机场T3航站楼,
        // carType=8-45座巴士30座,
        // brandName=巴士专车,
        // estimatePrice=708.0,
        // estimateDistance=3.0,
        // estimateTimeLength=7.0,
        // climbTime=Mon Aug 24 15:39:30 CST 2020,
        // createTime=null
        Entity entity = new Entity();
        String[] cityName = str[2].split("=");
        String[] u = str[4].split("=");
        String[] useTime = u[1].split(" ");
        String[] beginAddr = str[5].split("=");
        String[] endAddr = str[6].split("=");
        String[] carType = str[7].split("=");
        String[] brandName = str[8].split("=");
        String[] estimatePrice = str[9].split("=");
        String[] estimateDistance = str[10].split("=");
        String[] estimateTimeLength = str[11].split("=");
        String[] flag = str[14].split("=");

        entity.setCityName(cityName[1]);   //城市名
        entity.setAddress(beginAddr[1]);   //接机地点
        entity.setAirportName(endAddr[1]); //机场名称
        entity.setCar(carType[1]);         //车型
        entity.setSupplier(brandName[1]);       //供应商
        entity.setPrice(Double.parseDouble(estimatePrice[1]));   //价格
        entity.setTime(time);               //时间
        entity.setDistance(Double.parseDouble(estimateDistance[1])); //距离
        entity.setFlag(Integer.parseInt(flag[1]));
        entity.setUsetime(useTime[3]);
        context.write(entity,NullWritable.get());

    }
}
