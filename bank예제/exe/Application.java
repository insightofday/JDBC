package exe;

import java.util.Scanner;

import member.MemberService;

public class Application {
	Scanner sc=new Scanner(System.in);
	MemberService ms=new MemberService();
	public Application() {
		run();
	}
	
	private void run() {
		while(true) {
			if(MemberService.memberInfo==null) {
				System.out.println("1.login 2.close");
				int menu=Integer.parseInt(sc.nextLine());
				if(menu==1) {//로그인기능
						ms.login();
				}else if(menu==2){
					System.out.println("close the bank");
					break;
				}else {
					System.out.println("잘못입력했습니다");
				}
			}
			else if(MemberService.memberInfo!=null) {
				if(MemberService.memberInfo.getMemberAuth().equals("N")) {//고객
					new MemberApp();
				}else if(MemberService.memberInfo.getMemberAuth().equals("B")) {//은행원
					new AccountApp();
				}
				//로그인정보를 토대로 업무를 나눔(고객N/은행원B)
			}
		}
		
	}
}
