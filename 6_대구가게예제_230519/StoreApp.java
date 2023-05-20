package exe;

import java.util.Scanner;

import Store.storeService;

public class StoreApp {
	Scanner sc=new Scanner(System.in);
	public StoreApp() {
		start();
	}

	private void start() {
		storeService ss=new storeService();
		while(true) {
			System.out.println("1.모든 가게 정보 조회  2.지역구별 매출 합계 조회 3.가게정보 입력 4.매출수정 5.종료");//1
			System.out.println("입력>>");//얘네 셋이 while문 밖에 있으면 무한루프돈다    						2
			int menu=Integer.parseInt(sc.nextLine());//												3
		if(menu==1) {
			ss.selectAll();
		}else if(menu==2) {
			ss.getStoreSales();
		}else if(menu==3) {
			ss.insert();
		}else if(menu==4) {
			ss.updateSales();
		}else if(menu==5) {
			System.out.println("종료합니다");
		}
		}
		
//			switch(menu) {
//			case 1:
//				ss.selectAll();
//				break;
//			case 2:
//				ss.getStoreSales();
//				break;
//			case 3: 
//				ss.insert();
//				break;
//			case 4:
//				ss.updateSales();
//				break;
//			case 5:
//				System.out.println("종료합니다");
//				break;
//			
//		}
	

	}
}
