package erp_jsp_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import erp_jsp_exam.dao.EmployeeDao;
import erp_jsp_exam.dto.Department;
import erp_jsp_exam.dto.Employee;
import erp_jsp_exam.dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {
	private Connection con;
	private static EmployeeDaoImpl instance = new EmployeeDaoImpl(); 
	
	private EmployeeDaoImpl() {
		
	}
	
	public static EmployeeDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}


	private Employee getEmployee(ResultSet rs) throws SQLException{
		int no = rs.getInt("emp_no");
		String name = rs.getString("emp_name");
		Title title = new Title(rs.getInt("title_no"));
		
		Employee manager = new Employee(rs.getInt("mng_no"));
		int salary = rs.getInt("salary");
		Department dept = new Department(rs.getInt("dept_no"));
		Date hireDate = new Date(rs.getDate("hireDate").getTime());
		
		try {
			title.setName(rs.getString("title_name"));
		} catch (SQLException e) {
		}
		
		if (rs.getInt("mng_no") == 0) {
			manager = null;
		} else {
			try {
				manager.setName(rs.getString("mng_name"));
			} catch (SQLException e) {
			}
		}

		try {
			dept.setName(rs.getString("dept_name"));
		} catch (SQLException e) {
		}
		
		return new Employee(no, name, title, manager, salary, dept, hireDate);
	}
	
	@Override
	public List<Employee> selectEmployeeAll() {
		String sql = "select emp_no,emp_name,title_no,title_name,mng_no,mng_name,salary,dept_no,dept_name,hiredate from vw_all";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			List<Employee> list = new ArrayList<Employee>();
			while (rs.next()) {
				list.add(getEmployee(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select emp_no,emp_name,title_no,title_name,mng_no,mng_name,salary,dept_no,dept_name,hiredate from vw_all where emp_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, emp.getNo());
			try(ResultSet rs = pstmt.executeQuery();
					){
				if(rs.next()) {
					return getEmployee(rs);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?, ?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, emp.getNo());
			pstmt.setString(2, emp.getName());
			pstmt.setInt(3, emp.getTitle().getNo());
			pstmt.setInt(4, emp.getManager().getNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getNo());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where emp_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, emp.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee "
				+ "set emp_no = ?, emp_name = ?, tno = ?, manager = ?, salary = ?, dno = ?, hiredate = ? "
				+ "where emp_no = ?";
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setInt(1, emp.getNo());
			pstmt.setString(2, emp.getName());
			pstmt.setInt(3, emp.getTitle().getNo());
			pstmt.setInt(4, emp.getManager().getNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getNo());
			pstmt.setTimestamp(7, new Timestamp(emp.getHireDate().getTime()));
			pstmt.setInt(8, emp.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
