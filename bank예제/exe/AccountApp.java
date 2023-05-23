package exe;

import java.util.Scanner;

import account.AccountService;
import member.MemberService;

public class AccountApp {//은행원영역
	Scanner sc=new Scanner(System.in);
	AccountService ac=new AccountService();
	public AccountApp() {
		run();
	}
private void run() {
	while(true) {
		menu();
		int menuNo=Integer.parseInt(sc.nextLine());
		if(menuNo==1) {
			ac.insertMember();
		}else if(menuNo==2){
			ac.insertACcount();
		}else if(menuNo==3) {
			ac.inOutMoney();
		}else if(menuNo==4) {
			ac.transferMoney();
		}else if(menuNo==5) {
			ac.delteAccount();
		}else if(menuNo==6) {
			System.out.println("은행원 업무 종료");
			MemberService.memberInfo=null;
			break;
		}
	}
}

private void menu() {
	System.out.println("1.고객등록 2.계좌개설 3.입.출금 4.계좌이체 5.계좌해지 6.뒤로가기");
}
	
}
