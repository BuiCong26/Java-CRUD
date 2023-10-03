use lv1;
create table tbl_province(
	id int auto_increment primary key,
    name varchar(50) unique not null,
    code varchar(10) not null,
    type varchar(20) not null
);
create table tbl_district(
	id int auto_increment primary key,
    name varchar(50) unique not null,
    code varchar(10) not null,
    type varchar(20) not null,
    province_id int,
    Foreign key (province_id) references tbl_province(id)
);
create table tbl_commune(
	id int auto_increment primary key,
    name varchar(50) unique not null,
    code varchar(10) not null,
    type varchar(20) not null
);

create table tbl_certificate(
	id int auto_increment primary key,
    name varchar(50) not null,
    start_date varchar(20) not null,
    end_date varchar(20) not null,
    degree varchar(10) not null
);
SELECT * FROM tbl_province;
select * from tbl_district;
select * from tbl_commune;
select * from tbl_certificate;
drop table tbl_province;
drop table tbl_district;
drop table tbl_commune;
drop table tbl_certificate;

delete from tbl_district where province_id = 3;

Delimiter $
Create procedure deleteDistrictByProvinceId(province_id int)
begin
delete from tbl_district 
Delimiter /