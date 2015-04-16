package pl.put.miasi.bank;

public class DepositReport {
	public IAccount account;
	
	public String history;
	
	public Object object;
	
	DepositReport( IAccount account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	public DepositReport accept(Visitor v) {
		return v.visit(this);
	}
}
