package member;

import java.util.List;
import java.util.Scanner;

public class MemberService {
	public static Member memberInfo=null;
	Scanner sc=new Scanner(System.in);
	
	public void login() {
		System.out.println("로그인!");
		System.out.println("id>");
		String id=sc.nextLine();
		
		System.out.println("pw>");
		String pw=sc.nextLine();
		
		Member member=MemberDAO.getInstance().login(id);
		
		if(member!=null) {
			if(member.getMemberPw().equals(pw)) {
				System.out.println("login성공");
				memberInfo=member;
			}else {
				System.out.println("wrong pw");
			}
		}else {
			System.out.println("없는id임");
		}
	
	}
	public void getAccountInfo() {
		System.out.println("고객 계좌 정보 조회");
		List<Member>list=MemberDAO.getInstance().getAccountInfo();
		for(int i=0;i<list.size();i++) {
			System.out.println((i+1)+"번째~~~");
			System.out.println("계좌번호 : "+list.get(i).getAccountId());
			System.out.println("예금주 : "+list.get(i).getMemberName());
			System.out.println("잔액 : "+list.get(i).getAccountBalance());
			System.out.println("등급 : "+(list.get(i).getMemberAuth().equals("N")?"일반사용자":"은행원"));
		}
		
		
	}
}
