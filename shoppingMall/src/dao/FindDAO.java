package dao;


import java.util.*;

import util.JDBCUtil;

public class FindDAO {

	private static FindDAO instance = null;
	private FindDAO() {}
	public static FindDAO getInstance() {
		if(instance == null) 
			instance = new FindDAO();
		return instance;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	public Map<String, Object> find(String name, String tel){
	
		String sql = "SELECT * FROM MEMBER WHERE MEM_NAME = ? AND MEM_TEL = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(name);
		param.add(tel);
		
		return jdbc.selectOne(sql, param);
	}
	
	public Map<String, Object> find(String id,String name, String tel){
		
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ? MEM_NAME = ? AND MEM_TEL = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(name);
		param.add(tel);
		
		return jdbc.selectOne(sql, param);
	}
	
		public Map<String, Object> select(String id){
			String sql = "SELECT * FROM MEMBER ";
			sql += " WHERE MEM_ID = ? ";
			
			List<Object> param = new ArrayList<Object>();
			param.add(id);
			
			return jdbc.selectOne(sql, param);
		}
	}