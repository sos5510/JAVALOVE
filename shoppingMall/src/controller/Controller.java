package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import service.LoginService;
import service.MemberService;
import service.ProdService;
import util.Menu;
import util.ScanUtil;

public class Controller {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	// service class 객체 생성
	MemberService memberService = MemberService.getInstance();
	LoginService loginService = LoginService.getInstance();
	ProdService prodService = ProdService.getInstance();
	
	// Controller 객체 생성
	public static Controller controller = new Controller();
	
	public static void main(String[] args) throws IOException {
		controller.start();
		

	}

	
	private void start() throws IOException {
		sessionStorage.put("login", false);
		sessionStorage.put("loginInfo", null);
		
		int choice = Menu.HOME;
		
		while(true) {
			switch(choice ) {
			// 메인메뉴
			case Menu.HOME :
				choice = home();
				break;
				
			// 로그인 화면 -> return에 따라 일반회원페이지, 관리자페이지 구분해서 이동
			case Menu.MEMBER_LOGIN:
				choice = loginService.login();
				break;
			// 일반회원 페이지
			case Menu.MEMBER_PAGE:
				System.out.println("login : " + sessionStorage.get("login"));
				System.out.println("loginInfo : " + sessionStorage.get("loginInfo"));
				choice = memberService.main();
				break;
			// 회원가입 페이지
			case Menu.MEMBER_SIGNUP:
				choice = memberService.signUp();
				break;
			// 로그아웃
			case Menu.MEMBER_LOGOUT:	
				choice = memberService.logout();
				System.out.println("login : " + sessionStorage.get("login"));
				System.out.println("loginInfo : " + sessionStorage.get("loginInfo"));
				break;
				
			// 상품구매 페이지
			case Menu.BUY_PROD:
				choice = prodService.main();
				break;
			}
		}
		
	}
	
	
	private int home() {
		
		System.out.println("******GPTEAM 쇼핑몰*******");
		System.out.println("*****메뉴를 선택해 주세요******");
		System.out.println("1.로그인  2.ID/PW 찾기 3.회원가입");
		System.out.println("**************************");
		System.out.print("메뉴 선택 > ");
		switch(ScanUtil.nextInt()) {
		case 1:
			return Menu.MEMBER_LOGIN;
		case 2:
			return Menu.MEMBER_FIND;
		case 3:
			return Menu.MEMBER_SIGNUP;
		default :
			return Menu.HOME;
		}
		
	}
	
	
	
}
