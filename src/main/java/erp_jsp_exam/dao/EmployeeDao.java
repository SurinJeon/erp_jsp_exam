package erp_jsp_exam.dao;

import java.util.List;

import erp_jsp_exam.dto.Employee;

public interface EmployeeDao {
	
	List<Employee> selectEmployeeAll();
	Employee selectEmployeeByNo(Employee emp);
	int insertEmployee(Employee emp);
	int deleteEmployee(Employee emp);
	int updateEmployee(Employee emp);
	
}
