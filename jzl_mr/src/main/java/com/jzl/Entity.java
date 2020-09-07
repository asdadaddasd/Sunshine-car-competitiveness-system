package com.jzl;

import lombok.Data;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
@Data
public class Entity implements WritableComparable<Entity> {
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
    private double price;
    private String supplier;
    private String car;
    private String time;
    private String usetime;
    private int flag;
    public String toString(){
        return usetime+"\t"+cityName+"\t"+address+"\t"+airportName+"\t"+car+"\t"+supplier+"\t"+price+"\t"+time+"\t"+distance+"\t"+flag;
    }
    @Override
    public int compareTo(Entity o) {
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        //out.writeInt(id);
       // out.writeInt(cityId);
        out.writeUTF(cityName);
        out.writeUTF(airportName);
      //  out.writeUTF(loccd);
      //  out.writeInt(locsubcd);
      //  out.writeUTF(airportLongitude);
       // out.writeUTF(airportLatitude);
        out.writeUTF(address);
        //out.writeUTF(longitude);
       // out.writeUTF(latitude);
        out.writeDouble(distance);
        out.writeUTF(car);
        out.writeDouble(price);
        out.writeUTF(supplier);
        out.writeUTF(time);
        out.writeInt(flag);
        out.writeUTF(usetime);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
     //   this.id = in.readInt();
      //  this.cityId = in.readInt();
        this.cityName = in.readUTF();
        this.airportName = in.readUTF();
      //  this.loccd = in.readUTF();
       // this.locsubcd = in.readInt();
       // this.airportLongitude = in.readUTF();
       // this.airportLatitude = in.readUTF();
        this.address = in.readUTF();
      //  this.longitude = in.readUTF();
      //  this.latitude = in.readUTF();
        this.distance = in.readDouble();
        this.car = in.readUTF();
        this.price = in.readDouble();
        this.supplier = in.readUTF();
        this.time = in.readUTF();
        this.flag = in.readInt();
        this.usetime = in.readUTF();
    }
}
