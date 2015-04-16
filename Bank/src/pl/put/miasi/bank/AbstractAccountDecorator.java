package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public abstract class AbstractAccountDecorator implements IAccount {
	protected IAccount account;
	
	public AbstractAccountDecorator(IAccount account) {
		this.account = account;
	}
	
	@Override
	public String getPin() {		
		return account.getPin();
	}
	
	@Override
	public String getId() {
		return account.getId();
	}
	
	@Override
	public ArrayList<Owner> getOwner() {
		return account.getOwner();
	}		
	
	@Override
	public double getBalance() {
		return account.getBalance();
	}

	public Map<Date, Registry> getHistory() {
		return account.getHistory();
	}
	
}
