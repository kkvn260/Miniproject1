package com.kosta.dao;

import java.util.Scanner;

public class MemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1.db
		 * 2.preparedStatem insert update, delete => executeUpdate
		 * 				select => executeQuery
		 * 
		 * 3.executeUpdate => row기능 
		 * 	executeQuery => ResultSet
		 * 4.close
		 * 
		 */
		MemberDAO dao=new MemberDAO();
		Scanner sc=new Scanner(System.in);

		System.out.println("ID를 입력해주세요.");
		String id=sc.nextLine();
		System.out.println("비밀번호를 입력해주세요.");
		String pwd=sc.nextLine();
		System.out.println("이름를 입력해주세요.");
		String name=sc.nextLine();
		System.out.println("이메일를 입력해주세요.");
		String email=sc.nextLine();

		int result = dao.insert(id,pwd,name,email);

		if(result >=1)
			System.out.println("추가완료");
		else
			System.out.println("추가실패");  

	}

}
