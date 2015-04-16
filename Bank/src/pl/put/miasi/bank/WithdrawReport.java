package pl.put.miasi.bank;

public class WithdrawReport {
	public IAccount account;
	
	public String history;
	
	public Object object;
	
	WithdrawReport(IAccount account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	public WithdrawReport accept(Visitor v) {
		return v.visit(this);
	}
}
