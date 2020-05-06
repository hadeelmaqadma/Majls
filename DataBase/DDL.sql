
-- Entity sets
create table Jurist(
 ID varchar(5) primary key ,
 name varchar(25) NOT null
);

create table student(
 ID serial primary key,
 name1 varchar(10) not null ,
 name2 varchar(10),
 name3 varchar(10),
 name4 varchar(15) not null ,
 gender varchar(7) check (gender in('male' , 'female') ) ,
 city  varchar(15),
 street varchar(15)
);

drop table phone;
create table phone(
 ID int references student(ID) on delete cascade ,
 num char(10) unique ,
 primary key(ID, num)
); 

create table book (
 bk_id varchar(7) primary key,
 title varchar(30),
 credit varchar(3)
);
drop  table majls ;
create table majls(
 Mj_id varchar(5) primary key, 
 subject varchar(20),
 mosque varchar(20),
 vol_id varchar(5) references volunteer(ID) 
);
create table lecture(
Mj_id varchar(5) references majls(Mj_id), 
lec_date date,
topic    text,
alter_Mos varchar(20),
primary key (Mj_id,lec_date)
);

create table volunteer(
 ID varchar(5) primary key ,
 name varchar(25) NOT null );

-- Relationship sets 

--Majls , Jurist 

create table teaches (
ID varchar(5) references Jurist(ID),
Mj_id varchar(5) references majls(Mj_ID),
primary key(ID,Mj_id)
);
drop table Mj_Bk;
--Majls , book
create table Mj_Bk (
Mj_id varchar(5) references majls(Mj_id),
bk_id varchar(7)  references book (bk_id),
primary key(Mj_id, bk_id) );

drop table register;
--student , majls
drop table register;
create table register(
 ID int references student (ID) on delete cascade,
 Mj_id varchar(5) ,
 reg_Date Date DEFAULT CURRENT_DATE ,
 primary key (ID, Mj_id) );

--student , lecture 
drop table attendance;
create table attendance(
 ID int  ,
 Mj_id varchar(5)  ,
 lec_date date ,
 primary key(ID , Mj_id , lec_date) ,
 foreign key (ID , Mj_id) references register(ID, Mj_id) ,
 foreign key (Mj_id,lec_date) references lecture (Mj_id,lec_date)
 ) ;



create or replace function cert(Majls_id varchar) returns table(ID int, name1 varchar(10), name2 varchar(10)) as 
$Q$

   select distinct ID , name1, name4 
   from attendance natural join student 
   where Mj_id = Majls_id
   group by ID , name1, name4 
   having count(lec_date) >= (select 0.9*count(lec_date) from lecture
                             where Mj_id = Majls_id) ;
   
$Q$  language sql;

create role users ; 
grant CONNECT ON DATABASE "Project" TO users;
grant usage on schema "MAJLS_Project" to users;
grant all privileges on attendance, register,lecture,student to users with grant option ; 
grant all privileges on phone to users with grant option ;
grant select on majls to users with grant option ;
create user Ahmad with login password 'A111' ;
create user Ghayth with login password 'G222' ;
grant users to Ahmad ;
grant users to ghayth ;


create role manager login password 'manager' ; 
grant CONNECT ON DATABASE "Project" TO manager;
grant usage, create on schema "MAJLS_Project" to manager;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA "MAJLS_Project" TO manager; 

alter table volunteer
add constraint unique_con unique(name) ; ------- To be as username ; 

ALTER TABLE register 
ADD CONSTRAINT ref_ FOREIGN KEY (mj_id) REFERENCES majls(mj_id);


-----------************ 
create table file_path (
   fpath text primary key, 
   mj_id varchar(5) ,
   lec_date date ,
   foreign key (mj_id,lec_date) references lecture (Mj_id,lec_date)
);

grant all privileges on file_path to manager;
grant all privileges on file_path to users;






















/*drop function certification ;
create or replace function certification(Majls_id varchar) returns student as 
$Q$

   select distinct ID , name1, name2 , name3 , name4 , city , street , gender
   from attendance natural join student 
   where Mj_id = Majls_id
   group by ID , name1, name2 , name3 , name4 , city , street , gender
   having count(lec_date) >= (select 0.9*count(lec_date) from lecture
                             where Mj_id = Majls_id) ;
   
$Q$  language sql; */

/*CREATE TRIGGER check_reg before insert ON attendance
FOR EACH row
EXECUTE function check_reg_fun();
 drop function check_reg_fun;

CREATE OR REPLACE FUNCTION check_reg_fun() RETURNS trigger AS
$BODY$
BEGIN
   if not exists ( 
    select * from register r
    where r.ID = new.ID and r.Mj_id = new.Mj_id
   ) 
    then raise exception 'Student not registerd in this Majls ';
    end if; 
RETURN NEW;
END; $BODY$ language 'plpgsql'; */ 
