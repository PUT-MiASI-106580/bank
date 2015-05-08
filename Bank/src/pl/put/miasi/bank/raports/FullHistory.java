package pl.put.miasi.bank.raports;

import pl.put.miasi.bank.accountdata.IAccount;

public class FullHistory implements IRaport<FullHistory> {

	public IAccount account;
	
	public String history;
	
	public Object object;
	
	FullHistory( IAccount account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	@Override
	public FullHistory accept(Visitor v) {
		return v.visit(this);
	}

}
