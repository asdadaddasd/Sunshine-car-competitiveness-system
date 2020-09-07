//创建jzl_test库
create database jzl_test;
//创建分区表
use jzl_test;
create table log(pickuptime string,cityName string,address string,airporName string,car string,supplier string,price double,useTime date,distance double,flag int)
partitioned by (year int,month  int,day int)
row format delimited fields terminated by '\t';
//导入数据
load data inpath "'$str1'" into table jzl_test.log partition(year="'$year'",month="'$month'",day="'$day'")
//删除表
drop table log;
//查询
select * from log

//获得每个城市5千米距离送机价格最低供应商

select flag,cityname,address fromadd,airporname toadd,car,supplier,price,usetime from log b
join (select  flag f,cityname name,address a,supplier s,min(price) p,year,month,day from log
where distance <= 5
group by year,month,day,flag,cityname,address,supplier) a
on a.name = cityname and a.p = price and a.a = address and a.s = supplier and a.f = flag
where a.year = b.year and a.year =  cast(from_unixtime(unix_timestamp(),"yyyy") as int)
and b.month = a.month and a.month = cast(from_unixtime(unix_timestamp(),"MM") as int)
and a.day = b.day and a.day = 25 cast(from_unixtime(unix_timestamp(),"dd") as int)

//获得当天首汽约车不具有竞争力的信息
//翻译：找出首汽约车不是最低价格的信息
select flag,cityname,address fromadd,car,airporname toadd,supplier,price,usetime from log b
join(select flag f,cityname n,address a,car c,min(price) p,year,month,day  from log
group by year,month,day,flag,cityname,address,car) a
on a.n = cityname and a.c = car and a.a = address
where supplier = "首汽约车" and a.p <price  and a.f = flag
and a.year = b.year and a.year =  cast(from_unixtime(unix_timestamp(),"yyyy") as int)
and b.month = a.month and a.month = cast(from_unixtime(unix_timestamp(),"MM") as int)
and a.day = b.day and a.day = 25 cast(from_unixtime(unix_timestamp(),"dd") as int)
//获得当天中该路线中没有首汽约车的信息
//首汽约车订单
select cityname c,airporname air,address a,supplier s,usetime from log
where supplier = "首汽约车"
group by usetime,cityname,airporname,address,supplier
//所有订单
select cityname,airporname,address from log
group by cityname,airporname,address
//*************************************************************
select flag,cityname,airporname toadd,address fromadd,usetime from (
select year,month,day,flag,cityname,airporname,address,usetime from log
group by year,month,day,flag,usetime,cityname,airporname,address)  a
left join (
select year,month,day,flag f,cityname c,airporname air,address a,supplier s from log
where supplier = "首汽约车"
group by year,month,day,flag,cityname,airporname,address,supplier)  b
on a.cityname = c and a.airporname = air and a.address = b.a and b.f = a.flag
where b.s is null
and a.year = b.year and a.year =  cast(from_unixtime(unix_timestamp(),"yyyy") as int)
and b.month = a.month and a.month = cast(from_unixtime(unix_timestamp(),"MM") as int)
and a.day = b.day and a.day = 25cast(from_unixtime(unix_timestamp(),"dd") as int)-1

//查询当天中，同一路线、不同时间、同一个车型，携程价格不等的信息
select distinct a.airporname toadd,a.address fromadd,a.car,a.price,a.pickuptime,b.price,b.pickuptime from log a
join log b
on a.airporname = b.airporname and a.address = b.address and a.car = b.car and a.supplier = "首汽约车" and a.supplier = b.supplier
where a.price != b.price and a.pickuptime < b.pickuptime 
and a.year = b.year and a.year =  cast(from_unixtime(unix_timestamp(),"yyyy") as int)
and b.month = a.month and a.month = cast(from_unixtime(unix_timestamp(),"MM") as int)
and a.day = b.day and a.day = cast(from_unixtime(unix_timestamp(),"dd") as int)-1



//查询每月中，统一名称，同一路线、同一服务商、同一用车时间（时分相同），用车价格不同

select distinct a.airporname toadd,a.address fromadd,a.car,a.price,a.pickuptime from log a
join log b
on a.airporname = b.airporname and a.address = b.address and a.car = b.car and a.supplier = b.supplier and a.`month` = b.`month` 
where a.price != b.price and split(a.pickuptime, ':')[0] = split(b.pickuptime, ':')[0] and split(a.pickuptime, ':')[1] = split(b.pickuptime, ':')[1]
and a.year = b.year and a.year =  cast(from_unixtime(unix_timestamp(),"yyyy") as int)
and b.month = a.month and a.month = cast(from_unixtime(unix_timestamp(),"MM") as int)

//获得不同服务商的车辆占比

select  supplier,concat(round(nu/num, 4) * 100, '%') por from
(select num,nu,supplier from 
(select count(*) num from log) a,
(select count(car) nu,supplier  from log
group by supplier) b) c



