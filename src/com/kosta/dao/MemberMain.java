package com.kosta.dao;

import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		MemberDAO dao=new MemberDAO();
		MemberDTO dto=new MemberDTO();
		MemberCase ins=new MemberCase();


		while (true) {
			System.out.println("===�޴�===");
			System.out.println("1.ȸ������");
			System.out.println("2.ȸ������");
			System.out.println("3.ȸ������");
			System.out.println("4.ȸ����ȸ");
			System.out.println("5.��ü����");
			System.out.print("���� : ");
			String su=sc.nextLine();

			switch(su) {
			case "1":
				ins.case1();
				break; 
			case "2":
				ins.case2();
				break;
			case "3":
				ins.case3();
				break;
			case "4":
				ins.case4();
				break;
			case "5": 
				dao.getAll();
				break;
			default:
				System.out.println("���� �Ͻðڽ��ϱ�? y/n");
				String yn=sc.nextLine();
				if(yn.contentEquals("y")) {
					System.out.println("�����մϴ�.");
					System.exit(0);
				}
			}

		}

	}

}

