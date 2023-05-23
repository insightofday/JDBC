package account;

import common.DAO;
import member.Member;

public class AccountDAO extends DAO{
	private static AccountDAO aDAO=null;
	
	private AccountDAO() {	}
	
	public static AccountDAO getInstance() {
		if(aDAO==null) {
			aDAO=new AccountDAO();
		}return aDAO;
	}
	
	
	public int insertMember (Member member) {
		int result=0;
		try {conn();
		String sql="insert into member values(?,?,?,'N')";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		pstmt.setString(3, member.getMemberName());
		result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
	
	
	public int insertAccount(Account account) {
		int result=0;
		try {
			conn();
			String sql="insert into account values(?,?,sysdate,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account.getAccountId());
			pstmt.setInt(2, account.getAccountBalance());
			pstmt.setString(3, account.getMemberId());
			result=pstmt.executeUpdate();	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return result;
	}
	
	public int inOutMoney(Account account, int command) {
		int result=0;
		try {
			conn();
			//통장잔고를 확인하는 sql
			String sql2="select account_balance from account where account_id=?";
			pstmt=conn.prepareStatement(sql2);
			pstmt.setString(1, account.getAccountId());
			rs=pstmt.executeQuery();
			
			int balance = 0;//잔고를 담는 변수 
			if(rs.next()) {
				balance=rs.getInt("account_balance");
			}
			
			String sql="";
			if(command==1) {//입금
				sql="update account set account_balance=account_balance+? where account_id=?";
				
			}else if(command==2) {//출금
				if(balance<account.getAccountBalance()) {//잔고<출금예정금액
					sql=null;
				}else {
					sql="update account set account_balance=account_balance-? where account_id=?";
				}
			}
			
			if(sql==null) {
				System.out.println("예금액보다 출금액이 많음.");
			}else {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, account.getAccountBalance());
				pstmt.setString(2, account.getAccountId());
				result=pstmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return result;
	}
	
	public int transferMoney(String toAccount, String fromAccount, int balance) {
		//toaccount:받는사람의 계좌번호
		//fromaccount:보내는사람의계좌번호
		//balance:송금하는 금액
		int result=0;
		try {
			conn();
			//보내는 계좌에서 금액차감
			String sql="update account set account_balance=account_balance-? where account_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, balance);
			pstmt.setString(2, fromAccount);
			result=pstmt.executeUpdate();
			
			if(result==1) {
				System.out.println("정상출금되었습니다.");
				//받는 사람의 계좌에 금액 합산
				sql="update account set account_balance=account_balance+? where account_id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, balance);
				pstmt.setString(2, toAccount);
				result=pstmt.executeUpdate();
			}else {
				System.out.println("출금 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		
		return result;
	}
	
	public int deleteAccount(String accountId) {
		int result=0;
		try {
			conn();
			String sql="delete from account where account_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, accountId);
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConn();
		}
		return result;
	}
}
