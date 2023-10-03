use lv1;
create table Employee (
	id INT auto_increment primary key,
	code VARCHAR(50),
	name VARCHAR(50),
	email VARCHAR(50),
	phone VARCHAR(50),
	age INT
);
insert into Employee (id, code, name, email, phone, age) values (1, 'KMEI', 'Ysabel Flement', 'yflement0@vistaprint.com', '204-356-4951', 21);
insert into Employee (id, code, name, email, phone, age) values (2, 'KMGM', 'Alric Suttell', 'asuttell1@amazon.co.jp', '355-858-7159', 24);
insert into Employee (id, code, name, email, phone, age) values (3, 'YGBI', 'Lucien Danbury', 'ldanbury2@ucoz.ru', '528-685-5502', 26);
insert into Employee (id, code, name, email, phone, age) values (4, 'FZMA', 'Saxe Yakunikov', 'syakunikov3@google.com.br', '594-974-4474', 25);
insert into Employee (id, code, name, email, phone, age) values (5, 'VTSR', 'Claretta Dawson', 'cdawson4@ezinearticles.com', '440-462-6316', 22);
insert into Employee (id, code, name, email, phone, age) values (6, 'KHBR', 'Althea Haszard', 'ahaszard5@elegantthemes.com', '155-918-8250', 21);
insert into Employee (id, code, name, email, phone, age) values (7, 'LIPB', 'Junie Androletti', 'jandroletti6@state.gov', '995-602-9591', 23);
insert into Employee (id, code, name, email, phone, age) values (8, 'EISG', 'Elnore Pagel', 'epagel7@uiuc.edu', '698-620-5288', 23);
insert into Employee (id, code, name, email, phone, age) values (9, 'SCAN', 'Grantham Peniman', 'gpeniman8@geocities.com', '926-373-6404', 21);
insert into Employee (id, code, name, email, phone, age) values (10, 'FHAW', 'Kelly Josephy', 'kjosephy9@wikipedia.org', '933-660-9580', 24);
select * from employee;

Delimiter //
Create procedure FindEmployeeByDTO(
id_input int,
age_input int)
begin
	select * from employee where id = id_input and age = age_input;
end //
Delimiter ;
call FindEmployeeByDTO(1,21);
