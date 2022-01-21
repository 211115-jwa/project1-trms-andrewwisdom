drop table employee cascade;
drop table reimbursement cascade;
drop table department cascade;
drop table comment cascade;
drop table user_role cascade;
drop table status cascade;
drop table grading_format cascade;
drop table event_type cascade;

create table user_role (
		role_id serial primary key,
		role_name varchar(50) not null
);

create table status (
		status_id serial primary key,
		status_name varchar(50) not null,
		approver varchar(40) not null
);

create table grading_format (
		format_id serial primary key,
		format_name varchar(100) not null,
		example varchar(250)
);

create table event_type (
		type_id serial primary key,
		type_name varchar(50) not null,
		percent_coverage real not null
);

create table comment (
		comment_id serial primary key,
		--req_id integer references reimbursement,
		--approver_id integer references employee,
		comment_text varchar(250) not null,
		sent_at timestamp not null
);

create table reimbursement (
		req_id serial primary key,
		--emp_id integer references employee,
		event_date date,
		event_time time,
		location varchar(40) not null,
		description varchar(250) not null,
		cost real not null,
		grading_format_id integer not null references grading_format,
		event_type_id integer not null references event_type,
		status_id integer not null references status,
		submitted_at timestamp not null
);

create table department (
		dept_id serial primary key,
		dept_name varchar(50) not null
		--dept_head_id integer not null references employee
);

create table employee (
	emp_id serial primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	username varchar(50) not null,
	passwd varchar(50) not null,
	role_id integer not null references user_role,
	funds real not null,
	supervisor_id integer references employee
	--dept_id integer not null references department
);



alter table comment add column approver_id integer references employee;
alter table comment add column req_id integer references reimbursement;

alter table reimbursement add column emp_id integer references employee;

alter table department add column dept_head_id integer references employee;

alter table employee add column dept_id integer references department;