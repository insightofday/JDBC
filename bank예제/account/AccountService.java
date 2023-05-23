package account;

import java.util.Scanner;

import member.Member;

public class AccountService {
	Scanner sc=new Scanner(System.in);
	
	public void insertMember() {
		System.out.println("고객등록하세용");
		Member member=new Member();
		System.out.println("ID>");
		member.setMemberId(sc.nextLine());
		System.out.println("PW>");
		member.setMemberPw(sc.nextLine());
		System.out.println("Name>");
		member.setMemberName(sc.nextLine());
		
		int result= AccountDAO.getInstance().insertMember(member);
		if(result>0) {
			System.out.println("성공했슴미다");
		}else {
			System.out.println("고객등록실패햇슴다");
		}
	}
	
	public void insertACcount() {
		System.out.println("계좌 개설을 진행합니다.");
		Account ac=new Account();
		System.out.println("accountId(계좌번호)>");
		ac.setAccountId(sc.nextLine());
		//System.out.println("acccountBanlance(잔고)");
		ac.setAccountBalance(5000);
		System.out.println("id>");
		ac.setMemberId(sc.nextLine());
		
		int result=AccountDAO.getInstance().insertAccount(ac);
		if(result>0) {
			System.out.println("계좌개설 성공");
		}else {
			System.out.println("실패 ㅋㅋㅋ");
		}
	}
	
	public void inOutMoney() {
		System.out.println("입출금란");
		System.out.println("1.입금 2.출금");
		int menu=Integer.parseInt(sc.nextLine());
		Account ac= new Account();
		System.out.println("계좌번호 >");
		ac.setAccountId(sc.nextLine());
		
		if(menu==1){
			System.out.println("입금 금액>");
		}else if(menu==2) {
			System.out.println("출금 금액>");
		}
		ac.setAccountBalance(Integer.parseInt(sc.nextLine()));
		
		int result=AccountDAO.getInstance().inOutMoney(ac,menu);
		
		if(result>0) {
			if(menu==1) {
				System.out.println("입금성공");
			}else {
				System.out.println("출금성공");
			}
		}
			else if(menu==1) {
				System.out.println("입금실패");
			}else {
				System.out.println("출금실패");
			}
		
	}
	
	public void transferMoney() {
		System.out.println("계좌이체~ㅎㅎ");
		System.out.println("받으실 분의 계좌>");
		String toAccount=sc.nextLine();
		System.out.println("보낼 계좌>");
		String fromAccount=sc.nextLine();
		System.out.println("이체 금액>");
		int balance=Integer.parseInt(sc.nextLine());
		
		int result=AccountDAO.getInstance().transferMoney(toAccount, fromAccount, balance);
		if(result>0) {
			System.out.println("계좌이체 성공했ㅅ브니다.");
		}else {
			System.out.println("계좌이체 실패했ㅅ브니다.");
		}
	}
	
	public void delteAccount() {
		System.out.println("계좌해지 서비스입니다");
		System.out.println("해지 할 계좌는?>");
		String accountId=sc.nextLine();
		
		int result=AccountDAO.getInstance().deleteAccount(accountId);
		if(result==1) {
			System.out.println("정상적으로 해지되었습니다.");
		}else {
			System.out.println("실패하였습니다.");
		}
	}
}
