#!/bin/bash
for file in $(hdfs dfs -find "/jzl-clear/" -name "*000")
do
        str1=$file
	str2=${str1:11}  #截取
	str3=${str2%/*}  #截取
	str4=${str3//-/ }  #截取
	str5=`date +"%Y-%m-%d"` #当前时间
	flag=1
	year=''
	month=''
	day=''
	echo $str3
	echo $str4
	if [ `$str5` = `$str3` ]
	then
		for i in $str4;
		do
			case $flag in
 			   1) year=$i  
 		   	;;
 		  	 2) month=$i
 		  	 ;;
 		  	 3) day=`expr $i - 1`
 		  	 ;;
  		  	*)  echo 'default'
   		 	;;
		 	esac
			flag=`expr $flag + 1`
		done
		echo $year
		echo $month
		echo $day
		echo $str1
		hql='load data inpath "'$str1'" into table jzl_test.log partition(year="'$year'",month="'$month'",day="'$day'");'
		echo $hql
		hive -e "$hql"
	fi
done
