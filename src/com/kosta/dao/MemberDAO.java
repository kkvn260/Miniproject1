package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MemberDAO {

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
	public int check(String i){
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" insert into (id,no)" );
		sb.append("  values(?,0)"  );
		int result=0;
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, i);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt, conn);
		}return result;

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
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		sb.append("  select   ");
		sb.append("  no   " );
		sb.append("  ,id   " );
		sb.append("  ,name  ");
		sb.append("  ,email  ");
		sb.append("  ,cdate  ");
		sb.append("  from account  ");
		sb.append("  order by no  ");
		
		try {
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			System.out.println("번호\t ID\t 이름\t 이메일\t 가입일자\t");
			while(rs.next()) {
			System.out.printf("%d\t %s\t %s\t %s\t %s\n", rs.getInt("no")
												,rs.getString("id")
												,rs.getString("name")
												,rs.getString("email")
												,rs.getString("cdate")
												);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt, conn);
		}
		
	}

	public void search() {
			System.out.println("ID를 입력해주세요.");
			String id=sc.nextLine();
			int result=check(id);
			if(result==0) {
				Connection conn=getConnection();
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				StringBuffer sb=new StringBuffer();
				sb.append( " select id  "  );
				sb.append( " ,name  "  );
				sb.append("  ,email  ");
				sb.append("  ,cdate  ");
				sb.append("  from account  ");
				sb.append("  where = ?  ");
				
				try {
					pstmt=conn.prepareStatement(sb.toString());
					pstmt.setString(1, id);
					pstmt.executeQuery();
					
				}catch(SQLException e) {
					System.out.println(e);
				}finally {
					close(pstmt, conn);
				}

			}
			else
				System.out.println("입력한 ID가 없습니다.");
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

	public void delete() {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		System.out.println("삭제할 ID를 입력해주세요.");
		String id=sc.nextLine();
		MemberDTO ck=check(id);
		if(ck!=null) {
			System.out.println("정말 삭제 하시겠습니까? y/n");
			String yn=sc.nextLine();
			if(yn.contentEquals("y")) {
				sb.append("  delete from account  ");
				sb.append("  where id=? "  );
				int result=0;
				try {
					pstmt=conn.prepareStatement(sb.toString());
					pstmt.setString(1, id);
					result=pstmt.executeUpdate();
				}catch(SQLException e) {
					System.out.println(e);
				}finally {
					close(pstmt,conn);
				}
				if(result >=1)
					System.out.println("삭제 완료");
				else
					System.out.println("삭제 실패");
			}
		}
		else
			System.out.println("입력한 ID가 없습니다.");
	}

		public void start() {
			Connection conn=getConnection();
			

		}
}

