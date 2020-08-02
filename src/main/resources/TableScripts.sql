create table attendance_history
(
	mac_address varchar(50),
    rf_id 		varchar(50),
    system_date datetime
);
select * from attendance_history;

insert into attendance_history
	values('hello','hello',current_time())