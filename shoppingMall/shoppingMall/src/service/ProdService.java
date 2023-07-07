package service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ProdDAO;
import util.Menu;
import util.ScanUtil;

public class ProdService {

	private static ProdService instance = null;
	private ProdService() {};
	public static ProdService getInstance() {
		if(instance == null)instance = new ProdService();
		return instance;
	}
	
	ProdDAO dao = ProdDAO.getInstance();
	String sql;
	List<LinkedHashMap<String,Object>> list = new ArrayList<>();
	
	
	public int main() {
		
		System.out.println("*****카테고리를 선택해주세요*****");
		System.out.println("***************************");
		System.out.println("1.BEST 2.상의 3.하의 4.ACC");
		System.out.print(">> ");
		int num = ScanUtil.nextInt();
		int item;
		int qty;
		
		if(num == 1) {
			
		}else if(num == 2) {
			
			list = dao.topList();
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			System.out.println("-----1.구매 2.장바구니  3.이전화면-----");
			int choice = ScanUtil.nextInt();
			switch(choice) {
			case 1:
				System.out.print("몇번 품목을 구매하시겠습니까? : ");
				item = ScanUtil.nextInt();
				System.out.print("몇개를 구매하시겠습니까? : ");
				qty = ScanUtil.nextInt();
			//	dao.buyThis(list, item, qty);
//				dao.buyOne();
				break;
			case 2:
				System.out.print("몇번 품목을 장바구니에 담으시겠습니까? : ");
				item = ScanUtil.nextInt();
				System.out.print("몇개를 담으시겠습니까? : ");
				qty = ScanUtil.nextInt();
//				dao.addCartThis(item, qty);
				break;
			case 3:
				main();
				break;
				
			}
				
		}else if(num == 3) {
			System.out.println("1.구매 2.장바구니  3.이전화면");
			list = dao.pantsList();
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			int choice = ScanUtil.nextInt();
			switch(choice) {
			case 1:
				System.out.print("몇번 품목을 구매하시겠습니까? : ");
				
				break;
			case 2:
				System.out.print("몇번 품목을 장바구니에 담으시겠습니까? : ");
				
			case 3:
				main();
				break;
				
			}
			
			
		}
		else if(num == 4) {
			System.out.println("1.구매 2.장바구니  3.이전화면");
			list = dao.accList();
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			int choice = ScanUtil.nextInt();
			switch(choice) {
			case 1:
				System.out.print("몇번 품목을 구매하시겠습니까? : ");
				break;
			case 2:
				System.out.print("몇번 품목을 장바구니에 담으시겠습니까? : ");
				break;
			case 3:
				main();
				break;
				
			}
			
			
		}else {
			System.out.println("잘못된 입력입니다.");
			main();
		}
		
		
		return Menu.BUY_PROD;
		
	}
	
}
