#!/bin/bash
newtime=`date +%Y%m%d`
str=$newtime
if [ $str -eq 2020824 ]
then
	echo "一样"
fi
cp /home/hadoop/jzl-logs/$str /home/hadoop/flume-jzl-logs
