package cafe;

import java.util.List;
import java.util.Scanner;

public class cafeService {
	Scanner sc=new Scanner(System.in);
	cafe cafe=null;

	public void search() {
		// 메뉴조회
		List<cafe>list=cafeDAO.getInstance().search();
		for(int i=0;i<list.size();i++) {
			System.out.println("메뉴 : "+list.get(i).getCoffeeMenu()+" 가격: "+list.get(i).getCoffeePrice());
		}
	}

	public void searchDetail() {
		//메뉴상세조회
		System.out.println("메뉴 입력> ");
		String menu=sc.nextLine();
		cafe=cafeDAO.getInstance().searchDetail(menu);
		if(cafe==null) {
			System.out.println("없는 메뉴입니다");
		}else {
			System.out.println("메뉴 : "+cafe.getCoffeeMenu()+" 가격 : "+cafe.getCoffeePrice()
			+ " 설명 : "+cafe.getCoffeeExplain());
		}
		
	}

	public void add() {
		// 메뉴등록
		System.out.println("메뉴 입력> ");
		String menu=sc.nextLine();
		System.out.println("가격 입력> ");
		int price=Integer.parseInt(sc.nextLine());
		System.out.println("설명 입력> ");
		String explain=sc.nextLine();
		int result=cafeDAO.getInstance().add(menu, price, explain);
		if(result>0) {
			System.out.println("메뉴 등록 완료");
		}else {
			System.out.println("메뉴 등록 실패");
		}
	}

	public void sell() {
		// 판매
		System.out.println("메뉴 입력> ");
		String menu=sc.nextLine();
		int result=cafeDAO.getInstance().sell(menu);
		if(result>0) {
			System.out.println("판매량 완료");
		}else {
			System.out.println("판매량 집계를 실패하였습니다.");
		}
		
		
	}

	public void deleteMenu() {
		// 메쥬삭제
		System.out.println("메뉴 입력> ");
		String menu=sc.nextLine();
		int result=cafeDAO.getInstance().deleteMenu(menu);
		if(result>0) {
			System.out.println("메뉴 삭제 완료");
		}else {
			System.out.println("메뉴 삭제를 실패하였습니다.");
		}
		
	}

	public void view() {
		// 매출
		List<cafe>list=cafeDAO.getInstance().view();
		for(int i=0;i<list.size();i++) {
			System.out.println("메뉴 : "+list.get(i).getCoffeeMenu()+" 판매갯수: "+list.get(i).getCoffeSales()
					+"판매 금액 : "+list.get(i).getCoffeePrice());
		}
		
	}

}
