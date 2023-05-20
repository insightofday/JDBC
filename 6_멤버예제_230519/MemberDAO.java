package Member;

import java.util.ArrayList;
import java.util.List;

import DAO.DAO;

public class MemberDAO extends DAO{
	private static MemberDAO memberDao=null;
	
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		if(memberDao==null) {
			memberDao=new MemberDAO();
		}return memberDao;
	}
	
	//login
	public Member login(String id) {
		Member member=null;
		try {conn();
		String sql="select * from member where member_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs=pstmt.executeQuery();
		if(rs.next()) {//1건뿐이기때문에while안써도됨
			member=new Member();
			member.setMemberId(rs.getString("member_id"));
			member.setMemberPw(rs.getString("member_pw"));
			member.setMemberPhone(rs.getString("member_phone"));
			member.setMemberAddr(rs.getString("member_addr"));
			member.setMemberGrade(rs.getString("member_grade"));//한 글자이지만 char형으로 받아오는 건 없어서  String으로 조회
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();}
		return member;}
	
	//회원가입
	public int insertMember(Member member) {//다량의 정보가 들어오기 때문에 매개변수로 객체를 받음
		int result=0;
		try {conn();
		String sql="insert into member values(?,?,?,?,'N')";/// N이 기입됨(default)
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		pstmt.setString(3, member.getMemberPhone());
		pstmt.setString(4, member.getMemberAddr());
		//pstmt.setString(5, member.getMemberGrade());
		result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			disConn();
		}
		
		return result;
	}
	
	//전체조회(admin)
	public List<Member> getMemberList(){
		List<Member>list =new ArrayList<>();
		Member member=null;
		try {conn();
		String sql="select * from member";
		pstmt=conn.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			member=new Member();
			member.setMemberId(rs.getString("member_id"));
			member.setMemberPw(rs.getString("member_pw"));
			member.setMemberPhone(rs.getString("member_phone"));
			member.setMemberAddr(rs.getString("member_addr"));
			member.setMemberGrade(rs.getString("member_grade"));
			list.add(member);
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return list;
	}
	//회원 연락처 수정(admin)
	public int updatePhone(Member member) {
		int result=0;
		try {conn();
		String sql="update member set member_phone=? where member_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberPhone());
		pstmt.setString(2, member.getMemberId());
		
		result=pstmt.executeUpdate();
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return result;
	}
	
	//회원삭제(admin)
	public int deleteMember(String id) {
		int result=0;
		try {
			conn();
			String sql="delete from member where member_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
}
