package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.MemberDAO;
import util.JDBCUtil;
import util.Menu;
import util.ScanUtil;

public class MemberService {

	// 싱글톤패턴
	private static MemberService instance = null;
	private MemberService () {};
	public static MemberService getInstance() {
		if(instance == null)instance = new MemberService();
		return instance asasd;
	}

	Map<String, Object> sessionStorage = Controller.sessionStorage;
	MemberDAO dao = MemberDAO.getInstance();
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public int signUp() {
		
		int result = 0;
		
		System.out.println("*******회원가입********");
		String id = null;
		
		while(true) {
			System.out.println("1.회원가입 2.이전화면");
			int num = ScanUtil.nextInt();
			if(num == 2) {
				return 1;
			}
			System.out.print("ID >> ");
			id = ScanUtil.nextLine();
			Map<String, Object> map = idCheck(id);		
			if(map != null && !map.isEmpty()) {
				System.out.println(map);
				System.out.println("중복되는 아이디입니다. 다시 입력해 주십시오.");
			}else {
				System.out.println(id);
				break;
			}
		}
		System.out.print("PASSWORD >> ");
		String password = ScanUtil.nextLine();
		System.out.print("이름 >> ");
		String name = ScanUtil.nextLine();
		System.out.print("연락처 >> ");
		String tel = ScanUtil.nextLine();
		System.out.print("주소 >> ");
		String addr = ScanUtil.nextLine();
		System.out.print("이메일 >> ");
		String email = ScanUtil.nextLine();
		while(true) {
			System.out.print("위 자료를 저장하시겠습니까? (y/n) : ");
			String flag = ScanUtil.nextLine();
			
			if(flag.equalsIgnoreCase("y")) {
				result = dao.signUp(id, password, name, tel, addr, email);
				break;
			}else if(flag.equalsIgnoreCase("n")){
				System.out.println("메인 화면으로 돌아갑니다.");
				return 1;
			}else {
				break;
			}			
		}
		
//		List<Object> param = new ArrayList<>();
//		param.add(id);
//		param.add(password);
//		param.add(name);
//		param.add(tel);
//		param.add(addr);
//		param.add(email);
//		
		
		
		if(result == 1) {
			System.out.println(" " +name + " 회원님 회원가입을 축하합니다. ");
		}else {
			System.out.println("회원가입이 실패하였습니다.");
		}
		
		return Menu.HOME;
		
	}
	
	
	public int main() {
		
		while(true) {			
			System.out.println("******회원 페이지*******"); 
			System.out.println("*****메뉴를 선택해 주세요******");
			System.out.println("1.상품구매  2.장바구니 3.회원정보관리 4.구매내역 5.로그아웃");
			System.out.println("**************************");
			System.out.print("메뉴 선택 > ");
			switch(ScanUtil.nextInt()) {
			case 1:
				return Menu.BUY_PROD;
			case 2:
				return Menu.MEMBER_CART;
			case 3:
				return Menu.MEMBER_INFO;
			case 4:
				return Menu.MEMBER_BUYHISTORY;
			case 5:
				return Menu.MEMBER_LOGOUT;
			default :
				break;
			};
		}
		
		
	}
	
	
	public int logout() {
		sessionStorage.put("login", false);
		sessionStorage.put("loginInfo", null);
		return Menu.HOME;
	}
	
	
	
	

	public Map<String, Object> idCheck(String id) {
		String sql = "SELECT * FROM MEMBER WHERE MEM_ID = ?";
		List<Object> param = new ArrayList<>();
		param.add(id);
		return jdbc.selectOne(sql, param);
	}
	
	
	
	
	
	
}
