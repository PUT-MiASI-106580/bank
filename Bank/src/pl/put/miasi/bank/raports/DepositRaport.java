package pl.put.miasi.bank.raports;

import pl.put.miasi.bank.accountdata.IAccount;

public class DepositRaport {
	public IAccount account;
	
	public String history;
	
	public Object object;
	
	DepositRaport( IAccount account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	public DepositRaport accept(Visitor v) {
		return v.visit(this);
	}
}
