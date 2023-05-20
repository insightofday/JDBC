package Member;

import java.util.List;
import java.util.Scanner;

public class MemberService {
	Scanner sc=new Scanner(System.in);
	public static Member memberInfo=null;//스테틱으로 선언해서 프로젝트 내에서 사용가능케 만듬
	
	public void login() {
		Member member=null;
		System.out.println("ID>");
		String id=sc.nextLine();
		
		System.out.println("PW>");
		String pw=sc.nextLine();
		
		//회원정보조회(방법1:java에서 구현)
		member=MemberDAO.getInstance().login(id);
		if(member!=null) {//데이터가 있음+입력한 id가 맞다
			//왜냐면 로그인메서드에서 select * from member where member_id=?"하기 때문에
			if(member.getMemberPw().equals(pw)) {
				System.out.println("login success");
				System.out.println(member.getMemberId()+" welcome");
				memberInfo=member;//조회된 정보를 Static변수에 넣어서 전역에서 사용할 수 있게 함

			}else {
				System.out.println("wrong pw");
			}
		}else {
			System.out.println("wrong id");
		}
	}
	//회원정보조회(방법2:DB로 구현)
	//select * from member where member_id=? and member_pw=?
	//데이터가 조회된 경우:id, pw가 맞아서 login된 경우
	//조회x:id또는 pw불일치
	
	
	
	
	//회원가입
	public void insertMember() {
		System.out.println("~~~~~~~~~~~~회원가입~~~~~~~~~~");
		String id="";
		while(true) {
			System.out.println("ID>");
			id=sc.nextLine();
			Member member=MemberDAO.getInstance().login(id);
			if(member!=null) {
				System.out.println("이미 있는 id인데용");
			}else if(member==null) {
				System.out.println("사용가능한 id~추카~");
				break;
			}

		}
		System.out.println("PW>");
		String pw=sc.nextLine();
		System.out.println("Phone>");
		String phone=sc.nextLine();
		System.out.println("ADdress>");
		String addr=sc.nextLine();
		//System.out.println("Grade");는 default값 있어서 생략
		Member mem=new Member();
		mem.setMemberId(id);
		mem.setMemberPw(pw);
		mem.setMemberPhone(phone);
		mem.setMemberAddr(addr);
		int result=MemberDAO.getInstance().insertMember(mem);
		
		if(result>0) {
			System.out.println("회원가입되엇슴");
			//memberInfo=MemberDAO.getInstance().login(id);>> 회원가입 후 바로 로그인되게 하는 기능
		}else {
			System.out.println("회원가입실패");
		}
		
		
	}
	public void logout() {
		memberInfo=null;
		System.out.println("success logout");
	}
	
	public void confimrInfo() {
		System.out.println("회원정보조회");
		Member member=MemberDAO.getInstance().login(memberInfo.getMemberId());
		System.out.println("Id :"+member.getMemberId());
		System.out.println("Pw:"+member.getMemberPw());
		System.out.println("Phone: "+member.getMemberPhone());
		System.out.println("address:"+member.getMemberAddr());
		System.out.println("Grade:"+(member.getMemberGrade().equals("N")?"일반사용자":"관리자"));
		
		
	}
	//전체조회
	public void getMemberList() {
		List<Member>list=MemberDAO.getInstance().getMemberList();
		System.out.println("전체회원조회란");
		for(int i=0;i<list.size();i++) {
			System.out.println("ID : "+list.get(i).getMemberId());
			System.out.println("PW : "+list.get(i).getMemberPw());
			System.out.println("PHONE : "+list.get(i).getMemberPhone());
			System.out.println("ADDRESS : "+list.get(i).getMemberAddr());
			System.out.println("grade:"+(list.get(i).getMemberGrade().equals("A")?"일반사용자":"관리자"));
			System.out.println("==================================================");
		}
		
	}
	//연락처수정
	public void updatePhone() {
		System.out.println("연락처수정");
		System.out.println("ID>");
		String id=sc.nextLine();
		Member member=new Member();
		String phone="";
		
		while(true) {
			System.out.println("연락처>");
			phone=sc.nextLine();
			if(phone.length()>13) {
				System.out.println("자릿수초과했습니다 13자리 수 내로 입력하세요");//DB에 최대길이를 13으로 설정해놓음
			}else {
				System.out.println("연락처 확인 완료");
				break;
			}
		}
		member.setMemberId(id);
		member.setMemberPhone(phone);
		int result=MemberDAO.getInstance().updatePhone(member);
		
		if(result>0) {
			System.out.println("연락처 수정 완려");
		}else {
			System.out.println("연락처 수정 실패");
		}
	}
	
	//회원정보 삭제
	public void deleteMemeber() {
		System.out.println("회원정보 삭제");
		System.out.println("ID>");
		String id=sc.nextLine();
		
		int result=MemberDAO.getInstance().deleteMember(id);
		if(result>0) {
			System.out.println("회원정보삭제완료");
		}else {
			System.out.println("회원정보삭제실패");
		}
	}
	
	
}
