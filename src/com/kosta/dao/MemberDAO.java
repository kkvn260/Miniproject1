package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDAO {
	//db 기능 메서드
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

	//insert 기능 메서드
	//update 기능 메서드
	//delete 기능 메서드
	//select 기능 메서드 
	//close 기능 메서드

	public int insert(String i, String p, String n,String em) {
		Connection conn=getConnection();
		PreparedStatement pstmt=null;

		StringBuilder sql=new StringBuilder();
		sql.append(" insert into account(id,name,email,pwd,no) ");
		sql.append("  values(?,?,?,?,proseq.nextval)   "  	);


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

	public getAll()
	{
		//자료구조 list map set => ArraryList : db연결후에 ArraryList에 담아 리턴
		Connection conn=getConnection();

	}
}
