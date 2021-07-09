package com.kosta.dao;

public class MemberDTO {
	private String id;
	private String name;
	private String email;
	private String pwd;
	public MemberDTO(String id, String name, String email, String pwd, int no) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.no = no;
	}
	private int no;
}
