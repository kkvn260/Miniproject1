package com.kosta.dao;

import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		MemberDAO dao=new MemberDAO();


		while (true) {
			System.out.println("===�޴�===");
			System.out.println("1.�Է�");
			System.out.println("2.����");
			System.out.println("3.����");
			System.out.println("4.��ȸ");
			System.out.println("5.��ü����");
			System.out.print("���� : ");
			String su=sc.nextLine();

			switch(su) {
			case "1":
				while(true) {
				System.out.println("ID�� �Է����ּ���.");
				String id=sc.nextLine();
				MemberDTO ck=dao.check(id);
				if(ck==null) {
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
				else
					System.out.println("�̹� �����ϴ� ID�Դϴ�.");
				
				System.out.println("��� �Է� �Ͻðڽ��ϱ�? y/n");
				String yn=sc.nextLine();
				if(yn.contentEquals("n"))
					break; 
				}
				break; 
			case "2":
//				dao.modi();
				break;
			case "3":

				break;
			case "4":
				dao.search();
				break;
			case "5":
				dao.getAll();
				break;
			default:
				System.out.println("�����մϴ�.");
				System.exit(0);
				}

			}

		}

	}

