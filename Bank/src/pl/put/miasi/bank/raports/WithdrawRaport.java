package pl.put.miasi.bank.raports;

import pl.put.miasi.bank.accountdata.IAccount;

public class WithdrawRaport {
	public IAccount account;
	
	public String history;
	
	public Object object;
	
	WithdrawRaport(IAccount account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	public WithdrawRaport accept(Visitor v) {
		return v.visit(this);
	}
}
