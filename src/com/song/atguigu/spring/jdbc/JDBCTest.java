package com.song.atguigu.spring.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class JDBCTest {
	
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;	
	private EmployeeDao employeeDao;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		employeeDao = ctx.getBean(EmployeeDao.class);
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}
	/**
	 * update(String sql, SqlParameterSource paramSource)
	 */
	@Test
	public void testNamedParameterJdbcTemplate(){
		String sql = "INSERT INTO employees (id,last_name,email,dept_id) VALUES (:id,:lastName,:email,:deptId)";
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("id", 4);
//		paramMap.put("lastName", "Danny");
//		paramMap.put("email", "danny@tom.com");
//		paramMap.put("deptId", 3);
//      namedParameterJdbcTemplate.update(sql, paramMap);
		Employee employee = new Employee();
		employee.setId(5);
		employee.setLastName("AA");
		employee.setEmail("aa@tom.com");
		employee.setDeptId(1);
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(employee);
	    namedParameterJdbcTemplate.update(sql, sqlParameterSource);
	}
	
	@Test
	public void testEmployeeDao(){
		System.out.println(employeeDao.get(1));
	}
	/**
	 * 查询单个值：
	 * queryForObject(String sql, Class<Long> requiredType) 
	 */
	@Test
	public void testQueryForObject2(){
		String sql = "select count(id) from employees";
		long count = jdbcTemplate.queryForObject(sql, long.class);
		System.out.println(count);
	}
	
	/**
	 * 查询实体类集合
	 */
	@Test
	public void testQueryForList(){
		String sql = "SELECT id,last_name,email FROM employees  WHERE id > ?";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
 		
		List<Employee> employees = jdbcTemplate.query(sql, rowMapper,1);
		System.out.println(employees);
	}
	/**
	 * 查询实体类对象
	 */
	@Test
	public void testQueryForObject(){
		String sql = "SELECT id,last_name,email FROM employees  WHERE id = ? ";
		RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<>(Employee.class);
		Employee employee = jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(employee);
	}
	/**
	 * 批量插入
	 */
	@Test
	public void testBatchUpdate(){
//		String sql = "INSERT INTO departments (id,department_name) VALUES (?,?)";
//		List<Object[]> batchArgs = new ArrayList<>();
//		batchArgs.add(new Object[]{3,"dept-3"});
//		batchArgs.add(new Object[]{4,"dept-4"});
//		batchArgs.add(new Object[]{5,"dept-5"});
		
		String sql = "INSERT INTO employees (id,last_name,email,dept_id) VALUES (?,?,?,?)";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{1, "Tom", "tom@tom.com", 1});
		batchArgs.add(new Object[]{2, "Jerry", "Jerry@tom.com", 2});
		batchArgs.add(new Object[]{3, "Jack", "Jack@tom.com", 3});
		int[] counts = jdbcTemplate.batchUpdate(sql, batchArgs);
		System.out.println(counts.length);
	}
	
	/**
	 * 批量删除
	 */
	@Test
	public void testBatchDelete(){
		String sql = "delete t from departments t where t.id = ? ";
		List<Object[]> batchArgs = new ArrayList<>();
		batchArgs.add(new Object[]{3});
		batchArgs.add(new Object[]{4});
		batchArgs.add(new Object[]{5});
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
	/**
	 * 插入/更新
	 */
	@Test
	public void testInsert(){
		String sql = "INSERT INTO departments (id,department_name) VALUES (?,?)"; 
		int count = jdbcTemplate.update(sql, 2,"dept-2");
		System.out.println(count);
	}
	


	@Test
	public void test() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
//		Integer a = null, b = null;
//		boolean bool = java.util.Objects.equals(a, b);
//		System.out.println(bool);
//		Objects.equals(a, b);
		
	}

}
