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
		 * 3.executeUpdate => row��� 
		 * 	executeQuery => ResultSet
		 * 4.close
		 * 
		 */
		MemberDAO dao=new MemberDAO();
		Scanner sc=new Scanner(System.in);

		System.out.println("ID�� �Է����ּ���.");
		String id=sc.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���.");
		String pwd=sc.nextLine();
		System.out.println("�̸��� �Է����ּ���.");
		String name=sc.nextLine();
		System.out.println("�̸��ϸ� �Է����ּ���.");
		String email=sc.nextLine();

		int result = dao.insert(id,pwd,name,email);

		if(result >=1)
			System.out.println("�߰��Ϸ�");
		else
			System.out.println("�߰�����");  

	}

}
