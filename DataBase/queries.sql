
--Most Attendance ;

select lec_date , topic
from attendance a natural join lecture 
group by lec_date , topic
having count(distinct id) >= all(select count(distinct id) 
                             from attendance a natural join lecture 
                             where mj_id='FO1'
                             group by lec_date ,topic );
   -- OR --- 
 select count(distinct id) numOf_Attendance , lec_date ,topic
 from attendance a natural join lecture
 where mj_id='FO1'
 group by lec_date ,topic 
 order by numOf_Attendance desc;   
 -- limit ... 
 

 --?  Avarege of Attending                         
select (select count(distinct ID)
        from attendance natural join lecture
        where  mj_id='FA1')*100.0 / (select count(distinct ID ) from register r where r.mj_id='FO1' )  as avg;
        
     
 

-- Absentees >> more than Attendees %'
select  mj_id , lec_date ,topic 
from lecture l natural join attendance
group by mj_id , lec_date ,topic 
having count(id) < (select count(id) 
                    from register r
                    where r.mj_id = l.mj_id)/2.0 ;
                   
                   
 -- *********
 
SELECT distinct id , mj_id, count( lec_date) OVER (PARTITION BY id , mj_id) 
FROM lecture l natural join attendance;

SELECT distinct id , mj_id, count( lec_date) OVER (PARTITION BY id ) as Attending
FROM lecture l natural join attendance
where mj_id='FO1'
order by Attending desc;
 -------- ********** 
 with ids as (
   select ID 
   from attendance  
   where Mj_id ='FO1'
   group by ID 
   having count(lec_date) >= (select 0.5*count(lec_date) from lecture
                             where Mj_id = 'FO1') ) 
  select *
  from lecture l  natural join register r
  where   lec_date not in ( select lec_date
                            from ids , attendance a
                            where ids.Id= a.id and a.id=r.id
                            and  l.mj_id = a.mj_id );
           
  
  ------- ***** 
       