insert into Jurist values ('1111','Khilla') ;
insert into Jurist values ('2222','Hani AL-ALi') ;
insert into Jurist values ('3333','Farahat') ;
insert into Jurist values ('4444','ALGamal') ;
insert into Jurist values ('5555','Farahat') ;

insert into book values ('FW1' , 'Fteh EL-Wahhab'  , '30' ) ;
insert into book values ('AR1' , 'AL-Reqaq'  , '30' ) ;
insert into book values ('MW1' , 'AL-Msael&Wsael'  , '30' ) ;


insert into volunteer values ('1','Ahmad');
insert into volunteer values ('2','Ghayth');
insert into volunteer values ('3','Kareem');

insert into majls values ('FO1', 'Fiqh' , 'al_Omari', '1');
insert into majls values ('FA1', 'Fiqh' , 'Azzam', '2');
insert into majls values ('AQ1', 'Aqeda' , 'Qassam', '2'); 
insert into majls values ('AQ2', 'Aqeda' , 'Rahma', '3'); 

insert into student(name1, name2 , name3 , name4 , city , street , gender) values 
('AL-Hassan' , 'Ramadan' ,'Ahmad' , 'AL-Maqadma' , 'Nusirate', 'New_Camp', 'male');
insert into phone values (1 , '0599222222');
insert into student(name1, name2 , name3 , name4 , city , street , gender) values 
('Yousef' , 'Ahmad' , 'Mohmmed','Ahmad' , 'Zahraa','Falsteen', 'male');
insert into phone values (2 , '0599344251');
insert into student(name1, name2 , name3 , name4 , city , street , gender) values 
('Maha',null, null , 'Abdallah','Gaza','remal', 'female');
insert into phone values (3 , '0568080800');
insert into phone values (3 , '0595050500');

insert into student(name1, name2 , name3 , name4 , city , street , gender) values 
('Laya','Omar',null,'Sa','Rafah','Bla' , 'female' );
insert into phone values (4, '0596060343');

insert into student(name1, name2 , name3 , name4 , city , street , gender) values 
('Aya',null , null ,'Hussam' , 'Gaza' , 'Wehda','female');
insert into phone values (5, '0597070700');

insert into Mj_Bk (bk_id , Mj_id) values ('FW1' ,'FO1');
insert into Mj_Bk (bk_id , Mj_id) values ('AR1' ,'FA1');
insert into Mj_Bk (bk_id , Mj_id) values ('MW1' ,'AQ1');
insert into Mj_Bk (bk_id , Mj_id) values ('MW1' ,'AQ2');

insert into teaches(ID,Mj_id) values ('1111','FO1');
insert into teaches(ID,Mj_id) values ('2222','FA1');
insert into teaches(ID,Mj_id) values ('3333','AQ1');
insert into teaches(ID,Mj_id) values ('4444','AQ2');


insert into register(ID, Mj_id) values (1 , 'FO1');
insert into register(ID, Mj_id) values (2 , 'FA1');
insert into register(ID, Mj_id) values (3 , 'AQ1');
insert into register(ID, Mj_id) values (5 , 'AQ1');
insert into register(ID, Mj_id) values (4 , 'AQ2');

insert into register(ID, Mj_id) values (2 , 'FO1');
insert into register(ID, Mj_id) values (3 , 'FO1');
insert into register(ID, Mj_id) values (4 , 'FO1');
insert into register(ID, Mj_id) values (5 , 'FO1');

insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FO1' ,'2020-05-13','Requisites of Islam',null);
insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FO1' ,'2020-05-15','Zakah',null);
insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FO1' , '2020-05-17' ,'Fasting',null);
insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FO1' , '2020-05-19' ,'Hajj',null);
insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FO1' , '2020-05-21' ,'Hajj2',null);

insert into lecture(Mj_id ,lec_date,topic, alter_Mos) values(
'FA1' , '2020-05-21' ,'Charity',null);

insert into attendance (ID, Mj_id, lec_date) values (1,'FO1','2020-05-13');
insert into attendance (ID, Mj_id, lec_date) values (1,'FO1','2020-05-15');
insert into attendance (ID, Mj_id, lec_date) values (1,'FO1','2020-05-17');
insert into attendance (ID, Mj_id, lec_date) values (1,'FO1','2020-05-19');
insert into attendance (ID, Mj_id, lec_date) values (2,'FO1','2020-05-13');
insert into attendance (ID, Mj_id, lec_date) values (2,'FO1','2020-05-15');
insert into attendance (ID, Mj_id, lec_date) values (2,'FO1','2020-05-21');
insert into attendance (ID, Mj_id, lec_date) values (3,'FO1','2020-05-13');
insert into attendance (ID, Mj_id, lec_date) values (3,'FO1','2020-05-15');
insert into attendance (ID, Mj_id, lec_date) values (3,'FO1','2020-05-17');
insert into attendance (ID, Mj_id, lec_date) values (3,'FO1','2020-05-19');
insert into attendance (ID, Mj_id, lec_date) values (3,'FO1','2020-05-21');
insert into attendance (ID, Mj_id, lec_date) values (4,'FO1','2020-05-15');
insert into attendance (ID, Mj_id, lec_date) values (4,'FO1','2020-05-17');
insert into attendance (ID, Mj_id, lec_date) values (4,'FO1','2020-05-19');
insert into attendance (ID, Mj_id, lec_date) values (5,'FO1','2020-05-15');
insert into attendance (ID, Mj_id, lec_date) values (5,'FO1','2020-05-17');
insert into attendance (ID, Mj_id, lec_date) values (5,'FO1','2020-05-19');
insert into attendance (ID, Mj_id, lec_date) values (5,'FO1','2020-05-21');
insert into attendance (ID, Mj_id, lec_date) values (5,'FO1','2020-05-13');


