package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MemberDAO {

	// 싱글톤패턴
	private static MemberDAO instance = null;
	private MemberDAO() {};
	public static MemberDAO getInstance() {
		if(instance == null) instance = new MemberDAO();
		return instance;
	}
	
	// JDBC 호출
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public Map<String, Object> login(String id, String pass){
		
		return jdbc.selectOne("SELECT * FROM MEMBER"
				+ " WHERE MEM_ID ='" + id +"' AND MEM_PASS='"+pass+"' ");
			
	}
	
	public int signUp(String id,String password,String name,String tel,String addr,String email) {

		String sql = "INSERT INTO MEMBER (MEM_ID, MEM_PW, MEM_NAME, MEM_TEL, MEM_ADDRESS, MEM_EMAIL) ";
		sql += " VALUES (?, ?, ?, ?, ?, ?)";
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(id);
		param.add(password);
		param.add(name);
		param.add(tel);
		param.add(addr);
		param.add(email);
		
		return jdbc.update(sql, param);
			
	}
	
	
	
	
	
	
	
	
}
