package com.kosta.dao;

public class MemberDTO {
	private String id;
	private String name;
	private String email;
	private String pwd;
	public MemberDTO(String id, String name, String email, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name + "\t" + email;
	} 
	public String getEmail() {
		return email;
	}

}
