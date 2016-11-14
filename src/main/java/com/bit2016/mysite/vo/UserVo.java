package com.bit2016.mysite.vo;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

public class UserVo {
	private Long no;
	
	@NotEmpty
	@Length(min=2, max=5)
	private String name;
	
	@NotEmpty
	@Email
//	@Pattern(regexp="[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$/i")
	private String email;
	
	@NotEmpty
	@Pattern(regexp="^[0-9]+$")
	private String password;
	
	private String gender;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + "]";
	}
}
