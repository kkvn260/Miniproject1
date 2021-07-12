package com.kosta.dao;

import java.lang.reflect.Member;
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
	private Connection getConnection(){ 
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

	public int check(String i){
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		sb.append(" select id  " );
		sb.append("  from account  "  );
		sb.append("  where id=?  ");
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, i);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=1;
			}
			else
				result=0;
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt, conn);
		}
		return result;

	}
	public int insert(MemberDTO em) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;

		StringBuilder sql=new StringBuilder();
		sql.append(" insert into account(id,name,email,pwd,no,cdate) ");
		sql.append("  values(?,?,?,?,proseq.nextval,sysdate)   "  	);


		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, em.getId());
			pstmt.setString(2, em.getName());
			pstmt.setString(3, em.getEmail());
			pstmt.setString(4, em.getPwd());
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

	public void search(String id) {

		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		sb.append( " select id  "  );
		sb.append( " ,name  "  );
		sb.append("  ,email  ");
		sb.append("  ,cdate  ");
		sb.append("  from account  ");
		sb.append("  where id= ?  ");

		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			System.out.println("ID\t 이름\t 이메일\t 가입일자\t");
			while(rs.next()) {
				System.out.printf("%s\t %s\t %s\t %s\n"
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


	public int modi(MemberDTO em) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();

		sb.append("  update account  ");
		sb.append("  set pwd =? "   );
		sb.append("  set email =? "   );
		sb.append("  where id=? "  );
		int result=0;
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, em.getPwd());
			pstmt.setString(2, em.getEmail());
			pstmt.setString(3, em.getId());
			result=pstmt.executeUpdate();


		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			close(pstmt,conn);
		}
		return result;
	}

	public int delete(String id) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();

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
		}return result;
	}
}



