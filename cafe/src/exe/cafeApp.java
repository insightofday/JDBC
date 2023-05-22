package exe;

import java.util.Scanner;

import cafe.cafeService;

public class cafeApp {
	Scanner sc=new Scanner(System.in);
	cafeService cs=new cafeService();
	
	public cafeApp(){
		run();
	}
	

	public void run() {
		boolean run=true;
		while(run) {
			System.out.println("1.메뉴 조회| 2.메뉴 상세 조회|3.메뉴 등록|4.판매|5.메뉴 삭제|6.매출|7.종료");
			System.out.println("메뉴 입력> ");
			String menu=sc.nextLine();
			
			switch(menu) {
			case "1":
				cs.search();
				break;
			case "2":
				cs.searchDetail();
				break;
			case "3":
				cs.add();
				break;
			case "4":
				cs.sell();
				break;
			case "5":
				cs.deleteMenu();
				break;
			case "6":
				cs.view();
				break;
			case "7":
				System.out.println("end of prog");
				run=false;
				break;
			}
			
		}
		
	}
}
