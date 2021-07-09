package com.kosta.dao;

import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		MemberDAO dao=new MemberDAO();
		

		while (true) {
			System.out.println("===메뉴===");
			System.out.println("1.입력");
			System.out.println("2.수정");
			System.out.println("3.삭제");
			System.out.println("4.조회");
			System.out.println("5.전체보기");
			System.out.println("6.종료");
			System.out.print("선택 : ");
			String su=sc.nextLine();

			switch(su) {
			case "1":
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
				break;
			case "2":

				break;
			case "3":

				break;
			case "4":

				break;
			case "5":

				break;
			default:
				System.exit(0);



			}

		}

	}
}
