package com.kosta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class MemberDAO {
	
	public HashMap<String, MemberDTO> hm=new HashMap<>();
	
	
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
	private MemberDTO check(String i){
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
		while(true) {
			System.out.println("ID\t 이름\t 이메일\t");
			Iterator<String> ita=hm.keySet().iterator();
			while(ita.hasNext()) {
				String id=ita.next();
				MemberDTO value=hm.get(id);
				System.out.println(id+"\t"+value);
			}
			
		}
	
		
	}
}
