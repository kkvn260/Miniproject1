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
			System.out.println("===메뉴===");
			System.out.println("1.회원가입");
			System.out.println("2.회원수정");
			System.out.println("3.회원삭제");
			System.out.println("4.회원조회");
			System.out.println("5.전체보기");
			System.out.print("선택 : ");
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
				System.out.println("종료 하시겠습니까? y/n");
				String yn=sc.nextLine();
				if(yn.contentEquals("y")) {
					System.out.println("종료합니다.");
					System.exit(0);
				}
			}

		}

	}

}

