package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class LoginDAO {

	// 싱글톤패턴
	private static LoginDAO instance = null;
	private LoginDAO() {};
	public static LoginDAO getInstance() {
		if(instance == null) instance = new LoginDAO();
		return instance;
	}
	
	// JDBC 호출
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public Map<String, Object> login(String id, String pass){
		
		// 로그인 
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PW = ?";
		List<Object> param = new ArrayList<Object>();

		param.add(id);
		param.add(pass);
		
		return jdbc.selectOne(sql,param);
		
		
		
	}
	
	
	public Map<String, Object> select(String id){
		String sql = "SELECT * FROM MEMBER ";
		sql += " WHERE MEM_ID = ? ";
		
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		
		return jdbc.selectOne(sql, param);
	}
	
}
