package account;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data //전체 다 import

public class Account {
//	ACCOUNT_ID      NOT NULL VARCHAR2(20) 
//	ACCOUNT_BALANCE          NUMBER       
//	ACCOUNT_CREDATE          DATE         
//	MEMBER_ID                VARCHAR2(20) 
	private String accountId;
	private int accountBalance;
	private Date accountCredate;
	private String memberId;
	
	
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
	public Date getAccountCredate() {
		return accountCredate;
	}
	public void setAccountCredate(Date accountCredate) {
		this.accountCredate = accountCredate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
