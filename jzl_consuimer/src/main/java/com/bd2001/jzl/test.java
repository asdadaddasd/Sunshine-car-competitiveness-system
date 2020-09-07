package com.bd2001.jzl;

import java.sql.*;

public class test {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/climb_channel_order";
    static final String USER = "root";
    static final String PASS = "root";
    static private Connection conn = null;
    static  private Statement stmt = null;
    public static void main(String[] args) {
         try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
          //  sql = "SELECT id, city_id, city_name,airport_name,loccd, locsubcd,airport_longitude,,airport_latitude,address ,longitude,latitude,distance ,create_time FROM ctrip_param_info";
          sql = "SELECT * FROM ctrip_param_info";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                int flag = 17; //17 接机   18  送机

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

                System.out.println(id);
                System.out.println(city_id);
                System.out.println(city_name);
                System.out.println(airport_name);
                System.out.println(loccd);
                System.out.println(locsubcd);
                System.out.println(airport_longitude);
                System.out.println(airport_latitude);
                System.out.println(address);
                System.out.println(longitude);
                System.out.println(latitude);
                System.out.println(distance);
                System.out.println(create_time);


            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(
                SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        }
}

