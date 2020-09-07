package com.jzl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClearDriver {
    public static void main(String[] args) throws Exception{

        Configuration configuration = new Configuration();
        //获得任务对象
        Job job = Job.getInstance(configuration,"jzl-clear-job");
        //设置驱动类
        job.setJarByClass(ClearDriver.class);
        //设置map阶段的类
        job.setMapperClass(ClearMapper.class);
        //设置最终的输出类型
        job.setOutputKeyClass(Entity.class);
        job.setOutputValueClass(NullWritable.class);
        //设置输入  输出路径
//        FileInputFormat.addInputPath(job,new Path( args[0]));
//        FileOutputFormat.setOutputPath(job,new Path( args[1]));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String nyr = dateFormat.format(date);
       // FileInputFormat.addInputPath(job,new Path("/flume/jzl-log/"+nyr));
        FileInputFormat.addInputPath(job,new Path("/flume/jzl-log/"+nyr));

        String newtime2 = calendar.get(Calendar.YEAR)+"-"
                +(calendar.get(Calendar.MONTH)+1)+"-"
                +calendar.get(Calendar.DAY_OF_MONTH);
        FileOutputFormat.setOutputPath(job,new Path( "/jzl-clear/"+newtime2));
        //提交任务
        boolean result = job.waitForCompletion(true);
        System.out.println(result?"成功":"失败!");

    }

}
