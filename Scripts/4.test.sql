select user(), database();

select emp_no, emp_name, tno, manager, salary, dno, hiredate from employee;
select emp_no, emp_name, tno, manager, salary, dno, hiredate from employee where emp_no = 1003;
insert into employee values(1004, '짱수린', 2, 4377, 5000000, 2, '2020-08-25');
update employee set emp_no = 1004, emp_name = '짱수린', tno = 2, manager = 4377, salary = 6000000, dno = 2, hiredate = '2020-08-25' where emp_no = 1004;
delete from employee where emp_no = 1004;

create view vw_all
as
select e.emp_no, e.emp_name, t.title_no, t.title_name, m.emp_no as mng_no, m.emp_name as mng_name, e.salary, d.dept_no, d.dept_name , e.hiredate from employee e left join employee m on e.manager = m.emp_no left join department d on e.dno = d.dept_no left join title t on e.tno = t.title_no; 

select emp_no,emp_name,title_no,title_name,mng_no,mng_name,salary,dept_no,dept_name,hiredate from vw_all;