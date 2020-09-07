package com.bd2001;

import com.alibaba.fastjson.JSON;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
public class SqApplication {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/climb_channel_order";
    static final String USER = "root";
    static final String PASS = "root";
    static private Connection conn = null;
    static private Statement stmt = null;
    public static List<ChannelOrderInfo> list1 = new ArrayList<>();

    public static List log() throws Exception{
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        // 打开链接
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        // 执行查询
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT * FROM ctrip_param_info";
        ResultSet rs = stmt.executeQuery(sql);
        // 展开结果集数据库
        List<List<ChannelOrderInfo>> list2 = new ArrayList<>();

        while(rs.next()){
            int flag = 18; //17 接机   18  送机

            int id = rs.getInt("id");
            int city_id = rs.getInt("city_id");
            String city_name = rs.getString("city_name");
            String airport_name = rs.getString("airport_name");
            String loccd = rs.getString("loccd");
            int locsubcd = rs.getInt("locsubcd");
            String airport_longitude = rs.getString("airport_longitude");
            String airport_latitude = rs.getString("airport_latitude");
            String address = rs.getString("address");
            String longitude = rs.getString("longitude");
            String latitude = rs.getString("latitude");
            String distance = rs.getString("distance");
            String create_time = rs.getString("create_time");

            CtripParamInfo paramInfo = new CtripParamInfo();
            paramInfo.setAddress(address);
            paramInfo.setCityId(city_id);
            paramInfo.setLoccd(loccd);
            paramInfo.setLocsubcd(locsubcd);
            paramInfo.setLatitude(latitude);
            paramInfo.setLongitude(longitude);
            String json = CtripHttpClientUtil.connetCtrip(
                    flag, "2020-08-25 17:00:00",paramInfo );
            //System.out.println("aaaa"+json);
            CtripResultInfo resultInfo = JSON.parseObject(json, CtripResultInfo.class);
            list1 = CtripResultJsonUtil.inverse(new Date(),
                            address,
                            airport_name,
                            "2020-08-25 17:00:00",
                            "接送机",
                            city_name,
                            resultInfo,flag);
            //System.out.println("===");
            if (list1 != null)
            {
                list1.forEach((info)->{
                    if(info != null){
                        System.out.println("info"+info);
                    }
                });
            }

            list2.add(list1);
    }
        // 完成后关闭
        rs.close();
        stmt.close();
        conn.close();
        return list2;
    }
}
