package member;

public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAuth;
	
	//계좌조회(join을 위한 필드 추가
	//조인시 멤버테이블에서 갖고오는 칼럼이 더 많기 때문에 멤버클래스에 어카운트테이블의 정보를 넣는다
	
	private String accountId;
	private int accountBalance;
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	
}
