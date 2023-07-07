package service;

import java.util.Map;

import controller.Controller;
import dao.LoginDAO;
import util.Menu;
import util.ScanUtil;

public class LoginService {

	// 싱글톤패턴
	private static LoginService instance = null;
	private LoginService () {};
	public static LoginService getInstance() {
		if(instance == null)instance = new LoginService();
		return instance;
	}
	
	public static int loginCount = 0;
	
	LoginDAO dao = LoginDAO.getInstance();
	Map<String, Object> sessionStorage = Controller.sessionStorage;
	int pageNo = 0;
	
	public int login() {
		int count =0;
		while(true) {
			if(count >=3 ) {
				System.out.println("******로그인에 3회 실패하였습니다.******"
						+ "\n******메인 메뉴로 돌아갑니다.******");
				return Menu.HOME;
			} 
			System.out.print("ID : ");
			String id = ScanUtil.nextLine();
			System.out.print("PassWord : ");
			String pw = ScanUtil.nextLine();
			
			Map<String, Object> result = dao.login(id,pw);
			
			if(result != null) {
				Controller.sessionStorage.put("login", true);
				Controller.sessionStorage.put("loginInfo", result);
				System.out.println(result);
				System.out.println(result.get("MEM_NAME") + "님 반갑습니다.");
				
				
				if(String.valueOf(result.get("MEM_ADMIN")).equals("1")) {
					System.out.println("관리자 페이지로 이동합니다.");
					return pageNo = Menu.ADMIN_PAGE;
				}else if(String.valueOf(result.get("MEM_ADMIN")).equals("0")) {
					System.out.println("회원 페이지로 이동합니다.");
					return pageNo = Menu.MEMBER_PAGE;
				}
			}else {
				System.out.println("ID 또는 PW가 올바르지 않습니다.");
			}			
			count++;
		}
		
	}
	
	
}
