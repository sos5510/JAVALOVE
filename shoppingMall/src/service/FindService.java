package service;

import java.util.*;

import controller.Controller;
import dao.FindDAO;
import util.*;

public class FindService {

	private static FindService instance = null;

	private FindService() {
	}

	public static FindService getInstance() {
		if (instance == null)
			instance = new FindService();
		return instance;
	}

	FindDAO dao = FindDAO.getInstance();
	int pageNo = 0;

	public int list() {
		System.out.println();
		System.out.println("다음은 무슨 메뉴를 선택할거예요? >>> ");
		System.out.println("1. ID찾기  2. 비밀번호찾기 3. 홈으로가기");
		int result = 0;
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			result = Menu.MEMBER_FIND_ID;
			break;
		case 2:
			result = Menu.MEMBER_FIND_PW;
			break;
		case 3:
			result = Menu.HOME;
		default:
			break;
		}
		return result;
	}

	public int find_id() {
		while (true) {
			System.out.println("이름 입력>>> ");
			String name = ScanUtil.nextLine();
			System.out.println("연락처 입력>>> ");
			String tel = ScanUtil.nextLine();

			Map<String, Object> result = dao.find(name, tel);

			if (result != null) {
				Controller.sessionStorage.put("findId", true);
				Controller.sessionStorage.put("findIdInfo", result);
				System.out.println(result.get("MEM_NAME") + "님의 ID는 " + result.get("MEM_ID") + "입니다");
				pageNo = Menu.HOME;
				break;
			} else {
				System.out.println("잘못 입력하셨습니다");
				System.out.print("홈으로 돌아가시겠습니까? (y/n) : ");
				String flag = ScanUtil.nextLine();

			if (flag.equalsIgnoreCase("y")) {
				System.out.println();
				System.out.println();
				pageNo = Menu.HOME;
				break;

			} else if (flag.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println("-------------------------");
				System.out.println("재입력하겠습니다");
				System.out.println();
				System.out.println();
			} else {

				pageNo = Menu.HOME;
				System.out.println();

				break;
			}
		}
	}
	return pageNo;
}

public int find_pw() {
	while (true) {
		System.out.println("아이디 입력>>> ");
		String id = ScanUtil.nextLine();
		System.out.println("이름 입력>>> ");
		String name = ScanUtil.nextLine();
		System.out.println("연락처 입력>>> ");
		String tel = ScanUtil.nextLine();

		Map<String, Object> result = dao.find(name, tel);

		if (result != null) {
			Controller.sessionStorage.put("findId", true);
			Controller.sessionStorage.put("findIdInfo", result);
			System.out.println(result.get("MEM_NAME") + "님의 PW는 " + result.get("MEM_PW") + "입니다");
			pageNo = Menu.HOME;
			break;
		} else {
			System.out.println("잘못 입력하셨습니다");
			System.out.print("재입력 하시겠습니까? (y/n) ");
			String flag = ScanUtil.nextLine();

			if (flag.equalsIgnoreCase("y")) {
				System.out.println();
				System.out.println("-------------------------");
				System.out.println("재입력하겠습니다");
				System.out.println();
				System.out.println();
			
			} else if (flag.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println();
				pageNo = Menu.HOME;
				break;
				
			} else {
				pageNo = Menu.HOME;
				System.out.println();
				break;
					
				}
			}
		}
		return pageNo;
	}
}