create schema remoteacademy;

use remoteacademy;

create table student(
student_id varchar(36),
password varchar(30) not null,
name varchar(50) not null,
mobile varchar(20) not null unique,
email varchar(50) not null unique,
dob date,
gender varchar(10),
country varchar(50),
state varchar(50),
city varchar(50),
address varchar(200),
pincode varchar(6),
class varchar(20),
course varchar(50),
institute TEXT,
image varchar(100),
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(student_id)
);

create table teacher(
teacher_id varchar(36),
password varchar(30) not null,
name varchar(50) not null,
mobile varchar(20) not null unique,
email varchar(50) not null,
dob date,
gender varchar(10),
country varchar(50),
state varchar(50),
city varchar(50),
address varchar(200),
pincode varchar(6),
occupation varchar(50),
qualification varchar(100),
experience varchar(20),
image varchar(100),
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(teacher_id)
);

create table subject(
subject_id varchar(36),
teacher_id varchar(36) not null,
duration varchar(15),
timing varchar(100),
subject_name varchar(100) not null,
description TEXT,
tution_fee varchar(10),
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(subject_id),
foreign key(teacher_id)REFERENCES teacher(teacher_id)
);

create table batch(
batch_id varchar(36),
teacher_id varchar(36) not null,
name varchar(50) not null unique,
registration_start_date date,
batch_start_date date,
duration varchar(15),
timing time,
description TEXT,
batch_fee varchar(10),
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(batch_id),
foreign key(teacher_id)REFERENCES teacher(teacher_id)
);

create table subject_query(
subject_query_id varchar(36),
subject_id varchar(36) not null,
student_id varchar(36) not null,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(subject_query_id),
foreign key(subject_id)REFERENCES subject(subject_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table batch_query(
batch_query_id varchar(36),
batch_id varchar(36) not null,
student_id varchar(36) not null,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(batch_query_id),
foreign key(batch_id)REFERENCES batch(batch_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table subject_rating
(
subject_rating_id varchar(36),
subject_id varchar(36) not null,
student_id varchar(36) not null,
points int NOT NULL,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(subject_rating_id),
foreign key(subject_id)REFERENCES subject(subject_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table subject_joiners(
subject_joiner_id varchar(36),
subject_id varchar(36) not null,
student_id varchar(36) not null,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(subject_joiner_id),
foreign key(subject_id)REFERENCES subject(subject_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table batch_joiners(
batch_joiner_id varchar(36),
batch_id varchar(36) not null,
student_id varchar(36) not null,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(batch_joiner_id),
foreign key(batch_id)REFERENCES batch(batch_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table subject_query_response(
subject_query_response_id varchar(36),
subject_query_id varchar(36) not null,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(subject_query_response_id),
foreign key(subject_query_id)REFERENCES subject_query(subject_query_id)
);

create table batch_query_response(
batch_query_response_id varchar(36),
batch_query_id varchar(36) not null,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(batch_query_response_id),
foreign key(batch_query_id)REFERENCES batch_query(batch_query_id)
);

create table batch_rating(
batch_rating_id varchar(36),
batch_id varchar(36) not null,
student_id varchar(36) not null,
points int NOT NULL,
message TEXT,
created_by varchar(50),
modified_by varchar(50),
created_on date,
modified_on date,
is_deleted boolean default false,
primary key(batch_rating_id),
foreign key(batch_id)REFERENCES batch(batch_id),
foreign key(student_id)REFERENCES student(student_id)
);

create table admin
(
admin_id varchar(36),
name varchar(36) not null,
password varchar(36) not null,
email varchar(50) not null unique,
mobile varchar(20) not null unique,
primary key(admin_id)
);

INSERT INTO admin(admin_id,name,password,email,mobile)
VALUES ('ad001','praveen','3306','ppraveen203@gmail.com','9009604991');