package com.song.atguigu.spring.jdbc;

public class Employee {
	
	private Integer id;
	private String email;
	private String lastName;
	
	private Department depts;
	
	private Integer deptId;
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Department getDepts() {
		return depts;
	}
	public void setDepts(Department depts) {
		this.depts = depts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", lastName=" + lastName + ", depts=" + depts + "]";
	}
	
}
