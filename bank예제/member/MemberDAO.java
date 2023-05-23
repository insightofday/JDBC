package member;

import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class MemberDAO extends DAO{
	private static MemberDAO member=null;
	
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		if(member==null) {
			member=new MemberDAO();
		}return member;
	}
	
	
	public Member login(String id) {
		Member member=null;
		try {
			conn();
			String sql="select * from member where member_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member=new Member();
				member.setMemberId(id);
				member.setMemberName(rs.getString("member_name"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberAuth(rs.getString("member_auth"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return member;
	}
	
	
	//계좌정보조회(가정: 로그인이 되어 있다(따라서 고객정보를 따로 받아올 필요가 없다
	public List<Member> getAccountInfo(){
		List<Member>list=new ArrayList<>();
		Member member=null;
		try {
			conn();
			String sql="select m.member_id, a.account_id, a.account_balance,m.member_name, m.member_auth\r\n"
					+ "from account a join member m\r\n"
					+ "on a.member_id=m.member_id\r\n"
					+ "where a.member_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.memberInfo.getMemberId());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				member=new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setAccountId(rs.getString("account_id"));
				member.setAccountBalance(rs.getInt("account_balance"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberAuth(rs.getString("member_auth"));
				
				list.add(member);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return list;
	}
	
	
	
}
