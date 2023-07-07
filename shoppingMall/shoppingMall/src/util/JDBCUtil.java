package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

	// 싱글톤패턴
	private static JDBCUtil instance = null;
	private JDBCUtil() {};
	public static JDBCUtil getInstance() {
		if(instance == null)instance = new JDBCUtil();
		return instance;
	}
	/* DBMS 접속시 사용되는 방식
	 *   - thin, OCI
	 *   1) thin 방식
	 *     . 순수하게 자바 패키지(클래스들)만으로 바로 DB와 연결 
	 *     . 범용성이 높고, 상대적으로 OCI보다 속도가 느리다
	 *   2)OCI(Oracle Call Interface)
	 *     . '.DLL과' '.SO' 파일과 같이 특정 운영체제 내에서만 수행되는 Native Module을 통해 DB에 연결하는 방식 
	 *     . 각 하드웨어/소프트웨어(O/S) 별로 전용의 DB연결 프로그램을 OCI라 한다.
	 *     . 하드웨어 또는 소프트웨어 전용의 Module을 사용하여 thin보다 속도가 빠르다.
	 */
	private final String url = "jdbc:oracle:thin:@192.168.145.24:1521:xe";
	private final String user = "PROJECT11";
	private final String password = "JAVA";
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt= null;
	private Statement stmt= null;
	
	public List<LinkedHashMap<String,Object>> selectList(String sql, List<Object> param){
		
		List<LinkedHashMap<String,Object>> resultSet  = null;
		LinkedHashMap<String,Object> row = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i<= param.size(); i++) {
				pstmt.setObject(i+1, param.get(i));
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				row = new LinkedHashMap<>();
				if(resultSet == null) resultSet = new ArrayList<>();
				for(int i = 1; i <= colCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				resultSet.add(row);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {rs.close();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
		
		
		return resultSet;
		
	}
	
	public List<LinkedHashMap<String,Object>> selectList(String sql){
		
		List<LinkedHashMap<String,Object>> resultSet  = null;
		LinkedHashMap<String, Object> row = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			resultSet = new ArrayList<LinkedHashMap<String,Object>>();
			while(rs.next()) {
				row = new LinkedHashMap<>();
				if(resultSet == null) resultSet = new ArrayList<>();
				for(int i = 1; i <= colCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				resultSet.add(row);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(pstmt!=null)try {rs.close();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
		
		return resultSet;
		
		
	}
	
	public LinkedHashMap<String,Object> selectOne(String sql, List<Object> param){
		
		
		LinkedHashMap<String,Object> row = null;
		
		try {
			
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				pstmt.setObject(i+1, param.get(i));
			}
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				row = new LinkedHashMap<String,Object>();
				for(int i = 1; i <= colCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt != null) try { rs.close();}catch(Exception e) {}
			if(pstmt != null) try { rs.close();}catch(Exception e) {}
			if(rs != null) try { rs.close();}catch(Exception e) {}
			if(conn != null) try { rs.close();}catch(Exception e) {}
		}
		return row;
		
	}
	
	public LinkedHashMap<String,Object> selectOne(String sql){
		
		LinkedHashMap<String,Object> row = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			stmt = conn.createStatement();
			rs = pstmt.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				row = new  LinkedHashMap<String,Object>();
				
				for(int i = 1; i <= colCount; i++) {
					// getColumnName vs getColumnLabel
					// getColumnName : 원본 컬럼명을 가져옴
					// getColumnLabel : as로 선언된 별명을 가져옴, 없으면 원본 컬럼명
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					
					row.put(key, value);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.close();}catch(Exception e) {}
			if(conn!=null)try {conn.close();}catch(Exception e) {}
		}
		
		return row;
		
	}
	
	public int update(String sql, List<Object> param) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);

			for(int i = 0; i < param.size(); i++) {
				pstmt.setObject(i + 1, param.get(i));
			}
			
			// result에 반영된 record 건수 반환(int)
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try{rs.close();}catch(Exception e) {}
			if(conn != null) try{conn.close();}catch(Exception e) {}
		}
		
		System.out.println("result : "+result);
		return result;
	}
	 
	public int update(String sql) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();
			
			// result에 반영된 record 건수 반환(int)
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) try{rs.close();}catch(Exception e) {}
			if(conn != null) try{conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	
	
	
	
	
}
