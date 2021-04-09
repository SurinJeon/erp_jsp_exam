select user(), database();

grant all
   on erp_jsp_exam.*
   to 'erp_jsp_exam'@'localhost' identified by 'rootroot';
   
create database erp_jsp_exam;