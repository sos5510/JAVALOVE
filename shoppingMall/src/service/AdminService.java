package service;

import java.util.Map;

import controller.Controller;
import dao.AdminDAO;
import dao.LoginDAO;
import util.Menu;
import util.ScanUtil;

public class AdminService {
	private static AdminService instance = null;
	private AdminService() {}
	public static AdminService getInstance() {
		if(instance == null) instance = new AdminService();
		return instance;
		}
	
	AdminDAO dao = AdminDAO.getInstance();
	int pageNo = 0;
	
	public int list(){
		System.out.println();
		System.out.println("다음은 무슨 메뉴를 선택할거예요? >>> ");
		System.out.println("1. 회원관리  2. 상품관리 3. 카테관리 4. 매출관리  5. 홈으로");
		int result=0;
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			result =Menu.ADMIN_MEMBER; 
			break;
		case 2:
			result =Menu.ADMIN_PROD;
			break;
		case 3:
			result =Menu.ADMIN_CATEGORY;
			break;
		case 4:
			result =Menu.ADMIN_SALES;
			break;
		case 5:	
			result =Menu.HOME;
			break;
		default:
			break;
		}
		return result; 
	}
	
	public int member(){    //회원관리 - AMDIN_MEMBER
		System.out.println();
		System.out.println("다음은 무슨 메뉴를 선택할거예요? >>> ");
		System.out.println("1.전체관리  2.개인관리 3.이전으로 ");
		int result=0;
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			result =Menu.ADMIN_FULL; //전체
			break;
		case 2:
			result =Menu.ADMIN_PERSONAL; //개인
			break;
		case 3:	
			result =Menu.ADMIN_PAGE; //관리자 페이지로
			break;
		default:
			break;
}
		return result;
	}
		
	public int mem_p() { // 회원관리 -개인 - ADMIN_PERSONAL
		System.out.println();
		System.out.println("다음은 무슨 메뉴를 선택할거예요? >>> ");
		System.out.println("1.권한부여  2.수정/삭제 3.이전으로 ");
		int result = 0;
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			result = Menu.ADMIN_EMPOWERMENT;
			break;
		case 2:
			result = Menu.ADMIN_MODIFY;
			break;
		case 3:
			result = Menu.ADMIN_MEMBER;
			break;
		default:
			break;
		}
		return result;
	}
	
		public int empowerment() { //개인 권한부여
			
				while (true) {
					System.out.println(" 회원을 조회하겠습니다");
					System.out.println("아이디 입력>>> ");
					String id = ScanUtil.nextLine();
					System.out.println("이름 입력>>> ");
					String name = ScanUtil.nextLine();
				

					Map<String, Object> result = dao.select(id, name);
					System.out.println(result);

					if (result != null) {    //회원 조회가 완료 되었을경우
						
						System.out.println(result.get("MEM_NAME") + "님의 관리자여부는 " + result.get("MEM_ADMIN") + " 입니다");
						pageNo = Menu.HOME;
						
						
						/*System.out.println("이 회원의 관리자 여부를 변경하시겠습니까?");
						System.out.print("1: 변경 \t 2: 다른 회원 조회 \t 3: 이전으로 ");
						
						
						switch(ScanUtil.nextInt()) {
						case 1: 
							System.out.println("해당 숫자를 입력해주시길 바랍니다");
							System.out.println(" 0 : 회원으로 변경 \t 1 : 관리자로 변경 " );
							int admin = ScanUtil.nextInt();
							
						
							
						
							
							
						case 2: continue;
						case 3: pageNo = Menu.ADMIN_PERSONAL;
						
						
						
						
					
					
					
					
					
					
					
					}
					
					*/
					
					} else {  // 회원조회가 실패했을경우
							
						System.out.println("잘못 입력하셨습니다");
						System.out.print("재입력하시겠습니까? (y/n) : ");
						String flag = ScanUtil.nextLine();

							if (flag.equalsIgnoreCase("y")) {
								
								System.out.println("-------------------------");
								System.out.println("재입력하겠습니다");
								System.out.println();
								pageNo = Menu.ADMIN_EMPOWERMENT;
							

							} else if (flag.equalsIgnoreCase("n")) {
							
							pageNo = Menu.ADMIN_PERSONAL;
							break;

							} else {
								break;

						}
					}
					
					pageNo = Menu.ADMIN_PERSONAL;
					break;}
				return pageNo;
			}
		
		public int modify() {
			int result = 0;
			return result;
		}
	
		public int full() {
			int result = 0;
			return result;
		
}}