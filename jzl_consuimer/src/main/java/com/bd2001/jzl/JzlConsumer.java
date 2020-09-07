package com.bd2001.jzl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Component
public class JzlConsumer {
    Calendar calendar = null;
    String path = null;
    PrintWriter printWriter = null;
    //得到long类型当前时间
    long l = System.currentTimeMillis();
    //new日期对象
    Date date = new Date(l);
    //转换提日期输出格式
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    String nyr = dateFormat.format(date);
    @KafkaListener(topics = "jzl-log")
    public void myReceive(String message){

        try {
            if (path == null){
                path = "d://"+nyr;
                printWriter = new PrintWriter(new FileWriter(path,true));
            }else if (!checkFile()){
                printWriter.close();
                printWriter = new PrintWriter(new FileWriter(path,true));
            }
            printWriter.println(message);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkFile() {
        String newPath = "d://"+nyr;
        boolean result = newPath.equals(path);
        if (!result){
            path = newPath;
        }

        return result;
    }

}
