package erp_jsp_exam.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_jsp_exam.dao.impl.EmployeeDaoImpl;
import erp_jsp_exam.dto.Department;
import erp_jsp_exam.dto.Employee;
import erp_jsp_exam.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {

	private static Connection con = JdbcUtil.getConnection();
	private static EmployeeDaoImpl dao = EmployeeDaoImpl.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		con.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test04SelectEmployeeAll() {
		System.out.printf("%s()%n", "SelectEmployeeAll");
		List<Employee> list = new ArrayList<Employee>();
		list = dao.selectEmployeeAll();
		list.stream().forEach(System.out::println);
		Assert.assertNotNull(list);
	}

	@Test
	public void test05SelectEmployeeByNo() {
		System.out.printf("%s()%n", "SelectEmployeeByNo");
		Employee emp = new Employee(4377);
		Employee searchEmp = dao.selectEmployeeByNo(emp);
		Assert.assertNotNull(searchEmp);
		System.out.println(searchEmp);
	}

	@Test
	public void test01InsertEmployee() {
		System.out.printf("%s()%n", "InsertEmployee");
		Employee newEmp = new Employee(1004, "짱수린", new Title(2), new Employee(4377), 5000000, new Department(2), new Date(120, 7, 25));
		int res = dao.insertEmployee(newEmp);
		
		Assert.assertEquals(1, res);
		dao.selectEmployeeAll().stream().forEach(System.out::println);
	}

	@Test
	public void test03DeleteEmployee() {
		System.out.printf("%s()%n", "DeleteEmployee");
		Employee delEmp = new Employee(1004);
		int res = dao.deleteEmployee(delEmp);
		
		Assert.assertEquals(1, res);
		dao.selectEmployeeAll().stream().forEach(System.out::println);
	}

	@Test
	public void test02UpdateEmployee() {
		System.out.printf("%s()%n", "UpdateEmployee");
		Employee upEmp = new Employee(1004, "짱수린", new Title(2), new Employee(4377), 5000000, new Department(2), new Date(120, 9, 25));
		int res = dao.updateEmployee(upEmp);
		
		Assert.assertEquals(1, res);
		dao.selectEmployeeAll().stream().forEach(System.out::println);
	}

}
