package Store;

import java.util.List;
import java.util.Scanner;

public class storeService {
	Scanner sc= new Scanner(System.in);

	public void selectAll() {
		List<Store>list=StoreDAO.getInstance().selectAll();
		System.out.println("다음은 모든 가게의 정보입니다");
		for(int i=0;i<list.size();i++) {
			System.out.println("가게번호 :"+list.get(i).getStoreId());
			System.out.println("가게명 :"+list.get(i).getStoreName());
			System.out.println("가게연락처 :"+list.get(i).getStoreTel());
			System.out.println("주소 :"+list.get(i).getStoreAddr());
			System.out.println("매출 :"+list.get(i).getStoreSales());
			System.out.println("==============================================");
		}
	}


	public void insert() {
		Store store=new Store();
		System.out.println("가게등록");
		System.out.println("상호명>");
		store.setStoreName(sc.nextLine());
		System.out.println("연락처>");
		store.setStoreTel(sc.nextLine());
		System.out.println("주소>");
		store.setStoreAddr(sc.nextLine());
		System.out.println("매출>");
		store.setStoreSales(Integer.parseInt(sc.nextLine()));
		
		int result=StoreDAO.getInstance().insert(store);
		
		if(result>0) {
			System.out.println("입력성공");
		}else {
			System.out.println("실패");
		}
		
	}
	
	public void getStoreSales() {
		List<Store>list=StoreDAO.getInstance().getStoreSales();
		for(int i=0;i<list.size();i++) {
			System.out.println("지역구 :"+list.get(i).getStoreAddr());
			System.out.println("매출합계 :"+list.get(i).getStoreSales());
			System.out.println("==============================================");
		}
	}
	
	public void updateSales() {
		Store store=new Store();
		System.out.println("매출수정");
		System.out.println("ID>");
		store.setStoreId(Integer.parseInt(sc.nextLine()));
		
		System.out.println("매출입력>");
		store.setStoreSales(Integer.parseInt(sc.nextLine()));
		int result=StoreDAO.getInstance().updateSales(store);
		if(result>0) {
			System.out.println("매출수정완료");
		}else {
			System.out.println("매출수정실패");
		}
	}


	
	

}
