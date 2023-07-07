package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDAO {

	private static AdminDAO instance = null;
	private AdminDAO() {}
	public static AdminDAO getInstance() {
		if(instance == null) 
			instance = new AdminDAO();
		return instance;
	
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public Map<String, Object> select(String id, String name){

		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_NAME = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(name);
		return jdbc.selectOne(sql,param);
	}
	
/*	public Map<String, Object> update(String admin, String id, String name){

		String sql = "UPDATE MEMBER MEM_ADMIN = ?  where MEM_id = ? and mem_name = ? ";
				
		List<Object> list = new ArrayList<Object>();
		
		param.add(admin);
		param.add(id);
		param.add(name);
		param.add(email);
		
		return jdbc.update(sql, param);
	*/	

	
}	
