package exe;

import java.util.Scanner;

import account.AccountService;
import member.MemberService;

public class MemberApp {//고객관련 기능
	Scanner sc=new Scanner(System.in);
	MemberService ms=new MemberService();
	AccountService ac=new AccountService();
	public MemberApp() {
		memberRun();
	}
	
	private void memberRun() {
		boolean flag=true;
		while(flag) {
		menu();
		String selectNo=sc.nextLine();
			switch(selectNo) {
			case "1" :
				ms.getAccountInfo();
				break;
			case "2" :
				ac.inOutMoney();
				break;
			case "3" :
				ac.transferMoney();
				break;
			case "4" :
				flag=false;//로그인화면으로 돌아감(아마도? 
				MemberService.memberInfo=null;
				System.out.println("개인 업무 종료");
				break;
		}
		}
	}
	private void menu() {//계좌조회, 입출금(한번에구현),이체,
		System.out.println("1.계좌조회 2.입출금 3.이체 4.뒤로가기");
	}
}
