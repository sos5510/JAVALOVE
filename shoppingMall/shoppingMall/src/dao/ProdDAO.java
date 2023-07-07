package dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ProdDAO {

	// 싱글톤패턴
	private static ProdDAO instance = null;
	private ProdDAO() {};
	public static ProdDAO getInstance() {
		if(instance == null) instance = new ProdDAO();
		return instance;
	}
	
	
	// JDBC 호출
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<LinkedHashMap<String,Object>> topList(){
		
		String sql = "SELECT ROWNUM 번호, PROD_NAME 상품명, PROD_PRICE 가격 FROM PROD";
		sql += " WHERE CATE_CODE = 'A100'";
		
		System.out.println(sql);
		return jdbc.selectList(sql);
		
	}


	public List<LinkedHashMap<String,Object>> pantsList(){

		String sql = "SELECT ROWNUM 번호, PROD_NAME 상품명, PROD_PRICE 가격 FROM PROD";
		sql += " WHERE CATE_CODE = 'A101'";
		
		System.out.println(sql);
		return jdbc.selectList(sql);
		
	}
	public List<LinkedHashMap<String,Object>> accList(){

		String sql = "SELECT ROWNUM 번호, PROD_NAME 상품명, PROD_PRICE 가격 FROM PROD";
		sql += " WHERE CATE_CODE = 'A102'";
		
		return jdbc.selectList(sql);
		
	}

//	public int buyThis(List<LinkedHashMap<String,Object>> list, int item, int qty){
//		
//		
//		
//	}
		
		
	}
//	public int addCartThis(int result) {
//		String sql = "INSERT INTO CART "
//	}
//	
//	
	
//	public int buyOne() {
//		String sql = "UPDATE ";
//		
//	}
	
	
	
	
	
	
	
	
	
	
	

