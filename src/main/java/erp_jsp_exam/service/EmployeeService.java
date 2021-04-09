package erp_jsp_exam.service;

import java.sql.Connection;
import java.util.List;

import erp_jsp_exam.dao.impl.EmployeeDaoImpl;
import erp_jsp_exam.ds.JndiDS;
import erp_jsp_exam.dto.Employee;

public class EmployeeService {
	private EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public EmployeeService() {
		dao.setCon(con);
	}
	
	public List<Employee> showEmployees(){
		return dao.selectEmployeeAll();
	}
}
