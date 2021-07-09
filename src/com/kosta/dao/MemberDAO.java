package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MemberDAO {

	public HashMap<String, MemberDTO> hm=new HashMap<>();

	Scanner sc=new Scanner(System.in);
	private Connection getConnection(){ //db 기능 메서드
		String className = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "hr";
		String pwd = "hr";
		Connection conn = null;
		try {
			Class.forName(className);
			conn=DriverManager.getConnection(url,user,pwd);


		}catch(SQLException | ClassNotFoundException e ) {
			System.out.println(e);
		}
		return conn;
	}

	//insert 기능 메서드 o
	//update 기능 메서드
	//delete 기능 메서드
	//select 기능 메서드 
	//close 기능 메서드 o
	public MemberDTO check(String i){
		MemberDTO result=null;
		if(hm.containsKey(i)) {
			result=hm.get(i);
		}
		return result;
	}
	public int insert(String i, String p, String n,String em) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;

		StringBuilder sql=new StringBuilder();
		sql.append(" insert into account(id,name,email,pwd,no,cdate) ");
		sql.append("  values(?,?,?,?,proseq.nextval,sysdate)   "  	);


		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, i);
			pstmt.setString(2, n);
			pstmt.setString(3, em);
			pstmt.setString(4, p);
			result = pstmt.executeUpdate();
			hm.put(i, new MemberDTO(i, n, em, p));
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt,conn);
		}
		return result;
	}
	private void close(PreparedStatement pstmt, Connection conn)//pstmt conn close기능
	{
		if(pstmt!=null) try { pstmt.close();} catch(SQLException e) {}
		if(conn!=null) try { pstmt.close();} catch(SQLException e) {}
	} 

	public void getAll()
	{
		System.out.println("ID\t 이름\t 이메일\t");
		Iterator<String> ita=hm.keySet().iterator();
		while(ita.hasNext()) {
			String id=ita.next();
			MemberDTO value=hm.get(id);
			System.out.println(id+"\t"+value);
		}
	}

	public void search() {
		while(true) {
			System.out.println("ID를 입력해주세요.");
			String id=sc.nextLine();
			MemberDTO ck=check(id);
			if(ck!=null) {
				System.out.println("ID\t 이름\t 이메일\t");
				Iterator<String> ita=hm.keySet().iterator();
				id=ita.next();
				MemberDTO value=hm.get(id);
				System.out.println(id+"\t"+value);

				System.out.println("계속 조회할까요? y/n");
				String yn=sc.nextLine();
				if(yn.contentEquals("n"))
					break;
			}
			else
				System.out.println("입력한 ID가 없습니다.");
		}
	}


	/*
	 * public void cseq() { Connection conn=getConnection(); PreparedStatement
	 * pstmt=null; StringBuilder sql4=new StringBuilder();
	 * sql4.append("  create sequence proseq  "); try {
	 * pstmt=conn.prepareStatement(sql4.toString()); pstmt.executeUpdate();
	 * }catch(SQLException e) { System.out.println(e); }finally { close(pstmt,conn);
	 * } }
	 * 
	 * public void dseq() { Connection conn=getConnection(); PreparedStatement
	 * pstmt=null; StringBuilder sql3=new StringBuilder();
	 * sql3.append("  drop sequence proseq  ");
	 * 
	 * try { pstmt=conn.prepareStatement(sql3.toString()); pstmt.executeUpdate();
	 * }catch(SQLException e) { System.out.println(e); }finally { close(pstmt,conn);
	 * }
	 * 
	 * }
	 */
	public void modi() {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		System.out.println("수정할 ID를 입력해주세요.");
		String id=sc.nextLine();
		MemberDTO ck=check(id);
		if(ck!=null) {
			System.out.println("수정할 비밀번호를 입력해주세요.");
			String pwd=sc.nextLine();
			sb.append("  update account  ");
			sb.append("  set pwd =? "   );
			sb.append("  where id=? "  );
			int result=0;
			try {
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, pwd);
				pstmt.setString(2, id);
				result=pstmt.executeUpdate();
				
				
			}catch(SQLException e) {
				System.out.println(e);
			}finally {
				close(pstmt,conn);
			}
			if(result >=1)
			System.out.println("수정완료");
		}
		else
			System.out.println("입력한 ID가 없습니다.");

	}

//	public void start() {
//		return 
//	}
}

