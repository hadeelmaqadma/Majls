# Project_file



Majls Attendance is a [DBMS Project] with both Manager and User Layout 
The Project is built using javaFx on IntelliJ 2020.0.1 with PostgreSQL server.
It doesn't present professional GUI's, It focuses on DataBase Connection and operates from Java Framework ;

--------- 
Installation :
 
  Java 11.07 
  IntelliJ 2020.0.1 
  JavaFx 11.0.2 
JDBC PostgreSQL jar file
ControlsFx 11.0.1

--------
Begin with Project ER:
 
 you can find it in DataBase>> Project_ER.PNG 
-------
DataBase Relational Schema: 
 
 you can find it in DataBase>>Schema 
 you'll find DDL.sql , DML.sql files 
and you'll also find queries.sql file which contains such simple data queries .

------
Usage
Running the Program : 

open Project file on your IntelliJ .. Run Main Class 

** Main Class  Contains FxmlLoader to show  “Start.fxml” view .

** Start View is a  login stage  
    you can login as a manager or as a volunteer 
    also you have  Alternate Volunteer button .. to login as an alternative volunteer .
   
  1- If you click Alternate Volunteer 
      you’ll turn to Alter.fxml view 
       to enter  CSV file of attendance students  when the responsible volunteer is absent. 
2- If you click as volunteer or manager button  you’ll  go to the same View 
 ^^ Login View ^^ 
  you have to enter correct userID and Password .

  -- when you enter you Connect to the database server , using DBModel class
[
PGSimpleDataSource DS = new PGSimpleDataSource();
DS.setServerName("localhost");
DS.setDatabaseName("Project");
DS.setUser(getUsername());
DS.setPassword(getPassword());
]

--------------- IN AS VOLUNTEER --------------------
1- first view VolView.fxml : you have choose the majls id >> then what to do  .

    with is chosen majls id :
    what to do list : (Operations List) 
* Register … opens Reg.fxml view to  Register student to the majls 

* More Data.. opens Data.fxml view to show more data about students and majls’s lectures  like :
           1- Students who attended to 90% or more of this majls lectures .. and 
            you can enter write CSV file path to export those data .
           2- Lectures Attending Report .
   -      Import Files … opens Import.fxml view to import attendance files which written be the Alternative volunteer .

  -    Attendance/AddLecture … opens Atte_lec.fxml View to add new lecture or to take the Attendance of specific lecture .(AddLec.fxml view) +(Attendance.fxml view)

-     Student Report .. opens Report.fxml  view to show student attending report in this majls 
     With the possibility to amend it.

- Lecture Details… opens lecDetails.fxml  view to view specific lecture details like the average of attendance .
-

--------------- IN AS MANAGER------------- 

First view Mang.fxm view  
 you have potions : 
  1- Majls : to register new majls …  (UpdateMajls.fxml view)
  2- create user : create new user … (CreateUserView.fxml view) .
