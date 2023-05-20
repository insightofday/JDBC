package exe;

import java.util.Scanner;

import Member.MemberService;

public class MemberApp {
	Scanner sc=new Scanner(System.in);
	boolean run=true;
	MemberService ms= new MemberService();
	
	
	public MemberApp() {
		start();
	}

	private void start() {
		while(run) {
			if(MemberService.memberInfo!=null) {//로그인 된 경우(memberservice.memberinfo!=null)
				loginMenu();//login후 메뉴
				
			}else if(MemberService.memberInfo==null) {//로그인 안 된 경우(memberservice.memberinfo==null)
				logoutMenu();//로그아웃 후 메뉴
			}
		}
		
	}

	private void logoutMenu() {
		System.out.println("1.login 2.회원가입 3.close");
		System.out.println("입력>");
		String menu=sc.nextLine();
		if(menu.equals("1")) {
			ms.login();
		}else if(menu.equals("2")) {
			ms.insertMember();
		}else if(menu.equals("3")){
			run=false;
			System.out.println("program close");
		}
		else {
			System.out.println("wrong number");
		}
//		System.out.println("1.logout 2.close");
//		String menu=sc.nextLine();
//		switch(menu) {
//		case "1":
//			MemberService.memberInfo=null;
//			System.out.println("logout success");
//			break;
//		case "2":
//			run=false;
//			System.out.println("close the system");
//			break;
//		}
				
	}

	private void loginMenu() {
		if(MemberService.memberInfo.getMemberGrade().equals("A")) {//admin계정, 일반계정의 권한에 따라 로그인화면을 다르게 구현
			System.out.println("1/모든회원정보조회  2/회원정보수정  3/회원삭제  4/로그아웃  5/종료");
			String menu=sc.nextLine();
			switch(menu) {
			case "1" :
				ms.getMemberList();
				break;
			case "2" :
				ms.updatePhone();
				break;
			case "3" :
				ms.deleteMemeber();
				break;
			case "4" :
				ms.logout();
				break;
			case "5" :
				run=false;
				System.out.println("프로그램종료");
				break;
			}
		}else if(MemberService.memberInfo.getMemberGrade().equals("N")) {
			System.out.println("1.내정보조회 2.로그아웃 3.종료");
			String menu=sc.nextLine();
			if(menu.equals("1")) {//로그인된 경우기 때문에 memberinfo에 변수가 기입되어 있음+id는 pk기때문에 바뀔 일x 따라서 login메서드(id로 정보추출)재활용
				ms.confimrInfo();
			}else if(menu.equals("2")) {
				ms.logout();
			}else if(menu.equals("3")) {
				run=false;
				System.out.println("program close");
			}else {
				System.out.println("없는 메뉴 입력");
			}
			
		}
	
	
	
		
	}
	
	private void login() {
		System.out.println(MemberService.memberInfo.getMemberId());
		System.out.println("login success");
		String tmp=sc.nextLine();
	}
	

}
