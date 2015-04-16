package pl.put.miasi.bank;

public class FullHistory implements IReport<FullHistory> {

	public Account account;
	
	public String history;
	
	public Object object;
	
	FullHistory( Account account ){
		this.account = account;
		history = new String();
		object = new Object();
	}
	
	@Override
	public FullHistory accept(Visitor v) {
		return v.accept(this);
	}

}
